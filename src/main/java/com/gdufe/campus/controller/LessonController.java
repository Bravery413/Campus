package com.gdufe.campus.controller;

import com.gdufe.campus.enums.ResultEnum;
import com.gdufe.campus.pojo.DTO.LessonDTO;
import com.gdufe.campus.pojo.DTO.UserDTO;
import com.gdufe.campus.pojo.VO.LessonVO;
import com.gdufe.campus.pojo.VO.ResultVO;
import com.gdufe.campus.service.Impl.LessonServiceImpl;
import com.gdufe.campus.utils.ResultVOUtil;
import com.wf.captcha.utils.CaptchaUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public String listPage() {
        return "lesson/lesson";
    }

    @GetMapping("/addPage")
    public String addPage() {
        return "lesson/lesson_add";
    }


    @GetMapping("/detailPage")
    public String detailPage() {
        return "lesson/lesson_detail";
    }

    @GetMapping("/selfPage")
    public String selfPage() {
        return "lesson/lesson_self";
    }


    @ResponseBody
    @PostMapping("/list")
    public ResultVO listAll() {
        List<LessonDTO> lessonDTOS = lessonService.listLessons();
        return ResultVOUtil.success(lessonDTOS);
    }

    @ResponseBody
    @PostMapping("/listSelf")
    public ResultVO listSelf(HttpSession session) {
        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
        Long uid = loginUser.getId();
        List<LessonDTO> lessonDTOS = lessonService.findByUser(uid);
        return ResultVOUtil.success(lessonDTOS);
    }
    //TODO 去接到前端
    @ResponseBody
    @PostMapping("/add")
    public ResultVO add(@RequestBody LessonVO lessonVO, HttpServletRequest request) {


        if (!CaptchaUtil.ver(lessonVO.getCode(), request)) {
            CaptchaUtil.clear(request);
            return ResultVOUtil.error(ResultEnum.CODE_ERROR.getCode(),ResultEnum.CODE_ERROR.getMessage());
        }
        else {
            return ResultVOUtil.success();
        }
//        LessonDTO lessonDTO = new LessonDTO();
//        BeanUtils.copyProperties(lessonVO,lessonDTO);
//        Integer add = lessonService.addLesson(lessonDTO);
//        if (add==1){
//            return ResultVOUtil.success();
//        }
//        return ResultVOUtil.error(ResultEnum.ADD_FAILED.getCode(),
//                ResultEnum.ADD_FAILED.getMessage());

    }

    //update
    @ResponseBody
    @PostMapping("/update")
    public ResultVO update(LessonVO lessonVO) {
        LessonDTO lessonDTO = new LessonDTO();
        BeanUtils.copyProperties(lessonVO,lessonDTO);
        Integer update= lessonService.UpdateLesson(lessonDTO);
        if (update==1){
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error(ResultEnum.UPDATE_FAILED.getCode(),
                ResultEnum.UPDATE_FAILED.getMessage());

    }
    @ResponseBody
    @GetMapping("/delete")
    public ResultVO update(Long id) {
        Integer delete = lessonService.DeleteLesson(id);
        if (delete==1){
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error(ResultEnum.UPDATE_FAILED.getCode(),
                ResultEnum.UPDATE_FAILED.getMessage());
    }
}
