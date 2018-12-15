package com.hengmall.user.model.shops;

import com.hengmall.user.model.persistence.DictMessage;

import io.swagger.annotations.ApiModelProperty;

/**
 * 店铺粉丝返回参数 entity
 * @author Administrator
 *
 */
public class ShopsAttentionResponse{
	
	@ApiModelProperty(value = "店铺名")
	private String shopsName;
	
	@ApiModelProperty(value = "粉丝ID")
	private String userId;
	
	@ApiModelProperty(value = "粉丝名称")
	private String nickname;
	
	@ApiModelProperty(value = "粉丝头像")
	private String avatarUrl;
	
	@ApiModelProperty(value = "粉丝性别 1：男性，2：女性，0：未知")
	private String gender;
	
	@ApiModelProperty(value = "粉丝所在城市")
	private String city;
	
	@ApiModelProperty(value = "粉丝所在省份")
	private String province;
	
	@ApiModelProperty(value = "粉丝所在国家")
	private String country;

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
	
	
	
}