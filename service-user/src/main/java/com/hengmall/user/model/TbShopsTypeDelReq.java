package com.server.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;

/**
 * 分类删除 request
 * @author Administrator
 *
 */
public class TbShopsTypeDelReq {

	@NotNull (message="id不能为空")
	@ApiModelProperty(value = "id")
	private int id;
	
	@NotNull (message="shops_id不能为空")
	@ApiModelProperty(value = "shops_id")
	private int shops_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getShops_id() {
		return shops_id;
	}

	public void setShops_id(int shops_id) {
		this.shops_id = shops_id;
	}

}
