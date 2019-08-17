package com.gdufe.campus.utils;

import com.gdufe.campus.pojo.VO.LessonVO;
import com.wf.captcha.utils.CaptchaUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Bravery
 * @create: 2019-08-17 21:21
 **/


public class MyCaptchaUtil {
    public static Boolean check(String code, HttpServletRequest request){
        if (!CaptchaUtil.ver(code, request)) {
            com.wf.captcha.utils.CaptchaUtil.clear(request);
            return false;
        }
        return true;
    }
}
