package com.hengmall.goods.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


@Data
@ApiModel
public class CombineOrderList {

    @ApiModelProperty(value = "订单号")
    String outTradeNo;
    @ApiModelProperty(value = "还差拼成的人数")
    int stillNeedNum;
    @ApiModelProperty(value = "用户头像")
    String headImg;
    @ApiModelProperty(value = "用户昵称")
    String nickname;
    @ApiModelProperty(value = "拼者用户ID")
    int userId;
    @ApiModelProperty(value = "combine_sale 主键id")
    int combineSaleId;
    @ApiModelProperty(value = "开始时间")
    long startTime;
    @ApiModelProperty(value = "结束时间")
    long endTime;

}
