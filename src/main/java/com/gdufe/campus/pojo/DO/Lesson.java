package com.gdufe.campus.pojo.DO;

import lombok.Data;

@Data
public class Lesson {

    private Long id;

    private String place;

    private String time;

    private Integer price;

    private Integer isTake;

    private String info;

    private Long lastTime;

    private String wechat;

    private Long uid;

    //男1 女0
    private Integer sex;

    //广州1 佛山0
    private Integer location;


}
