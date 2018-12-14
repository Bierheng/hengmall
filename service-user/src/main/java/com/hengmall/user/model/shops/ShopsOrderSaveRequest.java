package com.server.entity.shops;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;

/**
 * 店铺订单操作
 * @author Administrator
 *
 */
@JsonIgnoreProperties(value={"token"})
public class ShopsOrderSaveRequest {

	@ApiModelProperty(value = "id")
	@NotEmpty(message="id不能为空")
	private String id;
	
	@ApiModelProperty(value = "token",hidden=true)
	private String token;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
