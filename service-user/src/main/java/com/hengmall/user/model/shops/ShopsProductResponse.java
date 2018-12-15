package com.hengmall.user.model.shops;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 店铺商品 entity
 * @author Administrator
 *
 */
public class ShopsProductResponse{
	
	@ApiModelProperty(value = "店铺商品表id")
	private String tbId;
	
	@ApiModelProperty(value = "店铺商品id")
	private String id;
	
	@ApiModelProperty(value = "店铺ID")
	private String shopsId;
	
	@ApiModelProperty(value = "店铺名")
	private String shopsName;
	
	@ApiModelProperty(value = "商品名")
	private String productName;
	
	@ApiModelProperty(value = "商品添加时间")
	private Date createdTime;
	
	@ApiModelProperty(value = "是否推荐")
	private String recommend;   //0否1是
	
	@ApiModelProperty(value = "商品类别")
	private String typeName;

	@ApiModelProperty(value = "商品图片")
	private String headimg;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShopsName() {
		return shopsName;
	}

	public void setShopsName(String shopsName) {
		this.shopsName = shopsName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getRecommend() {
		if(recommend != null) {
			if(recommend.equals("0")) {
				return "否";
			}else if(recommend.equals("1")) {
				return "是";
			}
		}
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getShopsId() {
		return shopsId;
	}

	public void setShopsId(String shopsId) {
		this.shopsId = shopsId;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public String getTbId() {
		return tbId;
	}

	public void setTbId(String tbId) {
		this.tbId = tbId;
	}

}