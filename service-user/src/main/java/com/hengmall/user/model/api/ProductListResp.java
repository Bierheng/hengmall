package com.server.entity.api;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2018/5/25.
 */
@ApiModel
public class ProductListResp {

	@ApiModelProperty(value = "商品主键id")
	int id;
	@ApiModelProperty(value = "商品名称")
	String name;
	@ApiModelProperty(value = "商品图片")
	String headimg;
	@ApiModelProperty(value = "价格")
	double price;

	@Override
	public String toString() {
		return "ProductListResp{" + "id=" + id + ", name='" + name + '\'' + ", headimg='" + headimg + '\'' + ", price="
				+ price + '}';
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
