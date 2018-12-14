package com.server.entity.shops;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;


/**
 * 店铺板块商品移除 entity  请求需要的参数
 * @author Administrator
 *
 */
public class ShopsPlateproductDelRequest{
	
	@ApiModelProperty(value = "移除板块商品需要用到的id",required=true)
	@NotEmpty(message="id不能为空")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}