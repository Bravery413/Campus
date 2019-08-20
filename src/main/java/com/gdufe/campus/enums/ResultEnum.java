package com.gdufe.campus.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    SYSTEM_ERROR(0, "系统错误"),

    PARAM_ERROR(1, "参数不正确"),
    PARAM_EMPTY(2, "参数为空"),
    EMAIL_ERROR(3, "注册邮件发送失败"),
    CODE_ERROR(4, "验证码错误"),
    ACCOUNT_EXIST(5, "用户名已存在"),
    ADD_FAILED(6, "新增失败"),
    UPDATE_FAILED(7, "更新失败"),
    DELETE_FAILED(8, "删除失败"),
    ACCOUNT_EMPTY(9, "账号不存在"),
    PASSWORD_ERROR(10,"账号不存在或密码错误"),
    NO_ACTIVE(11, "账号未激活"),
    REACTIVE(12, "账号重复激活"),


    SUCCESS(1000, "成功"),


    ;
    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
