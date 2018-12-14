package com.server.entity.common;

import io.swagger.annotations.ApiModelProperty;

/**
 * 获取商品列表 res
 * @author Administrator
 *
 */
public class ProductResponse{

	@ApiModelProperty(value = "商品ID")
	private String id;
	
	@ApiModelProperty(value = "商品名称")
	private String name;
	
	@ApiModelProperty(value = "商品图片")
	private String headimg;
	
	@ApiModelProperty(value = "商品价格")
	private Double price;
	
	@ApiModelProperty(value = "商品库存")
	private Integer stock;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = (double) (price / 100);
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	
}
