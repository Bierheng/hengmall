package com.hengmall.goods.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Administrator on 2018/5/25.
 */
@Data
@ApiModel
public class CombineDetailsReq {

    @ApiModelProperty(value = "token认证")
    String token;
    @ApiModelProperty(value = "发起拼单者用户ID")
    Integer userId;
    @ApiModelProperty(value = "拼单ID")
    Integer flashSaleId;
}
