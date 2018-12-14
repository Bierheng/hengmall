package com.hengmall.goods.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PlatformPlateProduct {
	
	@ApiModelProperty(value = "平台板块商品ID")
    private Integer id;
	@ApiModelProperty(value = "平台商品ID")
    private Integer product_id;
	@ApiModelProperty(value = "板块ID")
    private Integer platform_plate_id;
}