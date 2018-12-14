package com.server.entity.constitute;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2018/5/30.
 */
@ApiModel
public class AddressList {

	@ApiModelProperty(value = "主键ID，用于后台交互")
	private int id;
	@ApiModelProperty(value = "收获人姓名")
	private String uname;
	@ApiModelProperty(value = "收货人手机号码")
	private String phone;
	@ApiModelProperty(value = "邮政编码")
	private int zip_code;
	@ApiModelProperty(value = "省")
	private String province;
	@ApiModelProperty(value = "市")
	private String city;
	@ApiModelProperty(value = "区")
	private String area;
	@ApiModelProperty(value = "街道")
	private String street;
	@ApiModelProperty(value = "详细地址")
	private String adress;

	@Override
	public String toString() {
		return "AddressList{" + "id=" + id + ", uname='" + uname + '\'' + ", phone='" + phone + '\'' + ", zip_code="
				+ zip_code + ", province='" + province + '\'' + ", city='" + city + '\'' + ", area='" + area + '\''
				+ ", street='" + street + '\'' + ", adress='" + adress + '\'' + '}';
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

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}
}
