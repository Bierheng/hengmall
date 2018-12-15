package com.hengmall.user.model.shops;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;

/**
 * 店铺轮播图删除 entity  请求需要的参数
 * @author Administrator
 *
 */
public class CarouselShopsDelRequest{
	
	@NotEmpty(message="店铺轮播图id不能为空")
	@ApiModelProperty(value="店铺轮播图id",required=true)
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}