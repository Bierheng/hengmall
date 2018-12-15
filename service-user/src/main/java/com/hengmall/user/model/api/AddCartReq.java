package com.hengmall.user.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2018/5/25.
 */
@ApiModel
public class AddCartReq extends ApiRequest {

	@ApiModelProperty(value = "商品id")
	int productid;
	@ApiModelProperty(value = "选择的商品尺码大小")
	int size;
	@ApiModelProperty(value = "选择的商品颜色，（颜色图片）")
	String color;
	@ApiModelProperty(value = "选择的数量")
	int sum;

	@Override
	public String toString() {
		return "AddCartReq{" + "productid=" + productid + ", size=" + size + ", color='" + color + '\'' + ", sum=" + sum
				+ '}' + "，" + super.toString();
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}
}
