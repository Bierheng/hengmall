package com.server.entity.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2018/5/25.
 */
@ApiModel
public class EditAddressReq extends ApiRequest {

	@ApiModelProperty(value = "收货地址主键id")
	int id;
	@ApiModelProperty(value = "收货人姓名")
	String uname;
	@ApiModelProperty(value = "收货人手机号码")
	String phone;
	@ApiModelProperty(value = "邮政编码")
	int zip_code;
	@ApiModelProperty(value = "省")
	String province;
	@ApiModelProperty(value = "市")
	String city;
	@ApiModelProperty(value = "区")
	String area;
	@ApiModelProperty(value = "详细地址")
	String address;

	@Override
	public String toString() {
		return "EditAddressReq{" + "id='" + id + '\'' + ", uname='" + uname + '\'' + ", phone='" + phone + '\''
				+ ", zip_code=" + zip_code + ", province='" + province + '\'' + ", city='" + city + '\'' + ", area='"
				+ area + '\'' + ", address='" + address + '\'' + '}' + "，" + super.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getZip_code() {
		return zip_code;
	}

	public void setZip_code(int zip_code) {
		this.zip_code = zip_code;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String getToken() {
		return super.getToken();
	}

	@Override
	public void setToken(String token) {
		super.setToken(token);
	}
}
