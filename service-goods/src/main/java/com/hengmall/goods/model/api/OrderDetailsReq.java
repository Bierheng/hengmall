package com.hengmall.goods.model.api;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 订单详情 请求体
 */
@Data
@ApiModel
public class OrderDetailsReq implements Serializable {

    @ApiModelProperty(value = "token认证")
    private String token;
    @ApiModelProperty(value = "订单号")
    private String outTradeNo;
    @ApiModelProperty(value = "订单号")
    private int shops_id;

}
