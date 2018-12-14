package com.hengmall.goods.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class ShopsPlate {
	
	@ApiModelProperty(value = "店铺板块ID")
    private Integer id;
	@ApiModelProperty(value = "板块类型，1：文字，2：图片，3：视频")
    private Integer type;
	@ApiModelProperty(value = "内容")
    private String content;
	@ApiModelProperty(value = "序号")
    private Integer order_no;
	@ApiModelProperty(value = "店铺ID")
    private Integer shops_id;
	@ApiModelProperty(value = "店铺ID")
    private List<ProductReq> plateProductList;
	

	
	@Override
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("ShopsPlate{");
		buffer.append("id='").append(id).append("'");
		buffer.append("type='").append(type).append("'");
		buffer.append("content='").append(content).append("'");
		buffer.append("order_no='").append(order_no).append("'");
		buffer.append("shops_id='").append(shops_id).append("'");
		buffer.append("}");
		return buffer.toString();
	}



	public List<ProductReq> getPlateProductList() {
		return plateProductList;
	}



	public void setPlateProductList(List<ProductReq> plateProductList) {
		this.plateProductList = plateProductList;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getType() {
		return type;
	}



	public void setType(Integer type) {
		this.type = type;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public Integer getOrder_no() {
		return order_no;
	}



	public void setOrder_no(Integer order_no) {
		this.order_no = order_no;
	}



	public Integer getShops_id() {
		return shops_id;
	}



	public void setShops_id(Integer shops_id) {
		this.shops_id = shops_id;
	}
}