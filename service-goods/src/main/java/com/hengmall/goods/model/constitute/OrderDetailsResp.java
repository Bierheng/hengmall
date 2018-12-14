package com.hengmall.goods.model.constitute;

import java.util.List;

import com.hengmall.goods.model.ShopsLocation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Administrator on 2018/5/30.
 */
@Data
@ApiModel
public class OrderDetailsResp {

    @ApiModelProperty(value = "购买的商品信息")
    private List<ShopsLocation> shopsOrderDetailsList;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "详细地址")
    private String address;
    @ApiModelProperty(value = "描述")
    private String desc;

}
