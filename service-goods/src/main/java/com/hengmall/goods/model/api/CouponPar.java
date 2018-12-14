package com.hengmall.goods.model.api;

import java.util.List;

import com.hengmall.goods.model.constitute.PayParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class CouponPar {
    @ApiModelProperty(value = "token认证")
    String token;
    @ApiModelProperty(value = "下单所需参数")
    List<PayParameter> payParameters;
}
