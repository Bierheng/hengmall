package com.hengmall.user.model.manage.product.isnew;

import io.swagger.annotations.ApiModelProperty;

/**
 * 商品特色服务
 * @author Administrator
 *
 */
public class ShopsRightEntity {

	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty(value = "店铺资质图标")
	private String url;
	
	@ApiModelProperty(value = "资质名称")
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
