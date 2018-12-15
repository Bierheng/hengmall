package com.hengmall.user.model.shops;

import com.hengmall.user.model.basics.BasicsBean;

import io.swagger.annotations.ApiModelProperty;


/**
 * 店铺板块 entity  请求需要的参数
 * @author Administrator
 *
 */
public class ShopsPlateRequest extends BasicsBean{
	
	@ApiModelProperty(value = "店铺名称(条件查询)")
	private String shopsName;

	public String getShopsName() {
		return shopsName;
	}

	public void setShopsName(String shopsName) {
		this.shopsName = shopsName;
	}

	
}