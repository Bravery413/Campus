package com.gdufe.campus.pojo.DO;

import lombok.Data;

@Data
public class UserDO {

    private Long id;

    private String account;

    private String password;

    private String username;

    private String email;

    private String headImg;

    private String active;


}
