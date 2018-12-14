package com.server.entity.platform;

import io.swagger.annotations.ApiModelProperty;

public class PlatformProduct {

	@ApiModelProperty(value = "商品id")
	private String id;
	
	@ApiModelProperty(value = "商品名")
	private String productName;
	
	@ApiModelProperty(value = "商品图")
	private String headimg;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getHeadimg() {
		return headimg;
	}
	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
	
	
}
