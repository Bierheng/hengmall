package com.server.entity.shops;

import java.util.Date;

import com.server.entity.persistence.DictMessage;

import io.swagger.annotations.ApiModelProperty;

/**
 * 店铺会员返回参数 entity
 * @author Administrator
 *
 */
public class ShopsUserResponse{
	
	@ApiModelProperty(value = "店铺名")
	private String shopsName;
	
	@ApiModelProperty(value = "会员ID")
	private String userId;
	
	@ApiModelProperty(value = "会员名称")
	private String nickname;
	
	@ApiModelProperty(value = "会员头像")
	private String avatarUrl;
	
	@ApiModelProperty(value = "会员性别 1：男性，2：女性，0：未知")
	private String gender;
	
	@ApiModelProperty(value = "会员所在城市")
	private String city;
	
	@ApiModelProperty(value = "会员所在省份")
	private String province;
	
	@ApiModelProperty(value = "会员所在国家")
	private String country;
	
	@ApiModelProperty(value = "会员创建时间")
	private Date createdTime;
	
	@ApiModelProperty(value = "状态，0：启用，2：禁用，默认启用(预留，暂时不管)")
	private int status;

	public String getShopsName() {
		return shopsName;
	}

	public void setShopsName(String shopsName) {
		this.shopsName = shopsName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getGender() {
		return DictMessage.getDictLabel("type_sex", gender, "未知");
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}