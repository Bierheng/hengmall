package com.server.entity.sys;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;

/**
 * 系统配置删除   request
 * @author Administrator
 *
 */
public class ConfigDelRequest {

	@NotEmpty(message="系统配置id不能为空")
	@ApiModelProperty(value = "id")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
