package com.hengmall.goods.model.api;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 获取手机验证码
 */
@Data
@ApiModel
public class GetVerificationCode implements Serializable{

    @ApiModelProperty(value = "token")
    private String token;
    @ApiModelProperty(value = "手机号")
    private String phone;

}
