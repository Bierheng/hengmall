package com.hengmall.goods.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Administrator on 2018/5/25.
 */
@Data
@ApiModel
public class CouponReq {

    @ApiModelProperty(value = "token认证")
    String token;
    @ApiModelProperty(value = "优惠券标识；0：未使用，1：已使用，2：已过期；4：全部")
    int status;

}
