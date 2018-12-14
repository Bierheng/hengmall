package com.server.entity.shops.examine;

import org.hibernate.validator.constraints.NotEmpty;

import com.server.entity.basics.BasicsSaveBean;

import io.swagger.annotations.ApiModelProperty;

/**
 * 店铺审核操作   request
 * @author Administrator
 *
 */
public class ConfirmRequest extends BasicsSaveBean{

	@NotEmpty(message="id不能为空")
	@ApiModelProperty(value="id")
	private String id;
	
	@ApiModelProperty(value="类型，1：同意，2：拒绝")
	private Integer type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	
}
