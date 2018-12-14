package com.hengmall.goods.model;


import lombok.Data;

import java.util.Date;

/**
 * 限时抢购 实体类
 */
@Data
public class FlashSaleEntity {

    private int id;
    private int product_id; // 店铺商品id
    private int price;      //限时购买价格
    private int status;     //有效状态；默认1：未开始，2：已开始，0：无效
    private int stock;     //秒杀库存
    private int timeliness_id;     //抢购时效性关联id
    private String title;   //限时抢购标题
    private String icon;   //抢购图标
    private String start_time;   //限时开始时间
    private String end_time;   //限时结束时间
    private String created_time; 
    private String nickname;
    private String head_img;
    private Date startTime;   //开始时间
    private Date endTime;   //结束时间
    private int user_id;
    private int flash_sale_id;

}
