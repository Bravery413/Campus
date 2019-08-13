package com.gdufe.campus.service.Impl;

import com.gdufe.campus.enums.LocationEnum;
import com.gdufe.campus.pojo.DTO.ProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductServiceImplTest {
    @Autowired ProductServiceImpl productService;


    @Test
    public void listAll() {
        List<ProductDTO> productDTOS = productService.listAll();
        productDTOS.forEach(System.out::println);
        Assert.assertNotNull(productDTOS);
    }

    @Test
    public void findByUser() {
        List<ProductDTO> productDTOS = productService.findByUser(16L);
        productDTOS.forEach(System.out::println);
        Assert.assertNotNull(productDTOS);
    }

    @Test
    public void findById() {
        ProductDTO productDTO = productService.findById(1L);
        System.out.println(productDTO);
        Assert.assertNotNull(productDTO);
    }

    @Test
    public void add() {
        ProductDTO product = new ProductDTO();
        product.setUid(11L);
        product.setCreateTime(System.currentTimeMillis());
        product.setDetail("detail1111");
        product.setImg("http://111xxx.jpg");
        product.setLocation(LocationEnum.FOSHAN.getCode());
        product.setPrice(new BigDecimal(1000));
        product.setWechat("wechat111");
        product.setTitle("title1111");
        int update = productService.add(product);
        Assert.assertEquals(1,update);
    }

    @Test
    public void update() {
        ProductDTO product = new ProductDTO();
        product.setId(31L);
        product.setUid(111L);
        product.setCreateTime(System.currentTimeMillis());
        product.setDetail("detail");
        product.setImg("http://xxx.jpg");
        product.setLocation(LocationEnum.GUANGZHOU.getCode());
        product.setPrice(new BigDecimal(1111));
        product.setWechat("wechat");
        product.setTitle("title");
        int update = productService.update(product);
        Assert.assertEquals(1,update);
    }

    @Test
    public void delete() {
        int delete = productService.delete(31L);
        Assert.assertEquals(1,delete);
    }
}