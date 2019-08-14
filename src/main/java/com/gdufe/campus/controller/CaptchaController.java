package com.gdufe.campus.controller;


import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
        CaptchaUtil.out(request, response);
    }


}
