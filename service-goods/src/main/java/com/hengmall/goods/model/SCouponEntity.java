package com.hengmall.goods.model;

import lombok.Data;

import java.util.Date;

/**
 * 优惠券 实体类
 */
@Data
public class SCouponEntity {

    private int id;
    private String title;
    private double prerequisite;
    private double price;
    private int user_id;
    private String img;
    private String start_time;
    private String end_time;
    private String created_time;

}
