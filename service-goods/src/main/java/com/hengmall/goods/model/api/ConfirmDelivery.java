package com.hengmall.goods.model.api;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 确认收货
 */
@Data
@ApiModel
public class ConfirmDelivery implements Serializable {

    @ApiModelProperty(value = "token认证")
    private String token;
    @ApiModelProperty(value = "订单主键ID")
    private int orderId;
    @ApiModelProperty(value = "店铺主键ID")
    private int shops_id;
}
