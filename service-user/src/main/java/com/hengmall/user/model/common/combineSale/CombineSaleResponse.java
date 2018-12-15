package com.hengmall.user.model.common.combineSale;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 拼单商品列表  response
 * @author Administrator
 *
 */
public class CombineSaleResponse {

	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty(value = "标题（一）")
	private String title;
	
	@ApiModelProperty(value = "价格（二）")
	private Double price;
	
	@ApiModelProperty(value = "拼单icon（三）")
	private String icon;
	
	@ApiModelProperty(value = "拼单所需人数（四）")
	private Integer combineNum;
	
	@ApiModelProperty(value = "还需要的拼单人数（五）")
	private Integer stillNeed;
	
	@ApiModelProperty(value = "有效状态；默认1：未开始，2：已开始，0：已结束（六）")
	private Integer status;
	
	@ApiModelProperty(value = "创建时间（七）")
	private Date createdTime;
	
	@ApiModelProperty(value = "更新时间（八）")
	private Date updatedTime;
	
	@ApiModelProperty(value = "商品ID")
	private String productId;
	
	@ApiModelProperty(value = "商品name")
	private String productName;
	
	@ApiModelProperty(value = "店铺ID")
	private String shopsId;
	
	@ApiModelProperty(value = "店铺name")
	private String shopsName;
	
	@ApiModelProperty(value = "商品列表")
	private List<Select> productList;
	
	@ApiModelProperty(value = "店铺列表")
	private List<Select> shopsList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getPrice() {
		return price/100;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getCombineNum() {
		return combineNum;
	}

	public void setCombineNum(Integer combineNum) {
		this.combineNum = combineNum;
	}

	public Integer getStillNeed() {
		return stillNeed;
	}

	public void setStillNeed(Integer stillNeed) {
		this.stillNeed = stillNeed;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
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

	public List<Select> getProductList() {
		return productList;
	}

	public void setProductList(List<Select> productList) {
		this.productList = productList;
	}

	public List<Select> getShopsList() {
		return shopsList;
	}

	public void setShopsList(List<Select> shopsList) {
		this.shopsList = shopsList;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getShopsName() {
		return shopsName;
	}

	public void setShopsName(String shopsName) {
		this.shopsName = shopsName;
	}
	
	
}
