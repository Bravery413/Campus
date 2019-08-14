package com.gdufe.campus.pojo.VO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductVO {

    private Long id;

    private Long uid;

    private BigDecimal price;

    private String title;

    private String detail;

    private String img;

    private Long createTime;

    private Integer location;

    private String wechat;

    private String[] imgUrls;




}
