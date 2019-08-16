package com.gdufe.campus.controller;


import com.gdufe.campus.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bravery
 * @date 2019/8/9 10:00
 */
@Controller
public class BaseController {

    @GetMapping("/mobile/login")
    public String test() {
        return "user/m_authorize.html";
    }

}
