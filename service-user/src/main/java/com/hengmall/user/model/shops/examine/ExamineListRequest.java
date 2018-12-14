package com.server.entity.shops.examine;

import com.server.entity.basics.BasicsBean;

import io.swagger.annotations.ApiModelProperty;

/**
 * 店铺审核列表    request
 * @author Administrator
 *
 */
public class ExamineListRequest extends BasicsBean{

	@ApiModelProperty(value="token",hidden=true,required=false)
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
