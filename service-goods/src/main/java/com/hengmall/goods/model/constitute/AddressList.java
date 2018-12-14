package com.hengmall.goods.model.constitute;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Administrator on 2018/5/30.
 */
@Data
@ApiModel
public class AddressList {

    @ApiModelProperty(value = "主键ID，用于后台交互")
    private int id;
    @ApiModelProperty(value = "收获人姓名")
    private String uname;
    @ApiModelProperty(value = "收货人手机号码")
    private String phone;
    @ApiModelProperty(value = "邮政编码")
    private int zip_code;
    @ApiModelProperty(value = "省")
    private String province;
    @ApiModelProperty(value = "市")
    private String city;
    @ApiModelProperty(value = "区")
    private String area;
    @ApiModelProperty(value = "街道")
    private String street;
    @ApiModelProperty(value = "详细地址")
    private String address;

}
