package com.hengmall.user.model.platform;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;

/**
 * 产品货源地国籍删除 entity  请求需要的参数
 * @author Administrator
 *
 */
public class StateDelRequest{
	
	@NotEmpty(message="产品货源地国籍id不能为空")
	@ApiModelProperty(value="产品货源地国籍id",required=true)
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}