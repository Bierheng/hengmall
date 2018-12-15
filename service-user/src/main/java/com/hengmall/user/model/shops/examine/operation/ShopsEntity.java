package com.hengmall.user.model.shops.examine.operation;

import com.hengmall.user.model.basics.BasicsSaveBean;

import io.swagger.annotations.ApiModelProperty;

/**
 * 店铺审核操作  店铺  entity
 * @author Administrator
 *
 */
public class ShopsEntity extends BasicsSaveBean{

	@ApiModelProperty(value = "用户id")
    private String userId;
	
	@ApiModelProperty(value = "地址")
    private String address;
	
	@ApiModelProperty(value = "纬度")
    private String lat;
	
	@ApiModelProperty(value = "经度")
    private String lng;

	@ApiModelProperty(value = "店铺名")
	private String shopsName;
	
	@ApiModelProperty(value = "头像")
	private String avatarUrl;
	
	@ApiModelProperty(value = "国籍")
	private String national;
	
	@ApiModelProperty(value = "店铺描述")
	private String describe;

	@ApiModelProperty(value = "营业执照图（多张图）")
	private String licenses;
	
	@ApiModelProperty(value = "店主照片（多张图）")
	private String userPhotos;
	
	@ApiModelProperty(value = "店主电话")
	private String userPhone;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	
	public String getLicenses() {
		return licenses;
	}

	public void setLicenses(String licenses) {
		this.licenses = licenses;
	}

	public String getUserPhotos() {
		return userPhotos;
	}

	public void setUserPhotos(String userPhotos) {
		this.userPhotos = userPhotos;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	
}
