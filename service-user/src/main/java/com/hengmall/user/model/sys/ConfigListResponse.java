package com.server.entity.sys;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 系统配置列表  response
 * @author Administrator
 *
 */
public class ConfigListResponse {

	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty(value = "系统配置编码")
	private String code;
	
	@ApiModelProperty(value = "系统配置名称")
	private String name;
	
	@ApiModelProperty(value = "系统配置值")
	private String value;
	
	@ApiModelProperty(value = "状态,0：禁用，1：启用")
	private Integer status;
	
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	
	@ApiModelProperty(value = "修改时间")
	private Date updateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
