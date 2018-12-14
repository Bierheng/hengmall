package com.hengmall.goods.model;

import io.swagger.annotations.ApiModelProperty;

public class SupplierLocation {
	
	@ApiModelProperty(value = "地理位置纬度")
    private String lat;
	@ApiModelProperty(value = "地理位置经度")
    private String lng;
	@ApiModelProperty(value = "供应商/店铺主人token")
    private String token;
	@ApiModelProperty(value = "地理位置")
    private String address;
	@ApiModelProperty(value = "名称")
    private String name;

	
	@Override
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("ShopsLocation{");
		buffer.append("lat='").append(lat).append("'");
		buffer.append("lng='").append(lng).append("'");
		buffer.append("address='").append(address).append("'");
		buffer.append("token='").append(token).append("'");
		buffer.append("name='").append(name).append("'");
		buffer.append("}");
		return buffer.toString();
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


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
}
