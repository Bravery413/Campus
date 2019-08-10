package com.gdufe.campus.service.Impl;

import com.gdufe.campus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gdufe.campus.mapper.*;

/**
 * @author: Bravery
 * @create: 2019-08-10 16:44
 **/

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UserMapper userMapper;

}
