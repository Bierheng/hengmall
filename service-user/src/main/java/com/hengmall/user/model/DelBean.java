package com.server.entity;

import io.swagger.annotations.ApiModelProperty;

public class DelBean {
	
	@ApiModelProperty(value = "要被删除的ID")
    private Integer ids;

	public Integer getIds() {
		return ids;
	}

	public void setIds(Integer ids) {
		this.ids = ids;
	}

   
}