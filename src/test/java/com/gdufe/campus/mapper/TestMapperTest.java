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
public class TestMapperTest {
    @Autowired
    private TestMapper testMapper;


    @Test
    public void TestSelect(){
        List<LessonDO> lessonDOS = testMapper.selectList(null);
        lessonDOS.forEach(System.out::println);
        Assert.assertNotNull(lessonDOS);
    }

}