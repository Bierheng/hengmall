package com.hengmall.user.model;

import io.swagger.annotations.ApiModelProperty;

public class SkuReq2 {
	
//	price: 100, stock: 200, coding: "1000", one: "测试1", index: 0}
	@ApiModelProperty(value = "SKU价格")
    private Integer price;
	@ApiModelProperty(value = "SKU库存")
    private Integer stock;
	@ApiModelProperty(value = "SKU编码")
    private String coding;
	@ApiModelProperty(value = "SKU属性值")
    private String one;
	@ApiModelProperty(value = "序号")
    private Integer index;
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public String getCoding() {
		return coding;
	}
	public void setCoding(String coding) {
		this.coding = coding;
	}
	public String getOne() {
		return one;
	}
	public void setOne(String one) {
		this.one = one;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
}