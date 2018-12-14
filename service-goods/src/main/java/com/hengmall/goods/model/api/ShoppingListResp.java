package com.hengmall.goods.model.api;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Administrator on 2018/5/25.
 */
@Data
@ApiModel
public class ShoppingListResp {

    @ApiModelProperty(value = "商品ID")
    int productId;
    @ApiModelProperty(value = "购物车列表主键ID")
    int id;
    @ApiModelProperty(value = "商品名称")
    String name;
    @ApiModelProperty(value = "商品图标")
    String headimg;
    @ApiModelProperty(value = "SKU")
    JSONObject attr;
    @ApiModelProperty(value = "购买数量")
    int sum;
    @ApiModelProperty(value = "原价")
    double price;

}
