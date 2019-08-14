package com.gdufe.campus.controller;

import com.gdufe.campus.config.EmailConfig;
import com.gdufe.campus.enums.ResultEnum;
import com.gdufe.campus.execption.BusinessException;
import com.gdufe.campus.pojo.DO.UserDO;
import com.gdufe.campus.pojo.DTO.UserDTO;
import com.gdufe.campus.pojo.VO.ResultVO;
import com.gdufe.campus.pojo.VO.UserVO;
import com.gdufe.campus.service.UserService;
import com.gdufe.campus.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.Map;


@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    EmailConfig emailConfig;

    @GetMapping("/loginPage")
    public String loginPage() {
        return "user/login";
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

    @ResponseBody
    @PostMapping("/register")
    public ResultVO register(UserVO user) {
        if (user == null) {
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
