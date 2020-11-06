package com.gdufe.campus.controller;

import com.gdufe.campus.config.EmailConfig;
import com.gdufe.campus.enums.ResultEnum;
import com.gdufe.campus.exception.BusinessException;
import com.gdufe.campus.mapper.UserMapper;
import com.gdufe.campus.pojo.DTO.UserDTO;
import com.gdufe.campus.pojo.VO.MobilePassVO;
import com.gdufe.campus.pojo.VO.ResultVO;
import com.gdufe.campus.pojo.VO.UserVO;
import com.gdufe.campus.service.Impl.UserServiceImpl;
import com.gdufe.campus.service.UserService;
import com.gdufe.campus.utils.MyCaptchaUtil;
import com.gdufe.campus.utils.ResultVOUtil;
import com.gdufe.campus.utils.StringUtils;
import com.gdufe.campus.websocket.handler.QRCodeLoginHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;


@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    EmailConfig emailConfig;

    @GetMapping("/loginPage")
    public ModelAndView loginPage(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        if (request.getHeader("user-agent").contains("Mobile")){
            model.setViewName("user/mobile_login");
        }else {
            model.setViewName("user/scanLogin");
        }
        return model;
    }

    /**
     * 扫码后手机发起的Get请求
     * socket发送消息给电脑端
     * 手机返回电脑型号
     */
    @GetMapping("/qrlogin/authorize")
    public ModelAndView qrcodeLogin(@RequestParam String sid, HttpServletRequest request) throws IOException {
        ModelAndView model = new ModelAndView("user/m_authorize");
        request.getSession().setAttribute("sessionId", sid);
        WebSocketSession socketSession = QRCodeLoginHandler.getSession(sid);
        socketSession.sendMessage(new TextMessage("{\"action\": \"scanCode\"}"));
        model.addObject("ipAddr", socketSession.getAttributes().get("ipAddr"));
        return model;
    }

    /**
     *手机端请求
     * 确定同意或者拒绝
     */
    @ResponseBody
    @PostMapping("/qrlogin/authorize")
    public ResultVO authorize(@RequestBody MobilePassVO mobilePassVO, HttpServletRequest request) throws IOException {
        String sessionId = (String) request.getSession().getAttribute("sessionId");
        WebSocketSession socketSession = QRCodeLoginHandler.getSession(sessionId);
        if (mobilePassVO.getAgree()) {
            socketSession.sendMessage(new TextMessage("{\"action\": \"agree\"}"));
            //同意 创建电脑端登录状态
            UserDTO user = userService.findUserById(1L);
            request.getSession().setAttribute("loginUser", user);
            //登录成功,扫码登录完成 socket可以关掉了
            socketSession.close();
        } else {
            socketSession.sendMessage(new TextMessage("{\"action\": \"refuse\"}"));
        }
        return ResultVOUtil.success();
    }

    /**
     *完成操作后
     * 手机/PC分别的操作
     */
    @GetMapping("/home")
    public ModelAndView homePage(HttpServletRequest request,HttpSession session) {
        ModelAndView model = new ModelAndView();
//		Integer userId = (Integer) request.getSession().getAttribute("userId");
//		if (userId == null) {
//			model.setViewName("redirect:/user/login");
//		} else {
//			User user = userService.findById(userId);
//			model.addObject("user", user);
        if (request.getHeader("user-agent").contains("Mobile")) {
            model.setViewName("user/mobile_finish.html");
        } else {
            model.setViewName("user/pc_home");
            //以后电脑重定向到的地方
//            model.setViewName("redirect:https://www.baidu.com/");
        }
//		}
        return model;
    }











    @GetMapping("/center")
    public String personalPage() {
        return "user/personal";
    }

    @ResponseBody
    @PostMapping("/login")
    public ResultVO login(@RequestBody UserVO userVO,
                          Map<String, Object> map,
                          HttpSession session) {

        UserDTO userDTO = new UserDTO();
        userDTO.setAccount(userVO.getAccount());
        userDTO.setPassword(userVO.getPassword());
        try {
            UserDTO user = userService.login(userDTO);
            session.setAttribute("loginUser", user);

        } catch (BusinessException e) {
            map.put("msg", e);
            return ResultVOUtil.error(e.getCode(),e.getMessage());

        }
        return ResultVOUtil.success();
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        log.info("[退出登录]");
        session.removeAttribute("loginUser");
        return "redirect:/user/login";
    }

    /**
     * 注册系列接口
     */

    @GetMapping("/registerPage")
    public ModelAndView registerPage(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.setViewName("user/register");
        return model;
    }


    @ResponseBody
    @PostMapping("/checkAccount")
    public ResultVO checkAccount(@RequestBody UserVO user) {
        if (user == null|| StringUtils.isNullOrEmpty(user.getAccount())) {
            log.error("[用户注册] 用户账户检验参数为空");
            throw new BusinessException(ResultEnum.PARAM_EMPTY);
        }
        boolean exist = userService.checkAccount(user.getAccount());
        if (exist){
            return ResultVOUtil.success();
        }else {
            return ResultVOUtil.error(ResultEnum.ACCOUNT_EXIST);
        }

    }


    @ResponseBody
    @PostMapping("/register")
    public ResultVO register(@RequestBody UserVO user,HttpServletRequest request) {
        Boolean result = MyCaptchaUtil.check(user.getCode(), request);
        if (!result){
            return ResultVOUtil.error(ResultEnum.CODE_ERROR);
        }
        if (user == null|| StringUtils.isNullOrEmpty(user.getAccount())) {
            log.error("[用户注册] 用户注册参数为空");
            throw new BusinessException(ResultEnum.PARAM_EMPTY);
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        int save = userService.save(userDTO);
        return ResultVOUtil.success(save);
    }


    @GetMapping("/active")
    public ModelAndView active(@RequestParam String account,@RequestParam String key) {
        ModelAndView modelAndView = new ModelAndView();
        try{
            userService.active(account,key);
        }catch (BusinessException e){
            modelAndView.setViewName("redirect:/user/loginPage");
            return modelAndView;
        }
        modelAndView.setViewName("user/active");
        return modelAndView;
    }

}
