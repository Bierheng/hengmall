package com.server.entity.shops.examine;

import io.swagger.annotations.ApiModelProperty;

/**
 * 开店审核申请表单   request
 * @author Administrator
 *
 */
public class ExShopsRequest {
	@ApiModelProperty(value = "地址")
    private String address;

	@ApiModelProperty(value = "店铺名")
	private String shopsName;
	
	@ApiModelProperty(value = "头像")
	private String avatarUrl;
	
	@ApiModelProperty(value = "国籍")
	private String national;
	
	@ApiModelProperty(value = "店铺描述")
	private String describe;

	@ApiModelProperty(value = "营业执照图（多张图）")
	private String[] licenses;
	
	@ApiModelProperty(value = "店主照片（多张图）")
	private String[] userPhotos;
	
	@ApiModelProperty(value = "店主电话")
	private String userPhone;
	
	@ApiModelProperty(value = "纬度",hidden=true)
	private String lat;
	
	@ApiModelProperty(value = "经度",hidden=true)
	private String lng;
	
	@ApiModelProperty(value = "店铺主id",hidden=true)
	private String userId;


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getShopsName() {
		return shopsName;
	}

	public void setShopsName(String shopsName) {
		this.shopsName = shopsName;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getNational() {
		return national;
	}

	public void setNational(String national) {
		this.national = national;
	}


	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String[] getLicenses() {
		return licenses;
	}

	public void setLicenses(String[] licenses) {
		this.licenses = licenses;
	}

	public String[] getUserPhotos() {
		return userPhotos;
	}

	public void setUserPhotos(String[] userPhotos) {
		this.userPhotos = userPhotos;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
