package com.gdufe.campus.service.Impl;

import com.gdufe.campus.config.EmailConfig;
import com.gdufe.campus.enums.ResultEnum;
import com.gdufe.campus.pojo.DTO.UserDTO;
import com.gdufe.campus.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailConfig emailConfig;

    @Test
    public void login() {
        UserDTO userDTO = new UserDTO();
        userDTO.setAccount("py");
        userDTO.setPassword("123");
        UserDTO user = userService.login(userDTO);
        System.out.println(user);
        Assert.assertNotNull(user);
    }

    @Test
    public void findUserById() {
        UserDTO user = userService.findUserById(1L);
        System.out.println(user);
        Assert.assertNotNull(user);
    }

    @Test
    public void checkAccount() {
        System.out.println(ResultEnum.PASSWORD_ERROR);
        System.out.println(ResultEnum.PASSWORD_ERROR.getCode());
        System.out.println(ResultEnum.PASSWORD_ERROR.getMessage());
        boolean result = userService.checkAccount("pyaaaaa");
        System.out.println(result);
        Assert.assertEquals(true, result);

    }

    @Test
    public void save() {
        UserDTO user = new UserDTO();
        user.setAccount("yoyo");
        user.setPassword("123");
        user.setEmail("py@qq.com");
        user.setUsername("小潘呀");
        user.setHeadImg("http://xxx.jpg");
        int save = userService.save(user);
        System.out.println(save);
        Assert.assertEquals(1, save);
    }

    @Test
    public void emailConfigTest() {
        System.out.println(emailConfig.toString());
    }
}