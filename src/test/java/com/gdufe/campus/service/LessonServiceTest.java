package com.gdufe.campus.service;

import com.gdufe.campus.pojo.DTO.LessonDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LessonServiceTest {

    @Autowired
    LessonService lessonService;

    @Test
    public void listLessons() {
        List<LessonDTO> lessonDTOS = lessonService.listLessons();
        Assert.assertNotNull(lessonDTOS);
    }

    @Test
    public void findByUser() {
        List<LessonDTO> lessonDTOS = lessonService.findByUser(2L);
        Assert.assertNotNull(lessonDTOS);
    }

    @Test
    public void findById() {
        LessonDTO lessonDTO = lessonService.findById(1L);
        Assert.assertNotNull(lessonDTO);
    }

    @Test
    public void addLesson() {
        LessonDTO lesson = new LessonDTO();
        lesson.setInfo("info");
        lesson.setIsTake(1);
        lesson.setLastTime(System.currentTimeMillis());
        lesson.setSex(1);
        lesson.setTime("周五晚上");
        lesson.setWechat("bra");
        lesson.setLocation(1);
        lesson.setUid(1L);
        lesson.setPlace("我家");
        lesson.setPrice(25);
        int add = lessonService.addLesson(lesson);
        Assert.assertEquals(1,add);

    }

    @Test
    public void updateLesson() {
        LessonDTO lesson = new LessonDTO();
        lesson.setId(11L);
        lesson.setInfo("updateTest");
        lesson.setIsTake(1);
        lesson.setLastTime(System.currentTimeMillis());
        lesson.setSex(0);
        lesson.setTime("周五晚上111");
        lesson.setWechat("bra111");
        lesson.setLocation(0);
        lesson.setUid(2L);
        lesson.setPlace("我家啊啊啊啊");
        lesson.setPrice(250);
        int update = lessonService.UpdateLesson(lesson);
        Assert.assertEquals(1,update);
    }

    @Test
    public void deleteLesson() {
        int delete = lessonService.DeleteLesson(11L);
        Assert.assertEquals(1,delete);

    }

    @Test
    public void taken() {
        boolean taken = lessonService.taken(11L);
        Assert.assertEquals(true,taken);
    }
}