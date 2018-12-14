package com.server.entity.manage.product;

import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2018/5/25.
 */
@ApiModel
public class UploadProductReq {

	@ApiModelProperty(value = "token认证")
	String token;

	@ApiModelProperty(value = "商品名称")
	private String name;
	@ApiModelProperty(value = "商品展示图片")
	private String headimg;
	@ApiModelProperty(value = "价格")
	private double price;
	@ApiModelProperty(value = "库存")
	private int stock;
	@ApiModelProperty(value = "是否允许退款，1：允许，0：不允许；默认不允许")
	private int allowrefund;
	@ApiModelProperty(value = "尺寸大小、颜色")
	private JSONObject attribute;

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("UploadProductReq{");
		buffer.append("name='").append(name).append("'");
		buffer.append("headimg='").append(headimg).append("'");
		buffer.append("price='").append(price).append("'");
		buffer.append("stock='").append(stock).append("'");
		buffer.append("allowrefund='").append(allowrefund).append("'");
		buffer.append("attribute='").append(attribute).append("'");
		buffer.append("}");
		return buffer.toString();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public int getAllowrefund() {
		return allowrefund;
	}

	public void setAllowrefund(int allowrefund) {
		this.allowrefund = allowrefund;
	}

	public JSONObject getAttribute() {
		return attribute;
	}

	public void setAttribute(JSONObject attribute) {
		this.attribute = attribute;
	}
}
