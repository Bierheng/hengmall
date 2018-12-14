package com.hengmall.goods.model;


import lombok.Data;

/**
 * 限时抢购 实体类
 */
@Data
public class FlashSaleTimelinessEntity {

    //默认1：限时秒杀，2：限时拼单
    public static final int Kill = 1;
    public static final int Combine = 2;


    private int id;
    private String img;     //活动图片
    private String desc;   //描述
    private int type;     //默认1：限时秒杀，2：限时拼单
    private String start_time;     //抢购开始时间
    private String end_time;   //抢购结束时间
    private long startTime;     //抢购开始时间（时间戳）
    private long endTime;   //抢购结束时间（时间戳）
    private String created_time;   //抢购图标

}
