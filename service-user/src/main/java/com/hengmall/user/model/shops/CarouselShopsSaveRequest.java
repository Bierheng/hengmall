package com.hengmall.user.model.shops;

import org.hibernate.validator.constraints.NotEmpty;

import com.hengmall.user.model.basics.BasicsSaveBean;

import io.swagger.annotations.ApiModelProperty;


/**
 * 店铺轮播图修改、增加 entity  请求需要的参数
 * @author Administrator
 *
 */
public class CarouselShopsSaveRequest extends BasicsSaveBean{

	
	@NotEmpty(message="商品id不能为空")
	@ApiModelProperty(value="商品id",required=true)
	private String productId;
	
	@NotEmpty(message="店铺轮播图不能为空")
	@ApiModelProperty(value="店铺轮播图",required=true)
	private String path;
	
	@NotEmpty(message="店铺id不能为空")
	@ApiModelProperty(value="店铺id",required=true)
	private String shopsId;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getShopsId() {
		return shopsId;
	}

	public void setShopsId(String shopsId) {
		this.shopsId = shopsId;
	}
	
	
}