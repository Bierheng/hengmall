package com.hengmall.goods.model;

import lombok.Data;

import java.util.Date;

/**
 * 商品分类模块 实体类
 */
@Data
public class SCategoryEntity {

    //一级类目：1-首页，2-免税店，3-轻奢，4-女装，5-鞋包，6-母婴，
    public static final int Index = 1;
    public static final int Duty_Free = 2;
    public static final int Extravagant = 3;
    public static final int Female = 4;
    public static final int Shoes = 5;
    public static final int  Powdered= 6;
    public static final int  personal= 7;


    private int id;     //商品类别id
    private String name;   //类别名称
    private int level;   //类别级别1或2
    private int pid;    //上级类型id
    private String icon1;  //图标，小图，嵌入在首页
    private String icon2;  //图标，大图，在分类展示页面显示大图
}
