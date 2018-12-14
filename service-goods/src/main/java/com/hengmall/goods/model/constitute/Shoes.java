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
public class Shoes implements Serializable {

    @ApiModelProperty(value = "轮播图")
    private List<Carousel> carousels;
    @ApiModelProperty(value = "优惠券")
    private List<Coupon> coupons;
    @ApiModelProperty(value = "热门品类")
    private List<SCategory> sCategories;
    @ApiModelProperty(value = "品牌店")
    private List<Brands> brands;
}
