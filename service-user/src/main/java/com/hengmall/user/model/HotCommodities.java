package com.hengmall.user.model;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class HotCommodities {
	
    private Integer id;
	@ApiModelProperty(value = "类别名称")
    private String name;
	@ApiModelProperty(value = "图标")
    private String icon;
	@ApiModelProperty(value = "类型，默认0：无类型，1：女装，2：鞋包，3：母婴")
    private Integer type;
	@ApiModelProperty(value = "创建时间")
    private Date createdTime;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
}