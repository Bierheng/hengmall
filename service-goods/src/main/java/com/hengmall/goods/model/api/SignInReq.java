package com.hengmall.goods.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class SignInReq {

    @ApiModelProperty("code")
    private String code;
    @ApiModelProperty("用户登录信息")
    private UserInfo userInfo;

}
