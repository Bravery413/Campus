package com.gdufe.campus.controller;

import com.gdufe.campus.pojo.DO.UserDO;
import com.gdufe.campus.pojo.DTO.UserDTO;
import com.gdufe.campus.pojo.VO.UserVO;
import com.gdufe.campus.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        return "user/login";

    }
    @PostMapping("/login")
    public String login1(@RequestBody UserVO userVO) {

        UserDTO userDTO = new UserDTO();
        userDTO.setAccount(userVO.getAccount());
        userDTO.setPassword(userVO.getPassword());
        userService.login(userDTO);
        return "user/login";

    }
}
