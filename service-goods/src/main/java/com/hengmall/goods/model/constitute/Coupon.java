package com.hengmall.goods.model.constitute;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/5/30
 */
@Data
@ApiModel
public class Coupon implements Serializable {

    @ApiModelProperty(value = "《rel_coupon》主键ID，用于后台交互")
    private int id;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "需要满足的条件（单文：元）")
    private double prerequisite;
    @ApiModelProperty(value = "优惠券面值（单位：元）")
    private double price;
    @ApiModelProperty(value = "优惠券icon")
    private String img;
    @ApiModelProperty(value = "有效开始时间")
    private String start_time;
    @ApiModelProperty(value = "有效结束时间")
    private String end_time;
    @ApiModelProperty(value = "优惠券所属店铺主键ID")
    private int shops_id;
}
