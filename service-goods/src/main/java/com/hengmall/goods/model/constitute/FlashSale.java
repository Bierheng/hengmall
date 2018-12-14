package com.hengmall.goods.model.constitute;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/5/30.
 */
@Data
@ApiModel
public class FlashSale  implements Serializable {

    @ApiModelProperty(value = "flash_sale主键ID用于后台交互")
    private int flashSaleId;
    @ApiModelProperty(value = "限时商品ID")
    private int productId;
    @ApiModelProperty(value = "限时购买价格")
    private double price;
    @ApiModelProperty(value = "原价")
    private double originalPrice;
    @ApiModelProperty(value = "限时抢购标题")
    private String title;
    @ApiModelProperty(value = "限时抢购标题")
    private String icon;
    @ApiModelProperty(value = "限时抢购状态码；1：未开始，2：已开始，0：无效")
    private int status;

    @ApiModelProperty(value = "抢购状态码；0：未抢购，1：已抢购")
    private int buyStatus;
}
