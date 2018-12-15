package com.hengmall.user.model.platform;

import io.swagger.annotations.ApiModelProperty;

/**
 * 店铺资质列表 entity
 * @author Administrator
 *
 */
public class RightResponse{
	
	@ApiModelProperty(value = "店铺资质标签ID")
	private String id;
	
	@ApiModelProperty(value = "店铺资质图标")
	private String url;
	
	@ApiModelProperty(value = "资质名称")
	private String name;
	
	@ApiModelProperty(value = "创建时间")
	private String createdTime;
	
	@ApiModelProperty(value = "资质说明内容")
	private String content;
	
	@ApiModelProperty(value = "店铺id")
	private String shopsId;
	
	@ApiModelProperty(value = "店铺名称")
	private String shopsName;

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

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getShopsName() {
		return shopsName;
	}

	public void setShopsName(String shopsName) {
		this.shopsName = shopsName;
	}

	public String getShopsId() {
		return shopsId;
	}

	public void setShopsId(String shopsId) {
		this.shopsId = shopsId;
	}
	
	
}