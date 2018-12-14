package com.hengmall.goods.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class ApiRequest implements Serializable{

    @ApiModelProperty("token认证")
    private String token;
    @ApiModelProperty("平台一级分类ID")
    private Integer typeIdOne;

}
