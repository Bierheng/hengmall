package com.server.entity.aboutUs;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 关于我们 entity
 * @author Administrator
 *
 */
public class AboutUsResponse{
	
	@ApiModelProperty(value = "关于我们ID")
	private String id;
	
	@ApiModelProperty(value = "关于我们介绍内容 type:内容类型（1：文本，2：图片，3：视频）value:内容")
	private String info;
	
	@ApiModelProperty(value = "创建时间")
	private Date createdTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	
}