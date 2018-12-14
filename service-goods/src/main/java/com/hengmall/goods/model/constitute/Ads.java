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
public class Ads implements Serializable {

    @ApiModelProperty(value = "商品ID")
    private int productId;
    @ApiModelProperty(value = "资源地址")
    private String resource;
    @ApiModelProperty(value = "店铺ID")
    private int shops_id;
    @ApiModelProperty(value = "类型，1：图片，2：视频")
    private double type;
}
