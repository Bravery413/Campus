package com.gdufe.campus.utils;

import com.gdufe.campus.pojo.DTO.UserDTO;

import javax.servlet.http.HttpSession;

/**
 * @author bravery
 * @date 2019/8/15 9:18
 */
public class LoginSessonUtil {
    public final static String USER_SESSON = "loginUser";
    public static UserDTO getLoginUser(HttpSession session){
        return  (UserDTO)session.getAttribute(USER_SESSON);
    }
    public static void setloginUser(UserDTO userDTO,HttpSession session){
        session.setAttribute(USER_SESSON,userDTO);
    }


}
