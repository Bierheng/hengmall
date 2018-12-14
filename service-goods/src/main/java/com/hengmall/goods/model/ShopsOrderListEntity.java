package com.hengmall.goods.model;


import lombok.Data;

/**
 * 用于查询同一店铺下订单的工具
 */
@Data
public class ShopsOrderListEntity {

    private String out_trade_no;    //订单号
    private int shopsId;       //店铺ID
    private int order_id;       //订单ID
    private int total_price;       //订单总价。单位为：分
    private int status;       //订单状态。
    private int appraise_status;       //评论状态
    private int buy_status;       //业务支付状态码；默认1：单独购买（商品），2：发起拼单（商品），3：钱包充值 4：限时抢购 5:组合搭配购买
    private String pay_no;    //订单号
}
