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

    private Integer sex;

    private Integer location;

    /** 验证码*/
    private String code;

}
