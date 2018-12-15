package com.hengmall.user.model.shops.examine;

import com.hengmall.user.model.basics.BasicsBean;

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
