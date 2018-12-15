package com.hengmall.user.model.api;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.hengmall.user.model.constitute.ResourceCarousel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2018/5/25.
 */
@ApiModel
public class ProductInfoResp {

	@ApiModelProperty(value = "商品名称")
	String name;
	@ApiModelProperty(value = "商品图片或视频资源轮播")
	List<ResourceCarousel> resourceCarousels;
	@ApiModelProperty(value = "尺寸、颜色图片")
	JSONObject attr;
	@ApiModelProperty(value = "价格")
	double price;
	@ApiModelProperty(value = "库存")
	int stock;
	@ApiModelProperty(value = "是否支持退货；1：支持，0：不支持")
	int isAllowrefund;

	@Override
	public String toString() {
		return "ProductInfoResp{" + "name='" + name + '\'' + ", resourceCarousels=" + resourceCarousels + ", attr="
				+ attr + ", price=" + price + ", stock=" + stock + ", isAllowrefund=" + isAllowrefund + '}';
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ResourceCarousel> getResourceCarousels() {
		return resourceCarousels;
	}

	public void setResourceCarousels(List<ResourceCarousel> resourceCarousels) {
		this.resourceCarousels = resourceCarousels;
	}

	public JSONObject getAttr() {
		return attr;
	}

	public void setAttr(JSONObject attr) {
		this.attr = attr;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getIsAllowrefund() {
		return isAllowrefund;
	}

	public void setIsAllowrefund(int isAllowrefund) {
		this.isAllowrefund = isAllowrefund;
	}
}
