package com.hengmall.goods.model.constitute;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Administrator on 2018/5/30.
 */
@Data
@ApiModel
public class OrderDetails {

    @ApiModelProperty(value = "商品名称")
    private String product_name;
    @ApiModelProperty(value = "SKU")
    private String attrs;
    @ApiModelProperty(value = "价格")
    private double price;
    @ApiModelProperty(value = "购买数量")
    private int num;
    @ApiModelProperty(value = "购买时间")
    private String purchase_time;
    @ApiModelProperty(value = "支付时间")
    private String pay_time;
    @ApiModelProperty(value = "购买状态码")
    private int buy_status;
    @ApiModelProperty(value = "收货状态码")
    private int delivery_status;
    @ApiModelProperty(value = "用户名")
    private String uname;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "省")
    private String province;
    @ApiModelProperty(value = "城市")
    private String city;
    @ApiModelProperty(value = "地区")
    private String area;
    @ApiModelProperty(value = "详细地址")
    private String address;
    @ApiModelProperty(value = "店铺ID")
    private int shops_id;
    @ApiModelProperty(value = "商品图片")
    private String headimg;
    @ApiModelProperty(value = "交易流水号")
    private String pay_no;
    @ApiModelProperty(value = "商品ID")
    private int product_id;

}
