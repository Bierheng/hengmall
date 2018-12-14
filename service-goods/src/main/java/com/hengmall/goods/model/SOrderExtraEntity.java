package com.hengmall.goods.model;

import lombok.Data;

import java.util.Date;

/**
 * 订单关联 实体类
 */
@Data
public class SOrderExtraEntity {

    //0:待付款，1:待发货，2:待收货，3:已收货，99:所有订单
    public static final int PENDING_PAYMENT_STATUS = 0;
    public static final int PENDING_DELIVERY_STATUS = 1;
    public static final int UNCOLLECTED_GOODS_STATUS = 2;
    public static final int RECEIVED_STATUS = 3;
    public static final int ALL_ORDERS = 99;

    //评价状态码：评价状态；默认0：订单未完成，无需评价，1：未评价，2：已评价，3：追加评价


    private int id;
    private int orderid;        //订单号表id
    private int productid;      //商品id
    private String productname;     //商品名称，当时记录的商品名称
    private double price;       //应付金额，当时记录的商品价格,单位为分
    private int num;        //购买数量
    private int status;     //1:待发货，2:待收货，3:已收货，
    private String attrs;     //商品SKU
    private int paymethod;        // 支付方式
    private int shops_id;        // 店铺ID
    private int order_id;        // 订单号表id
    private int product_id;		 //商品id
    private int user_id;		 //用户ID
    private int sku_index;		 //用户ID

    /**
     -- 待付款 s_order.`status`=0    s_order.delivery_status=0
     -- 待发货 s_order.`status`=1  	s_order.delivery_status=0
     -- 待收货 s_order.`status`=1  	s_order.delivery_status=1
     -- 已收货 s_order.`status`=1  	s_order.delivery_status=2
     */
}
