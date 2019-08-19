package com.gdufe.campus.controller;

import com.gdufe.campus.config.EmailConfig;
import com.gdufe.campus.enums.ResultEnum;
import com.gdufe.campus.exception.BusinessException;
import com.gdufe.campus.pojo.DTO.UserDTO;
import com.gdufe.campus.pojo.VO.MobilePassVO;
import com.gdufe.campus.pojo.VO.ResultVO;
import com.gdufe.campus.pojo.VO.UserVO;
import com.gdufe.campus.service.Impl.UserServiceImpl;
import com.gdufe.campus.service.UserService;
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
import java.util.Map;


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
            map.put("msg", ResultEnum.PASSWORD_ERROR.getMessage());
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
    public ResultVO register(@RequestBody UserVO user) {
        if (user == null|| StringUtils.isNullOrEmpty(user.getAccount())) {
            log.error("[用户注册] 用户注册参数为空");
            throw new BusinessException(ResultEnum.PARAM_EMPTY);
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        int save = userService.save(userDTO);
        return ResultVOUtil.success(save);
    }


    @ResponseBody
    @PostMapping("/email")
    public ResultVO sendEmail() {
        try {
            //TODO 邮箱激活头尾去弄,只打通邮件发送
            HtmlEmail email = new HtmlEmail();//不用更改
            email.setHostName(emailConfig.getHostName());//需要修改，126邮箱为smtp.126.com,163邮箱为163.smtp.com，QQ为smtp.qq.com
            email.setCharset("UTF-8");
            email.addTo("2472937751@qq.com");// 收件地址

            email.setFrom(emailConfig.getSendFromEmailAddress(), emailConfig.getSendFromName());//此处填邮箱地址和用户名,用户名可以任意填写

            email.setAuthentication(emailConfig.getSendFromEmailAddress(), emailConfig.getEmailPassword());//此处填写邮箱地址和客户端授权码

            email.setSubject(emailConfig.getEmailTitle());//此处填写邮件名，邮件名可任意填写
            email.setMsg("<a herf='http://braverymall.cn/frontactiveAcount?mail.key=9bdeb4d3-c60b-4c9b-8ec7-ef06f7ad7640'>点击激活账号:</a>\n" +
                    "如果无法点击,使用以下地址\n" +
                    "http://braverymall.cn/frontactiveAcount?mail.key=9bdeb4d3-c60b-4c9b-8ec7-ef06f7ad7640");//此处填写邮件内容
            email.send();

            return ResultVOUtil.success();
        } catch (Exception e) {
            log.error("[邮箱激活] 邮箱激活失败");
        }
        return ResultVOUtil.error(ResultEnum.EMAIL_ERROR.getCode(), ResultEnum.EMAIL_ERROR.getMessage());
    }

}
