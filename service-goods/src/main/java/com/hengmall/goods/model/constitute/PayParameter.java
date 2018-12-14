package com.hengmall.goods.model.constitute;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Administrator on 2018/5/30.
 */
@Data
@ApiModel
public class PayParameter {

    @ApiModelProperty(value = "购买商品ID")
    int productId;
    @ApiModelProperty(value = "购买数量")
    int num;
    @ApiModelProperty(value = "商品SKU索引ID")
    int skuIndex;
    @ApiModelProperty(value = "优惠券ID")
    String couponId;
    @ApiModelProperty(value = "店铺ID")
    int shopsId;
    @ApiModelProperty(value = "拼单发起人ID")
    int initiatorId;

}
