package com.gdufe.campus.pojo.VO.num;

import lombok.Data;

/**
 * @author: Bravery
 * @create: 2020-11-06 22:05
 **/

@Data
public class Option {
    private Title title;
    private Toolbox toolbox;
    private Tooltip tooltip;
    private Legend legend;
    private XAxis xAxis;
    private YAxis yAxis;
    private Series series;
}
