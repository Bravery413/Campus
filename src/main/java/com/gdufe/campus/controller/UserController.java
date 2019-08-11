package com.gdufe.campus.controller;

import com.gdufe.campus.enums.ResultEnum;
import com.gdufe.campus.execption.BusinessException;
import com.gdufe.campus.pojo.DO.UserDO;
import com.gdufe.campus.pojo.DTO.UserDTO;
import com.gdufe.campus.pojo.VO.UserVO;
import com.gdufe.campus.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;


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
    public String login1(@RequestBody UserVO userVO,
                         Map<String, Object> map,
                         HttpSession session) {

        UserDTO userDTO = new UserDTO();
        userDTO.setAccount(userVO.getAccount());
        userDTO.setPassword(userVO.getPassword());
        try {
            UserDTO user = userService.login(userDTO);
            System.out.println(ResultEnum.PASSWORD_ERROR);
            System.out.println(ResultEnum.PASSWORD_ERROR.getCode());
            System.out.println(ResultEnum.PASSWORD_ERROR.getMessage());
            session.setAttribute("loginUser", user);

        }catch (BusinessException e){
            map.put("msg", ResultEnum.PASSWORD_ERROR.getMessage());
        }
        return "user/login";

    }
}
