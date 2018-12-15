package com.hengmall.user.model.shops;

import org.hibernate.validator.constraints.NotEmpty;

import com.hengmall.user.model.basics.BasicsBean;

import io.swagger.annotations.ApiModelProperty;


/**
 * 店铺会员 entity  请求需要的参数
 * @author Administrator
 *
 */
public class ShopsUserRequest extends BasicsBean{
	
	@NotEmpty(message="店铺ID不能为空")
	@ApiModelProperty(value = "店铺ID",required=true)
	private String shopsId;

	public String getShopsId() {
		return shopsId;
	}

	public void setShopsId(String shopsId) {
		this.shopsId = shopsId;
	}
}