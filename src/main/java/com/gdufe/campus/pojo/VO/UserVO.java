package com.gdufe.campus.pojo.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserVO {

    private Long id;
    @JsonProperty("account")
    private String account;

    private String password;

    private String username;

    private String email;

    private String headImg;

    private String active;

}
