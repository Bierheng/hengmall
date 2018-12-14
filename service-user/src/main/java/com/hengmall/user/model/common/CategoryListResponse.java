package com.server.entity.common;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 商品分类列表 entity
 * @author Administrator
 *
 */
public class CategoryListResponse{
	
	@ApiModelProperty(value = "商品类别id")
	private String id;    //id
	
	@ApiModelProperty(value = "类别名称")
    private String name;
	
	@ApiModelProperty(value = "类别级别1或2")
    private String level;
	
	@ApiModelProperty(value = "上级类型id")
    private String pid;
	
	@ApiModelProperty(value = "热门类目；默认0：不是，1：是")
    private String isHot;
	
	@ApiModelProperty(value = "图标，小图，嵌入在首页")
    private String icon1;
	
	@ApiModelProperty(value = "图标，大图，在分类展示页面显示大图")
    private String icon2;
	
	@ApiModelProperty(value = "创建时间")
    private Date createdTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getIsHot() {
		return isHot;
	}

	public void setIsHot(String isHot) {
		this.isHot = isHot;
	}

	public String getIcon1() {
		return icon1;
	}

	public void setIcon1(String icon1) {
		this.icon1 = icon1;
	}

	public String getIcon2() {
		return icon2;
	}

	public void setIcon2(String icon2) {
		this.icon2 = icon2;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	
}