package com.hengmall.goods.model.api;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2018/5/25.
 */

@ApiModel
public class UserInfo {

    @ApiModelProperty(value = "用户昵称")
    String nickName;
    @ApiModelProperty(value = "用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表132*132正方形头像）")
    String avatarUrl;
    @ApiModelProperty(value = "用户的性别，值为1时是男性，值为2时是女性，值为0时是未知")
    Integer gender;
    @ApiModelProperty(value = "用户所在城市")
    String city;
    @ApiModelProperty(value = "用户所在省份")
    String province;
    @ApiModelProperty(value = "用户所在国家")
    String country;
    @ApiModelProperty(value = "用户的语言，简体中文为zh_CN")
    String language;
    
    
	public String getNickName() throws UnsupportedEncodingException {
		String toWxNck = URLDecoder.decode(nickName, "UTF-8");
		return toWxNck;
	}
	public void setNickName(String nickName){
		this.nickName =  nickName;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
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
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
    
    

}
