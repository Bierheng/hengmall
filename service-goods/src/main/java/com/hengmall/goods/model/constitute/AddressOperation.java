package com.hengmall.goods.model.constitute;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Administrator on 2018/5/30.
 */
@Data
@ApiModel
public class AddressOperation {

    @ApiModelProperty(value = "token")
    private String token;
    @ApiModelProperty(value = "状态码：0：新增，1：修改")
    private int status;

    @ApiModelProperty(value = "收货地址主键id；无id时为 0 即可")
    int id;
    @ApiModelProperty(value = "收货人姓名")
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
    @ApiModelProperty(value = "详细地址")
    String address;

}
