package com.server.entity.platform;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;

/**
 * 店铺资质删除 entity  请求需要的参数
 * @author Administrator
 *
 */
public class RightDelRequest{
	
	@NotEmpty(message="资质id不能为空")
	@ApiModelProperty(value="资质id",required=true)
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}