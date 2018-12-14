package com.server.entity.shops;

import com.server.entity.basics.BasicsBean;

import io.swagger.annotations.ApiModelProperty;


/**
 * 店铺 entity  请求需要的参数
 * @author Administrator
 *
 */
public class ShopsRequest extends BasicsBean{
	
	@ApiModelProperty(value = "店铺ID(为空找所有，否则找单条)")
	private String shopsId;

	public String getShopsId() {
		return shopsId;
	}

	public void setShopsId(String shopsId) {
		this.shopsId = shopsId;
	}
}