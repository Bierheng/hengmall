package com.hengmall.user.model.order;

import io.swagger.annotations.ApiModelProperty;

/**
 * 派单员
 * @author Administrator
 *
 */
public class UsersResponse {

	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty(value = "用户名")
	private String username;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
