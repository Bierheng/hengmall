package com.hengmall.goods.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PlatformPlateEntity {
	
	@ApiModelProperty(value = "平台商品板块ID")
    private Integer id;
	@ApiModelProperty(value = "板块类型，1：文字，2：图片")
    private Integer type;
	@ApiModelProperty(value = "内容")
    private String content;
	@ApiModelProperty(value = "序号")
    private Integer order_num;
	@ApiModelProperty(value = "所属平台一级分类ID")
    private Integer type_id;
	@ApiModelProperty(value = "店铺ID")
    private List<ProductReq> plateProductList;
	@ApiModelProperty(value = "店铺ID")
    private Integer shops_id;
	@ApiModelProperty(value = "模板商品ID数组")
    private String platform_ids;
}