package com.gdufe.campus.service.Impl;

import com.gdufe.campus.enums.ResultEnum;
import com.gdufe.campus.exception.BusinessException;
import com.gdufe.campus.pojo.DO.UserDO;
import com.gdufe.campus.pojo.DTO.UserDTO;
import com.gdufe.campus.service.UserService;
import com.gdufe.campus.utils.EmailUtil;
import com.gdufe.campus.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gdufe.campus.mapper.*;

import java.util.UUID;

/**
 * @author: Bravery
 * @create: 2019-08-10 16:44
 **/

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO login(UserDTO user) throws BusinessException {
        if (user == null || StringUtils.isNullOrEmpty(user.getAccount())
                || StringUtils.isNullOrEmpty(user.getPassword())) {
            log.error("[用户登录] 用户账号或者密码不存在 user={}", user);
            throw new BusinessException(ResultEnum.PARAM_EMPTY);
        }
        UserDO userDO = new UserDO();
        userDO.setAccount(user.getAccount());
        userDO.setPassword(user.getPassword());
        UserDO login = userMapper.login(userDO);
        if (login == null) {
            log.error("[用户登录] 用户账号不存在或者密码错误user={}", user);
            throw new BusinessException(ResultEnum.PASSWORD_ERROR);
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(login, userDTO);
        return userDTO;
    }

    @Override
    public UserDTO findUserById(Long id) {
        if (id == null || id <= 0) {
            log.error("[ID查询用户] 用户id错误,id={}", id);
            throw new BusinessException(ResultEnum.PARAM_ERROR);
        }
        UserDO userDO = userMapper.findById(id);
        if (userDO == null) {
            log.error("[ID查询用户] 用户不存在,id={}", id);
            throw new BusinessException(ResultEnum.PARAM_ERROR);
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userDO, userDTO);
        return userDTO;
    }

    @Override
    public boolean checkAccount(String account) {
        if (StringUtils.isNullOrEmpty(account)) {
            log.error("[查重账户名] 账户名为空");
            throw new BusinessException(ResultEnum.PARAM_EMPTY);
        }
        UserDO userDO = userMapper.findByAccount(account);
        return userDO == null ? true : false;
    }

    @Override
    public int save(UserDTO userDTO) {
        if (userDTO==null){
            log.error("[注册账号] 账户为空");
            throw new BusinessException(ResultEnum.PARAM_EMPTY);
        }
        userDTO.setActive(UUID.randomUUID().toString());
        userDTO.setEmail("2472937751@qq.com");
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userDTO,userDO);
        // 邮箱注册
        EmailUtil.sendEmail(userDTO);
        //TODO 密码MD5盐值加密
        return userMapper.save(userDO);
    }
}
