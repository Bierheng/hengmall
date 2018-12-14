package com.server.entity.shops;

import org.hibernate.validator.constraints.NotEmpty;

import com.server.entity.basics.BasicsBean;

import io.swagger.annotations.ApiModelProperty;


/**
 * 店铺商品 entity  请求需要的参数
 * @author Administrator
 *
 */
public class SearchShopsProductRequest extends BasicsBean{
	
	@NotEmpty(message="店铺ID不能为空")
	@ApiModelProperty(value="店铺ID",required=true)
	private String shopsId;
	
	@NotEmpty(message="商品名称不能为空")
	@ApiModelProperty(value="商品名称",required=true)
	private String name;
	
	@ApiModelProperty(value="店铺板块id(板块id存在,店铺id一定不能为空)")
	private String plateId;
	
	@ApiModelProperty(value="商品ids(需要过滤的商品ids)")
	private String[] products;
	
	@ApiModelProperty(value="是否获取本店铺已有商品，false:获取本店铺的商品，true:获取本店铺以外的商品")
	private boolean oneself;
	
	public String getShopsId() {
		return shopsId;
	}

	public void setShopsId(String shopsId) {
		this.shopsId = shopsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}