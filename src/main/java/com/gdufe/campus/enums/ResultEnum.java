package com.gdufe.campus.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    SYSTEM_ERROR(0, "系统错误"),

    PARAM_ERROR(1, "参数不正确"),
    PARAM_EMPTY(2, "参数为空"),
    EMAIL_ERROR(3, "注册邮件发送失败"),
    CODE_ERROR(4, "验证码错误"),

    ADD_FAILED(6, "新增失败"),
    UPDATE_FAILED(7, "更新失败"),
    DELETE_FAILED(8, "删除失败"),

    PASSWORD_ERROR(10,"账号不存在或密码错误"),


    SUCCESS(1000, "成功"),


    ;
    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
