package com.hengmall.user.model.shops.examine;

import java.util.Date;

import com.hengmall.user.model.shops.commend.ShopsCommendRequest;

import io.swagger.annotations.ApiModelProperty;

/**
 * 店铺审核提交   实体
 * @author Administrator
 *
 */
public class ExamineRequest{

	@ApiModelProperty(value = "token",hidden=true,required=false)
	private String token;
	
	@ApiModelProperty(value = "用户id",hidden=true,required=false)
	private String userId;
	
	@ApiModelProperty(value = "最终提交的数据",hidden=true,required=false)
	private String data;
	
	@ApiModelProperty(value = "申请类型")
	private int type;

	@ApiModelProperty(value = "开店审核申请表单")
	private ExShopsRequest exShopsRequest;
	
	@ApiModelProperty(value = "店铺选择商品申请表单")
	private ProductShopsRequest productShopsRequest;
	
	@ApiModelProperty(value = "店铺首页产品图组合表单")
	private ShopsCommendRequest shopsCommendRequest;

	@ApiModelProperty(value = "店铺首页轮播图表单")
	private CarouselShopsRequest carouselShopsRequest;

	public ExShopsRequest getExShopsRequest() {
		return exShopsRequest;
	}

	public void setExShopsRequest(ExShopsRequest exShopsRequest) {
		this.exShopsRequest = exShopsRequest;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getInsertDate() {
		return new Date();
	}
	public ProductShopsRequest getProductShopsRequest() {
		return productShopsRequest;
	}

	public void setProductShopsRequest(ProductShopsRequest productShopsRequest) {
		this.productShopsRequest = productShopsRequest;
	}

	

	public ShopsCommendRequest getShopsCommendRequest() {
		return shopsCommendRequest;
	}

	public void setShopsCommendRequest(ShopsCommendRequest shopsCommendRequest) {
		this.shopsCommendRequest = shopsCommendRequest;
	}

	public CarouselShopsRequest getCarouselShopsRequest() {
		return carouselShopsRequest;
	}

	public void setCarouselShopsRequest(CarouselShopsRequest carouselShopsRequest) {
		this.carouselShopsRequest = carouselShopsRequest;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
