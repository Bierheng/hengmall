package com.server.entity.shops;

import io.swagger.annotations.ApiModelProperty;


/**
 * 店铺板块移除 entity  请求需要的参数
 * @author Administrator
 *
 */
public class ShopsPlateDelRequest{
	
	@ApiModelProperty(value = "店铺板块id")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
}