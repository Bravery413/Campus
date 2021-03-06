package com.gdufe.campus.mapper;

import com.gdufe.campus.pojo.DO.Category;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryMapperTest {
    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void findAllTest(){
        List<Category> categories = categoryMapper.findAll();
        Assert.assertNotNull(categories);
    }
}