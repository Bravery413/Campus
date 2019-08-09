package com.gdufe.campus.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bravery
 * @date 2019/8/9 10:00
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "helloaaa";
    }
}
