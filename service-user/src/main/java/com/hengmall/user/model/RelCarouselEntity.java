package com.hengmall.user.model;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 图片轮播 实体类
 */
public class RelCarouselEntity {

	// 1-商品，2-活动，3-团购
	public static final int Commodity_Type = 1;
	public static final int activity_Type = 2;
	public static final int Group_Type = 3;
	public static final int Duty_Type = 4;
	public static final int Luxury_Type = 5;
	public static final int Women_Type = 6;
	public static final int Shoe_Type = 7;

	private int id; // 商品类别id
	@ApiModelProperty(value = "轮播图所属类别ID")
	private int s_category_id; //
	@ApiModelProperty(value = "商品关联的id")
	private int product_id; // 商品关联的id
	@ApiModelProperty(value = "创建时间")
	private Date created_time; // 创建时间
	@ApiModelProperty(value = "图片地址")
	private String path; // 资源地址，对象存储地址
	@ApiModelProperty(value = "店铺的id")
	private int shops_id; 
	
	private String categoryName;  //商品类别名
	
	private String productName;	//商品名
	
	private String shops_name; 
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("RelCarouselEntity{");
		buffer.append("id='").append(id).append("'");
		buffer.append("sCategoryId='").append(s_category_id).append("'");
		buffer.append("productId='").append(product_id).append("'");
		buffer.append("createdTime='").append(created_time).append("'");
		buffer.append("path='").append(path).append("'");
		buffer.append("}");
		return buffer.toString();
	}
	
	public int getShops_id() {
		return shops_id;
	}

	public void setShops_id(int shops_id) {
		this.shops_id = shops_id;
	}

	public String getShops_name() {
		return shops_name;
	}

	public void setShops_name(String shops_name) {
		this.shops_name = shops_name;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getS_category_id() {
		return s_category_id;
	}

	public void setS_category_id(int s_category_id) {
		this.s_category_id = s_category_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public Date getCreated_time() {
		return created_time;
	}

	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}


}
