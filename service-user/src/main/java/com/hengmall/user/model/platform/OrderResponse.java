package com.hengmall.user.model.platform;

import io.swagger.annotations.ApiModelProperty;

/**
 * 定位地图购物信息弹框列表 entity
 * @author Administrator
 *
 */
public class OrderResponse{
	
	@ApiModelProperty(value = "定位地图购物信息弹框ID")
	private String id;
	
	@ApiModelProperty(value = "购买者用户名")
	private String userName;
	
	@ApiModelProperty(value = "购买位置")
	private String location;

	@ApiModelProperty(value = "商品名称")
	private String productName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
}