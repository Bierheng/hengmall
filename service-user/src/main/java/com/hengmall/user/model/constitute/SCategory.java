package com.hengmall.user.model.constitute;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 商品分类模块 实体类
 */
@ApiModel
public class SCategory {

	@ApiModelProperty(value = "主键ID，用于后台交互")
	private int id;
	@ApiModelProperty(value = "类别名称")
	private String name;
	@ApiModelProperty(value = "图标，小图，嵌入在首页")
	private String icon1;
	@ApiModelProperty(value = "图标2，小图")
	private String icon2;
	@ApiModelProperty(value = "一级分类名称")
	private String pname;

	@Override
	public String toString() {
		return "SCategory{" + "id=" + id + ", name='" + name + '\'' + ", icon1='" + icon1 + '\'' + '}';
	}
	
	

	public String getIcon2() {
		return icon2;
	}



	public void setIcon2(String icon2) {
		this.icon2 = icon2;
	}



	public String getPname() {
		return pname;
	}



	public void setPname(String pname) {
		this.pname = pname;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon1() {
		return icon1;
	}

	public void setIcon1(String icon1) {
		this.icon1 = icon1;
	}
}
