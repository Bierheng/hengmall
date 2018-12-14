package com.server.entity.sensitive;

import com.server.entity.basics.BasicsBean;

import io.swagger.annotations.ApiModelProperty;


/**
 * 敏感词 entity  请求需要的参数
 * @author Administrator
 *
 */
public class SensitiveWordRequest extends BasicsBean{
	
	@ApiModelProperty(value = "敏感词ID(条件查询)")
	private String id;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}