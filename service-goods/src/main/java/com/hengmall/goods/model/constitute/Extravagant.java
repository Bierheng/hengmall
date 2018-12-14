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
public class Extravagant  implements Serializable {

    @ApiModelProperty(value = "图片轮播")
    private List<Carousel> carousels;
    @ApiModelProperty(value = "优惠券领取")
    private List<Coupon> coupons;
    @ApiModelProperty(value = "活动商品")
    private List<Ads> ads;
    @ApiModelProperty(value = "品牌店")
    private List<Brands> brands;

}
