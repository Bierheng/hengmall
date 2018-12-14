package com.server.entity.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2018/5/25.
 */
@ApiModel
public class DefaultAddressReq extends ApiRequest {

	@ApiModelProperty(value = "收货地址id")
	int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "DefaultAddressReq{" + "id='" + id + '\'' + '}' + "，" + super.toString();
	}

	@Override
	public String getToken() {
		return super.getToken();
	}

	@Override
	public void setToken(String token) {
		super.setToken(token);
	}
}
