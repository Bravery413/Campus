package com.gdufe.campus.pojo.VO;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("lesson")
public class LessonVO {

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
