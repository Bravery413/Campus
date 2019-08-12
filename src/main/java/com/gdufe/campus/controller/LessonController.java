package com.gdufe.campus.controller;

import com.gdufe.campus.pojo.DTO.LessonDTO;
import com.gdufe.campus.pojo.DTO.UserDTO;
import com.gdufe.campus.pojo.VO.LessonVO;
import com.gdufe.campus.pojo.VO.ResultVO;
import com.gdufe.campus.service.Impl.LessonServiceImpl;
import com.gdufe.campus.service.LessonService;
import com.gdufe.campus.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bravery
 * @date 2019/8/12 18:06
 */
@Controller
@RequestMapping("/lesson")
@Slf4j
public class LessonController {
    @Autowired
    private LessonServiceImpl lessonService;

    @GetMapping("/listPage")
    public String listPage(Model model) {
        return "lesson/lesson";
    }

    @ResponseBody
    @PostMapping("/list")
    public ResultVO listAll() {
        List<LessonDTO> lessonDTOS = lessonService.listLessons();
        return ResultVOUtil.success(lessonDTOS);
    }
}
