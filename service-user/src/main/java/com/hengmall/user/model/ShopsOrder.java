package com.hengmall.user.model;

import io.swagger.annotations.ApiModelProperty;

public class ShopsOrder {
	
	@ApiModelProperty(value = "店铺订单ID")
    private Integer id;
	@ApiModelProperty(value = "订单ID")
    private Integer order_id;
	@ApiModelProperty(value = "创建时间")
    private Integer created_time;
	@ApiModelProperty(value = "店铺主人ID")
    private Integer shops_id;


	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("ShopsMember{");
		buffer.append("id='").append(id).append("'");
		buffer.append("order_id='").append(order_id).append("'");
		buffer.append("created_time='").append(created_time).append("'");
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

	public Integer getCreated_time() {
		return created_time;
	}

	public void setCreated_time(Integer created_time) {
		this.created_time = created_time;
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public Integer getShops_id() {
		return shops_id;
	}

	public void setShops_id(Integer shops_id) {
		this.shops_id = shops_id;
	}
	
}