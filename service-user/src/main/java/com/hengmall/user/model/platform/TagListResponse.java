package com.hengmall.user.model.platform;

import io.swagger.annotations.ApiModelProperty;

/**
 * 产品货源地国籍列表 entity
 * @author Administrator
 *
 */
public class TagListResponse{
	
	@ApiModelProperty(value = "平台标签ID(一)")
	private String id;
	
	@ApiModelProperty(value = "平台标签图标(二)")
	private String url;
	
	@ApiModelProperty(value = "平台标签名称(三)")
	private String name;

	@ApiModelProperty(value = "状态(四)")
	private Integer flag;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
	
}