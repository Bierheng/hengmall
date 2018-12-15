package com.hengmall.user.model.platform;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;


/**
 * 平台标签添加、修改 entity  请求需要的参数
 * @author Administrator
 *
 */
public class TagDelRequest{
	
	@NotEmpty(message="平台标签id不能为空")
	@ApiModelProperty(value="平台标签id",required=true)
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}