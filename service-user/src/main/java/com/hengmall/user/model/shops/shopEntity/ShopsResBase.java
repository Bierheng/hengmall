package com.hengmall.user.model.shops.shopEntity;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 店铺 entity 基类
 * @author Administrator
 *
 */
public class ShopsResBase {

	@ApiModelProperty(value = "id",hidden=true)
	private String id;    //id
	
	@ApiModelProperty(value = "创建时间",hidden=true)
	private Date createdTime;

	@ApiModelProperty(value = "状态  0：正常   1：删除  2：禁用",hidden=true)
	private String state;
	
	@ApiModelProperty(value = "粉丝数")
	private String fansNum;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getFansNum() {
		return fansNum;
	}

	public void setFansNum(String fansNum) {
		this.fansNum = fansNum;
	}
	
	
}
