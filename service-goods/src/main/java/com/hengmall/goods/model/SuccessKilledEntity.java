package com.hengmall.goods.model;


import lombok.Data;

/**
 * 商品活动模块实体类
 */
@Data
public class SuccessKilledEntity {

    private int id;
    private int user_id;
    private int flash_sale_id;
    private String phone;       //手机号码
    private String created_time;
}
