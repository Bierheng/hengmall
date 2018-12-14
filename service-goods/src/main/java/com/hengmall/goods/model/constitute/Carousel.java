package com.hengmall.goods.model.constitute;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 轮播图模块
 */
@Data
@ApiModel
public class Carousel implements Serializable {

    @ApiModelProperty(value = "商品关联ID")
    int productId;
    @ApiModelProperty(value = "图片地址")
    String path;
}
