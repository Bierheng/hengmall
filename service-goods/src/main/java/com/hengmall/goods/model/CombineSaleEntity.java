package com.hengmall.goods.model;


import lombok.Data;

import java.util.Date;

/**
 * 拼单 实体类
 */
@Data
public class CombineSaleEntity {

    private int id;          //拼单ID
    private int product_id;  // 店铺商品id
    private int price;       //拼单购买价格
    private int status;      //有效状态；默认1：未开始，2：已开始，0：无效
    private int stock;       //秒杀库存
    private String title;    //拼单标题
    private String icon;     //拼单图标
    private String start_time; //限时开始时间
    private String end_time;   //限时结束时间
    private String created_time;//创建时间
    private String nickname;    // 昵称
    private String head_img;    //头像
    private int initiator;      //拼主，0：拼单者，1：发起者
    private String out_trade_no;//订单号
    private Date startTime;     //开始时间
    private Date endTime;       //结束时间
    private int user_id;        //用户ID
    private int combine_num;    //拼单人数
    private int still_need;     //拼单还需人数
    private int shops_id;       //店铺ID
}
