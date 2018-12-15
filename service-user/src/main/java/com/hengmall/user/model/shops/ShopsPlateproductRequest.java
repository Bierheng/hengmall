package com.hengmall.user.model.shops;

import com.hengmall.user.model.basics.BasicsBean;

import io.swagger.annotations.ApiModelProperty;


/**
 * 店铺板块商品 entity  请求需要的参数
 * @author Administrator
 *
 */
public class ShopsPlateproductRequest extends BasicsBean{
	
	@ApiModelProperty(value = "板块ID(为空找所有，否则找对应板块的商品)")
	private String plateId;

	public String getPlateId() {
		return plateId;
	}

	public void setPlateId(String plateId) {
		this.plateId = plateId;
	}

}