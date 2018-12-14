package com.server.entity;

import java.util.Date;

/**
 * 图片轮播 实体类
 */
public class PAddressEntity {

	private int id;
	private String uname; // 收获人姓名
	private String user_id; // 用户关联ID
	private String phone; // 收货人手机号码
	private int zip_code; // 邮政编码
	private String province; // 省
	private String city; // 市
	private String area; // 区
	private String street; // 街道
	private String adress; // 详细地址
	private Date ctime; // 添加时间

	@Override
	public String toString() {
		return "PAddressEntity{" + "id=" + id + ", uname='" + uname + '\'' + ", user_id='" + user_id + '\''
				+ ", phone='" + phone + '\'' + ", zip_code=" + zip_code + ", province='" + province + '\'' + ", city='"
				+ city + '\'' + ", area='" + area + '\'' + ", street='" + street + '\'' + ", adress='" + adress + '\''
				+ ", ctime=" + ctime + '}';
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

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
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

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
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
