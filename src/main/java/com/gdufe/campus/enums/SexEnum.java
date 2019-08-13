package com.gdufe.campus.enums;

import lombok.Getter;

@Getter
public enum SexEnum {
    UNKNOWN(0, "未知"),

    MAN(1, "男"),
    WOMAN(2, "女"),



    ;
    private Integer code;

    private String message;

    SexEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
