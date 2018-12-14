package com.server.entity.shops;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 店铺轮播图列表 entity
 * @author Administrator
 *
 */
public class CarouselShopsListResponse{
	
	@ApiModelProperty(value = "轮播图ID")
	private String id;
	
	@ApiModelProperty(value = "商品id")
	private String productId;
	
	@ApiModelProperty(value = "商品名称")
	private String productName;
	
	@ApiModelProperty(value = "轮播图创建时间")
	private Date createdTime;
	
	@ApiModelProperty(value = "轮播图图片")
	private String path;
	
	@ApiModelProperty(value = "店铺id")
	private String shopsId;
	
	@ApiModelProperty(value = "店铺名")
	private String shopsName;

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

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getShopsName() {
		return shopsName;
	}

	public void setShopsName(String shopsName) {
		this.shopsName = shopsName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getShopsId() {
		return shopsId;
	}

	public void setShopsId(String shopsId) {
		this.shopsId = shopsId;
	}
	
	
}