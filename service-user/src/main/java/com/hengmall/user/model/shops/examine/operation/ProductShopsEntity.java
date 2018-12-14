package com.server.entity.shops.examine.operation;

import com.server.entity.basics.BasicsSaveBean;

import io.swagger.annotations.ApiModelProperty;

/**
 * 店铺选择商品审核操作    entity
 * @author Administrator
 *
 */
public class ProductShopsEntity extends BasicsSaveBean{

	@ApiModelProperty(value = "店铺id")
	private String shopsId;
	
	@ApiModelProperty(value = "商品们")
	private String[] productIds;
	
	@ApiModelProperty(value = "是否推荐")
	private Integer recommend;

	public String getShopsId() {
		return shopsId;
	}

	public void setShopsId(String shopsId) {
		this.shopsId = shopsId;
	}

	public String[] getProductIds() {
		return productIds;
	}

	public void setProductIds(String[] productIds) {
		this.productIds = productIds;
	}

	public Integer getRecommend() {
		return recommend;
	}

	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}
	
}
