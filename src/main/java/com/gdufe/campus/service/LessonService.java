package com.gdufe.campus.service;

import com.gdufe.campus.pojo.DTO.LessonDTO;

import java.util.List;

/**
 * @author bravery
 * @date 2019/8/12 15:20
 */
public interface LessonService {
    List<LessonDTO> listLessons();

    List<LessonDTO> findByUser(Long uid);

    LessonDTO findById(Long id);

    Integer addLesson(LessonDTO lessonDTO);

    Integer UpdateLesson(LessonDTO lessonDTO);

    Integer DeleteLesson(Long id);

    boolean taken(Long id);






}
