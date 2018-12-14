package com.hengmall.goods.model.api;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Administrator on 2018/5/25.
 */
@Data
@ApiModel
public class ProductListResp implements Serializable {

    @ApiModelProperty(value = "店铺商品主键id")// 此处在原来的设计里面是商品ID，在二期项目中由于商品库的设计以及店铺的存在，所有商品的上架都应该是通过店铺来实现
    int id;
    @ApiModelProperty(value = "商品名称")
    String name;
    @ApiModelProperty(value = "商品图片")
    String headimg;
    @ApiModelProperty(value = "价格")
    double price;
}
