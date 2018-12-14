package com.hengmall.goods.model.api;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 退款 请求体
 */
@Data
@ApiModel
public class RefundReq implements Serializable{

    @ApiModelProperty(value = "token认证")
    private String token;
    @ApiModelProperty(value = "订单号")
    private String outTradeNo;
    @ApiModelProperty(value = "店铺ID")
    private int shops_id;
    @ApiModelProperty(value = "退款联系人")
    private String name;
    @ApiModelProperty(value = "联系手机号")
    private String phone;
    @ApiModelProperty(value = "退款原因")
    private String refundReasons;
    @ApiModelProperty(value = "店铺ID")
    private int order_id;

}
