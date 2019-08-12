package com.gdufe.campus.mapper;

import com.gdufe.campus.pojo.DO.LessonDO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LessonMapperTest {
    @Autowired
    LessonMapper lessonMapper;

    @Test
    public void findAll() {
        List<LessonDO> lessons = lessonMapper.findAll();
        Assert.assertNotNull("[代课] 查询全部代课失败", lessons);
    }

    @Test
    public void findById() {
        LessonDO lesson = lessonMapper.findById(1L);
        System.out.println(lesson);
        Assert.assertNotNull("[代课] ID查询代课失败", lesson);
    }


    @Test
    public void deleteById() {
        int delete = lessonMapper.deleteById(10L);
        System.out.println(delete);
        Assert.assertEquals(1, delete);
    }

    @Test
    public void taken() {
        int taken = lessonMapper.taken(10L);
        System.out.println(taken);
        Assert.assertEquals(1, taken);

    }

    @Test
    public void save() {
        LessonDO lesson = new LessonDO();
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
        int save = lessonMapper.save(lesson);
        System.out.println(save);
        Assert.assertEquals(1, save);
    }

    @Test
    public void update() {
        LessonDO lesson = new LessonDO();
        lesson.setId(9L);
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
        int update = lessonMapper.update(lesson);
        System.out.println(update);
        Assert.assertEquals(1, update);
    }
}