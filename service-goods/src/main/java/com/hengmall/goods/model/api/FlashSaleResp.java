package com.hengmall.goods.model.api;

import java.util.List;

import com.hengmall.goods.model.constitute.FlashSale;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Administrator on 2018/5/25.
 */
@Data
@ApiModel
public class FlashSaleResp {

    @ApiModelProperty(value = "flash_sale_timeliness主键ID用于后台交互")
    int id;
    @ApiModelProperty(value = "活动图片")
    String img;
    @ApiModelProperty(value = "描述文字")
    String desc;
    @ApiModelProperty(value = "抢购开始时间")
    long startTime;
    @ApiModelProperty(value = "抢购结束时间")
    long endTime;
    @ApiModelProperty(value = "抢购商品参数")
    List<FlashSale> flashSales;

}
