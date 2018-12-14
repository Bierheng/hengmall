package com.server.entity.shops.examine;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;

/**
 * 店铺首页轮播图表单
 * @author Administrator
 *
 */
public class CarouselShopsRequest {

	@NotEmpty(message="必须选择一个商品")
	@ApiModelProperty(value="商品关联的id")
	private String productId;
	
	@NotEmpty(message="图片地址不能为空")
	@ApiModelProperty(value="图片地址")
	private String path;
	
	@NotEmpty(message="必须要选择一个店铺")
	@ApiModelProperty(value="店铺id")
	private String shopsId;
	
	@ApiModelProperty(value="店铺名",hidden=true,required=false)
	private String shopsName;
	
	@ApiModelProperty(value="商品名",hidden=true,required=false)
	private String productName;

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
