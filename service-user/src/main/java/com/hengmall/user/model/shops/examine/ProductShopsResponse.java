package com.server.entity.shops.examine;

import java.util.List;

import com.server.entity.common.ProductResponse;

import io.swagger.annotations.ApiModelProperty;

/**
 * 店铺选择商品申请表单  response
 * @author Administrator
 *
 */
public class ProductShopsResponse {

	@ApiModelProperty(value = "店铺名")
	private String shopsName;

	@ApiModelProperty(value = "店铺选择商品详情表单")
	private List<ProductResponse> products;

	public String getShopsName() {
		return shopsName;
	}

	public void setShopsName(String shopsName) {
		this.shopsName = shopsName;
	}

	public List<ProductResponse> getProducts() {
		return products;
	}

	public void setProducts(List<ProductResponse> products) {
		this.products = products;
	}
	
	
}
