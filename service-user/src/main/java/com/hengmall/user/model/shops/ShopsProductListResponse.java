package com.server.entity.shops;

import java.util.Date;

import com.server.entity.persistence.DictMessage;

import io.swagger.annotations.ApiModelProperty;

/**
 * 获取商品列表 entity 的返回参数
 * @author Administrator
 *
 */
public class ShopsProductListResponse{
	
	@ApiModelProperty(value = "商品ID")
	private String id; 
	
	@ApiModelProperty(value = "商品名称")
	private String name;  
	
	@ApiModelProperty(value = "商品展示图片，用于展示在商品列表上的图片")
	private String headimg;  
	
	@ApiModelProperty(value = "尺寸大小、颜色")
	private String attribute;  
	
	@ApiModelProperty(value = "价格")
	private String price;  
	
	@ApiModelProperty(value = "库存")
	private String stock;  
	
	@ApiModelProperty(value = "1上架，0下架")
	private String status;  
	
	@ApiModelProperty(value = "发布时间")
	private Date ctime;  
	
	@ApiModelProperty(value = "商品描述")
	private String description;

	@ApiModelProperty(value = " 店铺商品表id ")
	private String tbId;
	
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

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
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

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTbId() {
		return tbId;
	}

	public void setTbId(String tbId) {
		this.tbId = tbId;
	} 

}