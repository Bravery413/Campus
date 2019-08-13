package com.gdufe.campus.pojo.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {

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
