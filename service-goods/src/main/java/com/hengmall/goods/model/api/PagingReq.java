package com.hengmall.goods.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class PagingReq {

    @ApiModelProperty("token认证")
    private String token;
    @ApiModelProperty(value = "当前页数")
    protected int page;
    @ApiModelProperty(value = "每页大小")
    protected int limit;
}
