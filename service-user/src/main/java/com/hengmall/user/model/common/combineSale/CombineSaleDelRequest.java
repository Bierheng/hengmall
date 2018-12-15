package com.hengmall.user.model.common.combineSale;

import io.swagger.annotations.ApiModelProperty;

/**
 * 拼单商品del  request
 * @author Administrator
 *
 */
public class CombineSaleDelRequest{

	@ApiModelProperty(value = "id")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
