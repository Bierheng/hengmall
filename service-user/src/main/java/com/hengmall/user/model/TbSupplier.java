package com.server.entity;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class TbSupplier {
	
	@ApiModelProperty(value = "供应商ID")
    private Integer id;
	@ApiModelProperty(value = "供应商主人ID")
    private Integer user_id;
	@ApiModelProperty(value = "地理位置纬度")
    private String lat;
	@ApiModelProperty(value = "地理位置经度")
    private String lng;
	@ApiModelProperty(value = "创建时间")
    private Date created_time;
	@ApiModelProperty(value = "修改时间")
    private Date update_time;
	@ApiModelProperty(value = "代理名称")
    private String supplier_name;

	
	@Override
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("ShopsLocation{");
		buffer.append("id='").append(id).append("'");
		buffer.append("user_id='").append(user_id).append("'");
		buffer.append("update_time='").append(update_time).append("'");
		buffer.append("lat='").append(lat).append("'");
		buffer.append("lng='").append(lng).append("'");
		buffer.append("created_time='").append(created_time).append("'");
		buffer.append("supplier_name='").append(supplier_name).append("'");
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

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public Date getCreated_time() {
		return created_time;
	}

	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}

	public String getSupplier_name() {
		return supplier_name;
	}

	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}
	
	
}