package com.gdufe.campus.pojo.VO.num;

import lombok.Data;

/**
 * @author: Bravery
 * @create: 2020-11-06 17:42
 **/

@Data
public class Series {
    private String name;
    private int[] data;
    private String type;
    private PointOrLine markPoint;
    private PointOrLine markLine;
    private ItemStyle itemStyle;
}
