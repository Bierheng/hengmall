package com.hengmall.user.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ApiRequest {

	@ApiModelProperty("token认证")
	private String token;

	@Override
	public String toString() {
		return "ApiRequest{" + "token='" + token + '\'' + '}';
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
