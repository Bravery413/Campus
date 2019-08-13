package com.gdufe.campus.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gdufe.campus.enums.LocationEnum;
import com.gdufe.campus.enums.ResultEnum;
import com.gdufe.campus.pojo.DO.ProductDO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductMapperTest {
    @Autowired
    private ProductMapper productMapper;

    @Test
    public void selectList() {
        QueryWrapper<ProductDO> wrapper = new QueryWrapper<>();
        wrapper.eq("uid",11L).like("title","111");
        List<ProductDO> productDOS = productMapper.selectList(wrapper);
        productDOS.forEach(System.out::println);
        Assert.assertNotNull(productDOS);
    }

    @Test
    public void selectByIdTest() {
        Long id=29L;
        ProductDO productDO = productMapper.selectById(id);
        System.out.println(productDO);
        Assert.assertNotNull(productDO);
    }

    @Test
    public void insertTest() {
        ProductDO product = new ProductDO();
        product.setUid(1L);
        product.setCreateTime(System.currentTimeMillis());
        product.setDetail("detail");
        product.setImg("http://xxx.jpg");
        product.setLocation(LocationEnum.GUANGZHOU.getCode());
        product.setPrice(new BigDecimal(100));
        product.setWechat("wechat");
        product.setTitle("title");
        int insert = productMapper.insert(product);
        Assert.assertEquals(1,insert);
    }

    @Test
    public void updateTest(){
        ProductDO product = new ProductDO();
        product.setId(29L);
        product.setUid(11L);
        product.setCreateTime(System.currentTimeMillis());
        product.setDetail("detail1111");
        product.setImg("http://111xxx.jpg");
        product.setLocation(LocationEnum.FOSHAN.getCode());
        product.setPrice(new BigDecimal(1000));
        product.setWechat("wechat111");
        product.setTitle("title1111");
        int update = productMapper.updateById(product);
        Assert.assertEquals(1,update);
    }

    @Test
    public void deleteTest(){
        Long id=30L;
        int delete = productMapper.deleteById(id);
        Assert.assertEquals(1,delete);
    }
}