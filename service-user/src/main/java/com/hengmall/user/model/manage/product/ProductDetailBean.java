package com.hengmall.user.model.manage.product;

import com.hengmall.user.model.manage.PageReq;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: 商品详情信息
 * @Description:
 * @author 
 * @date 
 */
@ApiModel
public class ProductDetailBean extends PageReq {
	
	public static final int Introduce_Type = 1;
	public static final int Detail_Type = 2;
	public static final int Img_Type = 3;
	
	@ApiModelProperty(value = "详情id")
	private int id;
	@ApiModelProperty(value = "商品id")
	private int productid;
	@ApiModelProperty(value = "资源ID")
	private int resourcesid;
	@ApiModelProperty(value = "资源类型 1商品介绍资源，2商品详情资源，3商品图片")
	private int type;

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("ProductDetailBean{");
		buffer.append("id='").append(id).append("'");
		buffer.append("productid='").append(productid).append("'");
		buffer.append("resourcesid='").append(resourcesid).append("'");
		buffer.append("type='").append(type).append("'");
		buffer.append("}");
		return buffer.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public int getResourcesid() {
		return resourcesid;
	}

	public void setResourcesid(int resourcesid) {
		this.resourcesid = resourcesid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
