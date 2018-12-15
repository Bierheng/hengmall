package com.hengmall.user.model.shops;

import com.hengmall.user.model.persistence.DictMessage;

import io.swagger.annotations.ApiModelProperty;

/**
 * 店铺板块商品 entity
 * @author Administrator
 *
 */
public class ShopsPlateproductResponse{
	
	@ApiModelProperty(value = "移除板块商品需要用到的id")
	private String id;    //id
	
	@ApiModelProperty(value = "商品名称")
    private String productName;
	
	@ApiModelProperty(value = "商品图")
	private String headimg;
	
	@ApiModelProperty(value = "价格")
	private Double price;
	
	@ApiModelProperty(value = "库存")
	private String stock;

	@ApiModelProperty(value = "1上架，0下架")
	private String status;
	
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

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public Double getPrice() {
		return price / 100d;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getStatus() {
		return DictMessage.getDictLabel("product_status", status, "");
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getShopsName() {
		return shopsName;
	}

	public void setShopsName(String shopsName) {
		this.shopsName = shopsName;
	}

}