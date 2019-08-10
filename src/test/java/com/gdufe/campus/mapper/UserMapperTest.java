package com.gdufe.campus.mapper;

import com.gdufe.campus.pojo.DO.UserDO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void findAll() {
        List<UserDO> users = userMapper.findAll();
        Assert.assertNotNull(users);
    }

    @Test
    public void findByID() {
        UserDO user = userMapper.findById(1);
        Assert.assertNotNull(user);
    }

    @Test
    public void findByAccount() {
        UserDO user = userMapper.findByAccount("py");
        Assert.assertNotNull(user);
    }

    @Test
    public void login() {
        UserDO userDO = new UserDO();
        userDO.setAccount("py");
        userDO.setPassword("123");
        UserDO user = userMapper.login(userDO);
        Assert.assertNotNull(user);
    }

    @Test
    public void save() {
        UserDO userDO = new UserDO();
        userDO.setAccount("aapy");
        userDO.setPassword("123");
        userDO.setEmail("py@qq.com");
        userDO.setUsername("小潘呀");
        userDO.setHeadImg("http://xxx.jpg");
        int save = userMapper.save(userDO);
        Assert.assertEquals(1,save);


    }

    @Test
    public void deleteById() {
        int delete = userMapper.deleteById(54);
        Assert.assertEquals(1,delete);
    }

    @Test
    public void update() {
        UserDO userDO = new UserDO();
        userDO.setId(48L);
        userDO.setAccount("aapy");
        userDO.setPassword("123");
        userDO.setEmail("py@qq.com");
        userDO.setUsername("小潘呀");
        userDO.setHeadImg("http://xxx.jpg");
        int update = userMapper.update(userDO);
        System.out.println(update);
        Assert.assertEquals(1,update);
    }
}