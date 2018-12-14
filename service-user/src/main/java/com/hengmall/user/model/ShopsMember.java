package com.server.entity;

import io.swagger.annotations.ApiModelProperty;

public class ShopsMember {
	
	@ApiModelProperty(value = "店铺会员ID")
    private Integer id;
	@ApiModelProperty(value = "会员ID")
    private Integer user_id;
	@ApiModelProperty(value = "创建时间")
    private Integer created_time;
	@ApiModelProperty(value = "状态，1：启用，0：禁用，默认启用")
    private Integer status;
	@ApiModelProperty(value = "店铺主人ID")
    private Integer shops_id;


	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("ShopsMember{");
		buffer.append("id='").append(id).append("'");
		buffer.append("user_id='").append(user_id).append("'");
		buffer.append("created_time='").append(created_time).append("'");
		buffer.append("status='").append(status).append("'");
		buffer.append("shops_id='").append(shops_id).append("'");
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

	public Integer getCreated_time() {
		return created_time;
	}

	public void setCreated_time(Integer created_time) {
		this.created_time = created_time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getShops_id() {
		return shops_id;
	}

	public void setShops_id(Integer shops_id) {
		this.shops_id = shops_id;
	}
	
}