package com.hengmall.goods.model;

import io.swagger.annotations.ApiModelProperty;

public class ShopsPlateProduct {
	
	@ApiModelProperty(value = "店铺板块商品ID")
    private Integer id;
	@ApiModelProperty(value = "平台商品ID")
    private Integer product_id;
	@ApiModelProperty(value = "板块ID")
    private String plate_id;
	@ApiModelProperty(value = "店铺ID")
    private Integer shops_id;
	

	
	@Override
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("ShopsPlate{");
		buffer.append("id='").append(id).append("'");
		buffer.append("product_id='").append(product_id).append("'");
		buffer.append("plate_id='").append(plate_id).append("'");
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



	public Integer getProduct_id() {
		return product_id;
	}



	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}



	public String getPlate_id() {
		return plate_id;
	}



	public void setPlate_id(String plate_id) {
		this.plate_id = plate_id;
	}



	public Integer getShops_id() {
		return shops_id;
	}



	public void setShops_id(Integer shops_id) {
		this.shops_id = shops_id;
	}

}