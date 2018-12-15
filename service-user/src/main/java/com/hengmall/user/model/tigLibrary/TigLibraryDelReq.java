package com.hengmall.user.model.tigLibrary;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;

/**
 * 分类删除 request
 * @author Administrator
 *
 */
public class TigLibraryDelReq {

	@NotEmpty(message="id不能为空")
	@ApiModelProperty(value = "id")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
