package com.gdufe.campus.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    SUCCESS(0, "成功"),
    PARAM_ERROR(1, "参数不正确"),
    PARAM_EMPTY(2, "参数为空"),
    EMAIL_ERROR(3, "注册邮件发送失败"),

    PASSWORD_ERROR(10,"账号不存在或密码错误"),

    SYSTEM_ERROR(100, "系统错误")
    ;
    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
