package com.gdufe.campus.enums;

import lombok.Getter;

@Getter
public enum LocationEnum {
    UNKNOWN(0, "未知"),

    GUANGZHOU(1, "广州"),
    FOSHAN(2, "佛山"),



    ;
    private Integer code;

    private String message;

    LocationEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
