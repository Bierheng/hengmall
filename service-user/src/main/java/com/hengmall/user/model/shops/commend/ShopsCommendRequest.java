package com.hengmall.user.model.shops.commend;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;

/**
 * 店铺组合推荐  实体
 * @author Administrator
 *
 */
public class ShopsCommendRequest {

	@NotEmpty(message="必须选择店铺")
	@ApiModelProperty(value="店铺id")
	private String shopsId;
	
	@NotEmpty(message="组合名称不能为空")
	@ApiModelProperty(value="组合名称")
	private String groupName;
	
	@ApiModelProperty(value="组合总特价")
	private Double groupPrice;
	
	@NotEmpty(message="最少要选择一件商品")
	@ApiModelProperty(value="组合商品列表")
	private String[] productIds;
	
	@ApiModelProperty(value="店铺名",required=false,hidden=true)
	private String shopsName;

	public String getShopsId() {
		return shopsId;
	}

	public void setShopsId(String shopsId) {
		this.shopsId = shopsId;
	}

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
