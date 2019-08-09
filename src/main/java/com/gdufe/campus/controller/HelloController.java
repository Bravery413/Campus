package com.gdufe.campus.controller;


import com.gdufe.campus.mapper.CategoryMapper;
import com.gdufe.campus.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author bravery
 * @date 2019/8/9 10:00
 */
@RestController
public class HelloController {

//    @GetMapping("/hello")
//    public String hello() {
//        return "helloaaa";
//    }
    @Autowired CategoryMapper categoryMapper;

    @RequestMapping("/list")
    public String listCategory(Model m) throws Exception {
        List<Category> cs=categoryMapper.findAll();

        m.addAttribute("cs", cs);

        return "cs";
    }
}
