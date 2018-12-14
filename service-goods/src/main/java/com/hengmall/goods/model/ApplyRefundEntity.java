package com.hengmall.goods.model;


import lombok.Data;

/**
 * 申请退款实体类
 */
@Data
public class ApplyRefundEntity {

    private int id;
    private int user_id;            //用户ID
    private int order_id;    //订单号
    private int shops_id;    //店铺ID
    private String name;            //联系人
    private String phone;           //联系手机号
    private String refund_reasons;      //退款理由
    private int status;                 //状态码，默认2：审核中，1：通过，0：拒绝
    private String refuse_reason;   //审核 拒绝退款理由
    private String created_time;
    private String updated_time;
}
