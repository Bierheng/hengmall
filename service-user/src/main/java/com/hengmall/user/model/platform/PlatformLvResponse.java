package com.server.entity.platform;

import io.swagger.annotations.ApiModelProperty;

/**
 * 分类列表 entity
 * @author Administrator
 *
 */
public class PlatformLvResponse{
	
	@ApiModelProperty(value = "分类ID")
	private String id;
	
	@ApiModelProperty(value = "分类名称")
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}