package com.gdufe.campus.controller;


import com.wf.captcha.Captcha;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;


/**
 * @author bravery
 * @date 2019/8/14 11:02
 */
@Controller
@RequestMapping("/captcha")
public class CaptchaController {

    @GetMapping("/codePage")
    public String codePage() {
        return "/test/code";
    }

    @RequestMapping("/image")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //详细设置参数
        CaptchaUtil.setHeader(response);
        GifCaptcha gifCaptcha = new GifCaptcha(130, 48, 5);
        gifCaptcha.setCharType(Captcha.TYPE_DEFAULT);
        request.getSession().setAttribute("captcha",gifCaptcha.text().toLowerCase());
        gifCaptcha.out(response.getOutputStream());
        //简写
//        CaptchaUtil.out(request, response);

    }


}
