package com.gdufe.campus.controller;

import com.gdufe.campus.service.LessonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author bravery
 * @date 2019/8/12 18:06
 */
@Controller
@RequestMapping("/lesson")
@Slf4j
public class LessonController {
    @Autowired
    private LessonService lessonService;

    @GetMapping("/listPage")
    public String listPage(Model model) {
        return "lesson/lesson";
    }
}
