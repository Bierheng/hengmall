package com.server.entity.platform;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 产品货源地国籍列表 entity
 * @author Administrator
 *
 */
public class StateResponse{
	
	@ApiModelProperty(value = "国籍ID")
	private String id;
	
	@ApiModelProperty(value = "国旗")
	private String stateUrl;
	
	@ApiModelProperty(value = "国家名称")
	private String stateName;
	
	@ApiModelProperty(value = "创建时间")
	private Date createdTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStateUrl() {
		return stateUrl;
	}

	public void setStateUrl(String stateUrl) {
		this.stateUrl = stateUrl;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	
}