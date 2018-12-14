package com.hengmall.goods.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Administrator on 2018/5/25.
 */

@Data
@ApiModel
public class WalletDetail{

    @ApiModelProperty(value = "收支金额")
    double money;
    @ApiModelProperty(value = "收支状态；默认1：收入，2：支出")
    int status;
    @ApiModelProperty(value = "余额")
    double remainder;
    @ApiModelProperty(value = "收入或支出时间")
    String createTime;

}
