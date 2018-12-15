package com.hengmall.user.model.shops;
import com.hengmall.user.model.basics.BasicsBean;

import io.swagger.annotations.ApiModelProperty;


/**
 * 获取商品列表 entity  请求需要的参数
 * @author Administrator
 *
 */
public class ShopsProductListRequest extends BasicsBean{
	

	@ApiModelProperty(value="店铺id")
	private String shopsId;
	
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

	public String getPlateId() {
		return plateId;
	}

	public void setPlateId(String plateId) {
		this.plateId = plateId;
	}

	public String[] getProducts() {
		return products;
	}

	public void setProducts(String[] products) {
		this.products = products;
	}

	public boolean isOneself() {
		return oneself;
	}

	public void setOneself(boolean oneself) {
		this.oneself = oneself;
	}

}