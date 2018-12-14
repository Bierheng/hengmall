package com.server.entity;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class ShopsProduct {
	
	@ApiModelProperty(value = "店铺商品ID")
    private Integer id;
	@ApiModelProperty(value = "店铺ID")
    private Integer shops_id;
	@ApiModelProperty(value = "商品ID")
    private Integer product_id;
	@ApiModelProperty(value = "创建时间")
    private Date created_time;
	@ApiModelProperty(value = "是否推荐")
    private Date recommend;


	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("ShopsMember{");
		buffer.append("id='").append(id).append("'");
		buffer.append("shops_id='").append(shops_id).append("'");
		buffer.append("created_time='").append(created_time).append("'");
		buffer.append("product_id='").append(product_id).append("'");
		buffer.append("recommend='").append(recommend).append("'");
		buffer.append("}");
		return buffer.toString();
	}
	
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public Integer getShops_id() {
		return shops_id;
	}

	public void setShops_id(Integer shops_id) {
		this.shops_id = shops_id;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public Date getCreated_time() {
		return created_time;
	}

	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}

	public Date getRecommend() {
		return recommend;
	}

	public void setRecommend(Date recommend) {
		this.recommend = recommend;
	}
	
}