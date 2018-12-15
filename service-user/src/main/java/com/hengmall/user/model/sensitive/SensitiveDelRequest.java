package com.hengmall.user.model.sensitive;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;


/**
 * 删除敏感词 entity  请求需要的参数
 * @author Administrator
 *
 */
public class SensitiveDelRequest{
	

	@ApiModelProperty(value = "敏感词")
	@NotEmpty(message="敏感词ID不能为空")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}