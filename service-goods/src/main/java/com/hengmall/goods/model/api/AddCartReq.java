package com.hengmall.goods.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Administrator on 2018/5/25.
 */
@Data
@ApiModel
public class AddCartReq extends ApiRequest{

    @ApiModelProperty(value = "商品id")
    int productId;
    @ApiModelProperty(value = "SKU索引ID")
    int skuIndex;
    @ApiModelProperty(value = "选择的数量")
    int sum;
    @ApiModelProperty(value = "店铺id")
    int shopsId;
    @ApiModelProperty(value = "价格类型，1：人民币，2：美元；")
    int priceType;

}
