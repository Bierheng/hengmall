package com.server.entity.shops.examine.operation;

import com.server.entity.basics.BasicsSaveBean;

import io.swagger.annotations.ApiModelProperty;

/**
 * 店铺首页产品图组合审核操作    entity
 * @author Administrator
 *
 */
public class ShopsCommendEntity extends BasicsSaveBean{

	@ApiModelProperty(value = "店铺id")
	private String shopsId;
	
	@ApiModelProperty(value = "状态，0：禁用，1：启用")
	private Integer status;
	
	@ApiModelProperty(value = "组合名称")
	private String groupName;
	
	@ApiModelProperty(value = "组合总特价")
	private Integer groupPrice;
	
	@ApiModelProperty(value = "组合商品列表")
	private String productIds;

	public String getShopsId() {
		return shopsId;
	}

	public void setShopsId(String shopsId) {
		this.shopsId = shopsId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getGroupPrice() {
		return groupPrice;
	}

	public void setGroupPrice(Integer groupPrice) {
		this.groupPrice = groupPrice;
	}

	public String getProductIds() {
		return productIds;
	}

	public void setProductIds(String productIds) {
		this.productIds = productIds;
	}
	
	
}
