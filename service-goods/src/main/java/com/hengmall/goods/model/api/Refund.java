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
public class Refund implements Serializable {

    @ApiModelProperty(value = "token认证")
    private String token;
    @ApiModelProperty(value = "订单ID")
    private int  order_id;
    @ApiModelProperty(value = "店铺ID")
    private int  shops_id;
}
