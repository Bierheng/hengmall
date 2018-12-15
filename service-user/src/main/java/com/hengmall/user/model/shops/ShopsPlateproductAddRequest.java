package com.hengmall.user.model.shops;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;


/**
 * 店铺板块商品添加 entity  请求需要的参数
 * @author Administrator
 *
 */
public class ShopsPlateproductAddRequest{
	
	@ApiModelProperty(value = "板块id",required=true)
	@NotEmpty(message="板块id不能为空")
	private String plateId;

	@ApiModelProperty(value = "店铺id",required=true)
	@NotEmpty(message="店铺id不能为空")
	private String shopsId;
	
	@ApiModelProperty(value = "商品ids",required=true)
	@NotEmpty(message="商品不能为空")
	private String[] productIds;

	

	public String getPlateId() {
		return plateId;
	}

	public void setPlateId(String plateId) {
		this.plateId = plateId;
	}

	public String getShopsId() {
		return shopsId;
	}

	public void setShopsId(String shopsId) {
		this.shopsId = shopsId;
	}

	public String[] getProductIds() {
		return productIds;
	}

	public void setProductIds(String[] productIds) {
		this.productIds = productIds;
	}
	
}