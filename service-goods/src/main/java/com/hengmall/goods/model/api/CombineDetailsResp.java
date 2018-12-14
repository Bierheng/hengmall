package com.hengmall.goods.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Administrator on 2018/5/25.
 */
@Data
@ApiModel
public class CombineDetailsResp {

    @ApiModelProperty(value = "头像")
    String headImg;
    @ApiModelProperty(value = "拼单创建人ID")
    int initiatorId;
    @ApiModelProperty(value = "订单号")
    String out_trade_no;
    @ApiModelProperty(value = "开始时间")
    long startTime;
    @ApiModelProperty(value = "结束时间")
    long endTime;
}
