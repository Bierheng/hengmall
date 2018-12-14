package com.hengmall.goods.model.api;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 立即购买请求体
 */
@Data
@ApiModel
public class PursePay implements Serializable{

    @ApiModelProperty(value = "token认证")
    private String token;
    @ApiModelProperty(value = "订单号")
    private String outTradeNo;
    @ApiModelProperty(value = "店铺ID")
    private int shops_id ;
    @ApiModelProperty(value = "优惠券抵扣")
    private int allCoupon ;
    @ApiModelProperty(value = "支付金额")
    private String total_fee ;

}
