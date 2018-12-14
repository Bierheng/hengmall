package com.hengmall.goods.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Administrator on 2018/5/25.
 */
@Data
@ApiModel
public class ProductDetail {

    @ApiModelProperty(value = "token认证")
    String token;
    @ApiModelProperty(value = "商品ID")
    int id;
}
