package com.hengmall.goods.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Administrator on 2018/5/25.
 */
@Data
@ApiModel
public class ProductInfoReq {

    @ApiModelProperty(value = "token认证")
    String token;
    @ApiModelProperty(value = "商品ID")
    int id;
    @ApiModelProperty(value = "业务状态码：0：普通商品信息，1：限时抢购商品信息，2：拼单商品信息")
    int status;
    @ApiModelProperty(value = "status为1和2时：flashSaleId为必传，否则为0")
    int flashSaleId;
}
