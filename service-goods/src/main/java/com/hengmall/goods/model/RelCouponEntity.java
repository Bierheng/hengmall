package com.hengmall.goods.model;

import lombok.Data;

/**
 * 优惠券关联用户 实体类
 */
@Data
public class RelCouponEntity {

    private int id;
    private String s_coupon_id;
    private int user_id;
    private String created_time;
    private int shops_id; //店铺主ID

}
