package com.server.entity.shops.examine;

import io.swagger.annotations.ApiModelProperty;

/**
 * 店铺首页轮播图表单
 * @author Administrator
 *
 */
public class CarouselShopsResponse {

	@ApiModelProperty(value="图片地址")
	private String path;
	
	@ApiModelProperty(value="店铺名",hidden=true)
	private String shopsName;
	
	@ApiModelProperty(value="商品名",hidden=true)
	private String productName;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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
	
	
}
