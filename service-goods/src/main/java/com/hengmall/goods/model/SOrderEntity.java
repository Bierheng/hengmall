package com.hengmall.goods.model;

import lombok.Data;

/**
 * 订单 实体类
 */
@Data
public class SOrderEntity {

    //status状态码-3:逻辑删除,-2:已退款,-1:已取消,0:待支付,1:已支付
    public static final int DEL_STATUS = -3;
    public static final int RETURN_STATUS = -2;
    public static final int CANCEL_STATUS = -1;
    public static final int PENDING_STATUS = 0;
    public static final int ALREADY_STATUS = 1;

    //delivery_status 状态码0：待发货，1：待收货，2：已收货
    public static final int PENDING_DELIVERY_STATUS = 1;
    public static final int UNCOLLECTED_GOODS_STATUS = 2;
    public static final int RECEIVED_STATUS = 3;


    private int id;         //订单id
    private String out_trade_no;        //微信支付订单号
    private String trade_no;        //商城订单号
    private int user_id;         //用户id
    private int price;      //支付金额
    private int paymethod;      //支付方式
    private int status;         //-3逻辑删除,-2已退款,-1已取消,0-待支付,1-已支付
    private int delivery_status;         //0：待发货，1：待收货，2：已收货
    private String created_time;          //订单创建时间
    private String pay_time;        //支付时间
    private String refund_time;     //退款时间
    private int address_id;        //购买时选择的收获人详细地址
    private int buy_status;        //
    private int initiator_id;  	   //拼单创建人ID
    private int combine_sale_id;     //拼单商品ID

}
