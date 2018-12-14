package com.server.entity.shops.examine.operation;

import com.server.entity.basics.BasicsSaveBean;

import io.swagger.annotations.ApiModelProperty;

/**
 * 店铺添加轮播图审核操作    entity
 * @author Administrator
 *
 */
public class CarouselShopsEntity extends BasicsSaveBean{

	@ApiModelProperty(value = "商品关联的id")
	private String productId;
	
	@ApiModelProperty(value = "图片地址")
	private String path;
	
	@ApiModelProperty(value = "店铺id")
	private String shopsId;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getShopsId() {
		return shopsId;
	}

	public void setShopsId(String shopsId) {
		this.shopsId = shopsId;
	}
	
	
}
