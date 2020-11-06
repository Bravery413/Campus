package com.gdufe.campus.pojo.VO.num;

import lombok.Data;

/**
 * @author: Bravery
 * @create: 2020-11-06 22:12
 **/

@Data
public class Feature {
    private FeatureItem saveAsImage;
    private FeatureItem restore;
    private FeatureItem dataView;
    private FeatureItem dataZoom;
    private MagicType magicType;


}
