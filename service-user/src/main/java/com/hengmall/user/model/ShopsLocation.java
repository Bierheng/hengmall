package com.hengmall.user.model;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class ShopsLocation {
	
	@ApiModelProperty(value = "店铺ID")
    private Integer id;
	@ApiModelProperty(value = "店铺主人ID")
    private Integer user_id;
	@ApiModelProperty(value = "地理位置纬度")
    private String lat;
	@ApiModelProperty(value = "地理位置经度")
    private String lng;
	@ApiModelProperty(value = "店铺地址")
    private String address;
	@ApiModelProperty(value = "创建时间")
    private Date created_time;
	@ApiModelProperty(value = "店铺名称")
    private String shops_name;
	@ApiModelProperty(value = "店铺头像")
    private String avatar_url;
	@ApiModelProperty(value = "国籍")
    private String national;
	@ApiModelProperty(value = "粉丝数")
    private Integer fans_num;
	@ApiModelProperty(value = "店铺描述")
    private String describe;
	

	
	@Override
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("ShopsLocation{");
		buffer.append("id='").append(id).append("'");
		buffer.append("user_id='").append(user_id).append("'");
		buffer.append("address='").append(address).append("'");
		buffer.append("lat='").append(lat).append("'");
		buffer.append("lng='").append(lng).append("'");
		buffer.append("created_time='").append(created_time).append("'");
		buffer.append("shops_name='").append(shops_name).append("'");
		buffer.append("avatar_url='").append(avatar_url).append("'");
		buffer.append("national='").append(national).append("'");
		buffer.append("fans_num='").append(fans_num).append("'");
		buffer.append("describe='").append(describe).append("'");
		buffer.append("}");
		return buffer.toString();
	}
	
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


	public Integer getUser_id() {
		return user_id;
	}


	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreated_time() {
		return created_time;
	}

	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}

	public String getShops_name() {
		return shops_name;
	}

	public void setShops_name(String shops_name) {
		this.shops_name = shops_name;
	}

	public String getAvatar_url() {
		return avatar_url;
	}

	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
	}

	public String getNational() {
		return national;
	}

	public void setNational(String national) {
		this.national = national;
	}

	public Integer getFans_num() {
		return fans_num;
	}

	public void setFans_num(Integer fans_num) {
		this.fans_num = fans_num;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
}