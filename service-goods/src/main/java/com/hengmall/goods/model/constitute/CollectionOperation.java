package com.hengmall.goods.model.constitute;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


@Data
@ApiModel
public class CollectionOperation {

    @ApiModelProperty(value = "token认证")
    String token;
    @ApiModelProperty(value = "商品ID")
    List<Integer> ids;
    @ApiModelProperty(value = "收藏状态码；0：取消收藏，1：收藏")
    int status;

}
