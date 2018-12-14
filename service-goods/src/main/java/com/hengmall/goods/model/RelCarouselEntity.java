package com.hengmall.goods.model;

import lombok.Data;

import java.util.Date;

/**
 * 图片轮播 实体类
 */
@Data
public class RelCarouselEntity {

    //一级类目：1-首页，2-免税店，3-轻奢，4-女装，5-鞋包，6-母婴，
    public static final int Index = 1;
    public static final int Duty_Free = 2;
    public static final int Extravagant = 3;
    public static final int Female = 4;
    public static final int Shoes = 5;
    public static final int  Powdered= 6;


    private int id;     //商品类别id
    private int type;   //1-商品，2-活动，3-团购
    private int relid;   //商品关联的id
    private int sid;    //图片地址关联id
    private Date ctime;    //创建时间

    private String path;   //资源地址，对象存储地址


}
