package com.hengmall.user.model.shops.commend;

import java.util.List;

import com.hengmall.user.model.common.ProductResponse;

import io.swagger.annotations.ApiModelProperty;

/**
 * 店铺组合推荐  实体
 * @author Administrator
 *
 */
public class ShopsCommendResponse {

	@ApiModelProperty(value="组合名称")
	private String groupName;
	
	@ApiModelProperty(value="组合总特价")
	private Double groupPrice;
	
	@ApiModelProperty(value="组合商品列表")
	private List<ProductResponse> products;
	
	@ApiModelProperty(value="店铺名")
	private String shopsName;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Double getGroupPrice() {
		return groupPrice;
	}

	public void setGroupPrice(Double groupPrice) {
		this.groupPrice = groupPrice * 100;
	}

	public List<ProductResponse> getProducts() {
		return products;
	}

	public void setProducts(List<ProductResponse> products) {
		this.products = products;
	}

	public String getShopsName() {
		return shopsName;
	}

	public void setShopsName(String shopsName) {
		this.shopsName = shopsName;
	}
	
	
}
