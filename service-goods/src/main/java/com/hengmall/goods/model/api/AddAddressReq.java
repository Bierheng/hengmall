package com.hengmall.goods.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Administrator on 2018/5/25.
 */
@Data
@ApiModel
public class AddAddressReq {

    @ApiModelProperty(value = "token认证")
    String token;
    @ApiModelProperty(value = "收获人姓名")
    String uname;
    @ApiModelProperty(value = "收货人手机号码")
    String phone;
    @ApiModelProperty(value = "邮政编码")
    int zip_code;
    @ApiModelProperty(value = "省")
    String province;
    @ApiModelProperty(value = "市")
    String city;
    @ApiModelProperty(value = "区")
    String area;
    @ApiModelProperty(value = "街道")
    String street;
    @ApiModelProperty(value = "详细地址")
    String address;
}
