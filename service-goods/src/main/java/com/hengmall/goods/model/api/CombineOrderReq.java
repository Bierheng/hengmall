package com.hengmall.goods.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


@Data
@ApiModel
public class CombineOrderReq {

    @ApiModelProperty(value = "token认证")
    String token;
    @ApiModelProperty(value = "商品主键ID")
    Integer productId;
}
