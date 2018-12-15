package com.hengmall.user.model.platform;

import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModelProperty;


/**
 * 平台模板列表删除 entity  请求需要的参数
 * @author Administrator
 *
 */
public class PlatformDelRequest{
	
	@NotNull(message="平台模板ID不能为空")
	@ApiModelProperty(value = "平台模板ID")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}