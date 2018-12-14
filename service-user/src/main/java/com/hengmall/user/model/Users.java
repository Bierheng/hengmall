package com.server.entity;

import com.hazelcast.nio.serialization.Data;

import io.swagger.annotations.ApiModelProperty;

public class Users {
    private Integer id;
	@ApiModelProperty(value = "小程序唯一标识")
    private String openid;
	@ApiModelProperty(value = "头像")
    private String avatar_url;
	@ApiModelProperty(value = "用户的性别，1：男性，2：女性，0：未知")
    private Integer gender;
	@ApiModelProperty(value = "用户所在城市")
    private String city;
	@ApiModelProperty(value = "用户所在省份")
    private String province;
	@ApiModelProperty(value = "用户所在国家")
    private String country;
	@ApiModelProperty(value = "默认收货地址id")
    private Integer address_id; 
	@ApiModelProperty(value = "token认证")
    private String token; 
	@ApiModelProperty(value = "个人中心钱包总金额（单位：分）")
    private Integer money; 
	@ApiModelProperty(value = "默认收货地址id")
    private Data created_time; 
	@ApiModelProperty(value = "默认收货地址id")
    private Data updated_time; 
	@ApiModelProperty(value = "默认收货地址id")
    private String nickname;
	
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getAvatar_url() {
		return avatar_url;
	}

	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
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

	public Integer getAddress_id() {
		return address_id;
	}

	public void setAddress_id(Integer address_id) {
		this.address_id = address_id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public Data getCreated_time() {
		return created_time;
	}

	public void setCreated_time(Data created_time) {
		this.created_time = created_time;
	}

	public Data getUpdated_time() {
		return updated_time;
	}

	public void setUpdated_time(Data updated_time) {
		this.updated_time = updated_time;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}