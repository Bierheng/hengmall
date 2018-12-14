package com.hengmall.goods.model;


import lombok.Data;

/**
 * 商品活动模块实体类
 */
@Data
public class AdsEntity {

    //类型；默认1：首页活动，2：免税店活动，3：轻奢，4：女装活动，5：鞋包活动，6：母婴
    public final static int Index_Status = 1;
    public final static int Duty_Freek_Status = 2;
    public final static int Extravagant_Status = 3;
    public final static int Female_Status = 4;
    public final static int Shoes_Status = 5;
    public final static int Powdered_Status = 6;


    private int id;
    private int product_id;
    private String resource;    //资源地址
    private int type;       //类型，1：图片，2：视频
    private int status;     //状态码；默认1：首页活动，2：免税店活动，3：轻奢，4：女装活动，5：鞋包活动
    private String created_time;
    private int shops_id; 
}
