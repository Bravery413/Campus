package com.gdufe.campus.controller;

import com.gdufe.campus.pojo.VO.ResultVO;
import com.gdufe.campus.pojo.VO.num.*;
import com.gdufe.campus.utils.ResultVOUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

/**
 * @author: Bravery
 * @create: 2020-11-06 17:21
 **/


@Controller()
public class SortController {
    static int[] qus = {86, 21, 41, 50, 62, 40};
    static int i = 0;
    static int j = 0;

    @GetMapping("sort")
    public String test() {

        return "sort/sort.html";
    }

    @ResponseBody
    @GetMapping("date")
    public ResultVO lessonDetail() {
        getNew();
        System.out.println(i);
        System.out.println(j);
        Title title = new Title();
        title.setText("Echarts");
        title.setSubtext("Bravery");
        title.setLeft("left");
        title.setBorderColor("red");
        title.setBorderWidth("3");


        Feature feature = new Feature();
        FeatureItem saveAsImage = new FeatureItem();
        saveAsImage.setShow(true);
        FeatureItem restore = new FeatureItem();
        restore.setShow(true);
        FeatureItem dataView = new FeatureItem();
        dataView.setShow(true);
        FeatureItem dataZoom = new FeatureItem();
        dataZoom.setShow(true);
        feature.setDataView(dataView);
        feature.setDataZoom(dataZoom);
        feature.setRestore(restore);
        feature.setSaveAsImage(saveAsImage);
        MagicType magicType = new MagicType();
        String[] type = {"line", "bar"};
        magicType.setType(type);
        feature.setMagicType(magicType);
        Tooltip tooltip = new Tooltip();
        tooltip.setTrigger("axis");
        Toolbox toolbox = new Toolbox();
        toolbox.setShow(true);
        toolbox.setFeature(feature);


        XAxis xAxis = new XAxis();
        String[] str1 = {"衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"};
        xAxis.setData(str1);

        YAxis yAxis = new YAxis();
        yAxis.setObj(new Object());

//        int[] random = getRandom(6, 100);
        PointOrLine markPoint = new PointOrLine();
        DataObj data = new DataObj();
        data.setType("max");
        data.setName("最大值");
        markPoint.setDataObj(data);
        Series series = new Series();
        series.setName("销量");
        series.setType("bar");
        bubleSort(true);
        series.setData(qus);
        series.setMarkLine(markPoint);

        Legend legend = new Legend();
        legend.setData(new String[]{"销量"});

        Option option = new Option();
        option.setTitle(title);
        option.setToolbox(toolbox);
        option.setTooltip(tooltip);
        option.setXAxis(xAxis);
//        option.setYAxis(yAxis);
        option.setLegend(legend);
        option.setSeries(series);

        return ResultVOUtil.success(option);
    }

    public static void getNew() {
        if (i >= 6) {
            qus = getRandom(6, 100);
            i=0;
            j=0;
        }

    }

    public static int[] getRandom(int num, int max) {
        if (num <= 0 || max <= 0) {
            return new int[0];
        }
        int[] qus = new int[num];
        for (int i = 0; i < qus.length; i++) {
            Random random = new Random();
            qus[i] = random.nextInt(max) + 1;
        }
        System.out.println(Arrays.toString(qus));
        return qus;
    }


    public static void bubleSort(boolean go) {
        for (; i < qus.length; i++) {
            for (; j < qus.length - i - 1; j++) {
                if (go) {
                    if (qus[j] > qus[j + 1]) {
                        int temp = qus[j];
                        qus[j] = qus[j + 1];
                        qus[j + 1] = temp;
                    }
                    go = false;
                } else {
                    System.out.println(Arrays.toString(qus));
                    return;
                }
            }
            j=0;
        }
    }
}

