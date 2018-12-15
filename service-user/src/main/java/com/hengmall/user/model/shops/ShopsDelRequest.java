package com.hengmall.user.model.shops;

import io.swagger.annotations.ApiModelProperty;


/**
 * 店铺商品移除 entity  请求需要的参数
 * @author Administrator
 *
 */
public class ShopsDelRequest{
	
	@ApiModelProperty(value = "店铺商品id")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
}