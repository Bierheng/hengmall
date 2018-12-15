package com.hengmall.user.model.shops;

import io.swagger.annotations.ApiModelProperty;

/**
 * 店铺板块 entity
 * @author Administrator
 *
 */
public class ShopsPlateResponse{
	
	@ApiModelProperty(value = "店铺板块ID")
	private String id;    //id
	
	@ApiModelProperty(value = "板块内容类型   1：文字，2：图片，3：视频")
	private String type;
	
	@ApiModelProperty(value = "板块内容")
	private String content;
	
	@ApiModelProperty(value = "排序号")
	private Integer orderNo;
	
	@ApiModelProperty(value = "店铺ID(条件查询)")
	private String shopsId;
	
	@ApiModelProperty(value = "店铺名")
	private String shopsName;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

}