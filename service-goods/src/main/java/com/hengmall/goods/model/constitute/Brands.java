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
public class Brands implements Serializable{

    @ApiModelProperty(value = "商品ID")
    private String name;
    @ApiModelProperty(value = "资源地址")
    private String icon;
    @ApiModelProperty(value = "类型，1：图片，2：视频")
    private String desc;

}
