package com.hengmall.goods.model.constitute;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/5/30.
 */
@Data
@ApiModel
public class DutyFree implements Serializable {

    @ApiModelProperty(value = "国际馆")
    private List<SCategory> sCategories;
    @ApiModelProperty(value = "优惠券")
    private List<Coupon> coupons;
    @ApiModelProperty(value = "限时抢购商品")
    private List<FlashSale> flashSales;
    @ApiModelProperty(value = "活动商品")
    private List<Ads> ads;
    @ApiModelProperty(value = "品牌店")
    private List<Brands> brands;

}
