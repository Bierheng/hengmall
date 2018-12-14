package com.server.entity.shops;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;


/**
 * 店铺商品添加 entity  请求需要的参数
 * @author Administrator
 *
 */
public class ShopsAddRequest{
	
	@ApiModelProperty(value = "店铺id",required=true)
	@NotEmpty(message="店铺id不能为空")
	private String shopsId;

	@NotEmpty(message="是否推荐不能为空")
	@ApiModelProperty(value = "是否推荐(1:是 0:否)")
	private String recommend;
	
	@NotEmpty(message="商品ids不能为空")
	@ApiModelProperty(value = "商品ids")
	private int[] productIds;


	@ApiModelProperty(value = "创建时间",hidden=true)
	private Date createdTime;
	
	public String getShopsId() {
		return shopsId;
	}

	public void setShopsId(String shopsId) {
		this.shopsId = shopsId;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public int[] getProductIds() {
		return productIds;
	}

	public void setProductIds(int[] productIds) {
		this.productIds = productIds;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

}