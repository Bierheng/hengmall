package com.hengmall.goods.model.constitute;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Administrator on 2018/5/30.
 */
@Data
@ApiModel
public class OrderDetailsList {

    @ApiModelProperty(value = "商品名称")
    private String productName;
    @ApiModelProperty(value = "商品图片")
    private String headimg;
    @ApiModelProperty(value = "SKU")
    private JSONObject attrs;
    @ApiModelProperty(value = "价格")
    private double price;
    @ApiModelProperty(value = "购买数量")
    private int num;
    @ApiModelProperty(value = "商品ID")
    private int product_id;
    @ApiModelProperty(value = "购买时间")
    private String purchaseTime;
    @ApiModelProperty(value = "支付时间")
    private String payTime;
    @ApiModelProperty(value = "购买状态码 -3逻辑删除,-2已退款,-1已取消,0-待支付,1-已支付")
    private int buy_status;
    @ApiModelProperty(value = "收货状态码 0：待发货，1：待收货，2：已收货")
    private int delivery_status;

}
