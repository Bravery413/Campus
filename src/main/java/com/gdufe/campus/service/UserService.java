package com.gdufe.campus.service;


import com.gdufe.campus.pojo.DTO.UserDTO;

/**
 * @author: Bravery
 * @create: 2019-08-10 16:40
 **/


public interface UserService {

    UserDTO login(UserDTO user);

    UserDTO findUserById(Long id);

    boolean checkAccount(String account);

    int save(UserDTO userDTO);




}
