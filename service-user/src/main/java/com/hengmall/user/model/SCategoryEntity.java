package com.server.entity;

import io.swagger.annotations.ApiModelProperty;

/**
 * 商品分类模块 实体类
 */
public class SCategoryEntity {

	// 类别级别1或2
	public static final int First_Level = 1;
	public static final int Second_Level = 2;

	@ApiModelProperty(value = "商品类别id")
	private int id; // 商品类别id
	@ApiModelProperty(value = "类别名称")
	private String name; // 类别名称
	@ApiModelProperty(value = "类别级别1或2")
	private int level; // 类别级别1或2
	@ApiModelProperty(value = "上级类型id")
	private int pid; // 上级类型id
	@ApiModelProperty(value = "图标，小图，嵌入在首页")
	private String icon1; // 图标，小图，嵌入在首页
	@ApiModelProperty(value = "图标，大图，在分类展示页面显示大图")
	private String icon2; // 图标，大图，在分类展示页面显示大图
	
	private Integer shops_id;
	private String shops_name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getShops_id() {
		return shops_id;
	}

	public void setShops_id(Integer shops_id) {
		this.shops_id = shops_id;
	}

	public String getShops_name() {
		return shops_name;
	}

	public void setShops_name(String shops_name) {
		this.shops_name = shops_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
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
	
}
