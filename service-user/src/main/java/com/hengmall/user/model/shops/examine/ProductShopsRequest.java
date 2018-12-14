package com.server.entity.shops.examine;

import io.swagger.annotations.ApiModelProperty;

/**
 * 店铺选择商品申请表单  response
 * @author Administrator
 *
 */
public class ProductShopsRequest {

	@ApiModelProperty(value = "店铺id")
	private String shopsId;
	
	@ApiModelProperty(value = "店铺名",required=false,hidden=true)
	private String shopsName;

	@ApiModelProperty(value = "商品们")
	private String[] productIds;

	public String getShopsId() {
		return shopsId;
	}

	public void setShopsId(String shopsId) {
		this.shopsId = shopsId;
	}

	public String[] getProductIds() {
		return productIds;
	}

	public void setProductIds(String[] productIds) {
		this.productIds = productIds;
	}

	public String getShopsName() {
		return shopsName;
	}

	public void setShopsName(String shopsName) {
		this.shopsName = shopsName;
	}

}
