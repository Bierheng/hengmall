package com.hengmall.goods.model.api;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 一键生成供应商 请求体
 */
@Data
@ApiModel
public class GenerateSuppliersReq implements Serializable {

    @ApiModelProperty(value = "token认证")
    private String token;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "验证码")
    private String code;
}
