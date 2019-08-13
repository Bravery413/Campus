package com.gdufe.campus.pojo.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import javafx.scene.image.PixelFormat;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("goods")
public class ProductDO {

    @TableId(type=IdType.AUTO)
    private Long id;

    private Long uid;

    private BigDecimal price;

    private String title;

    private String detail;

    private String img;

    private Long createTime;

    private Integer location;

    private String wechat;




}
