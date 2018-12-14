package com.hengmall.goods.model;

import com.hengmall.goods.model.manage.PageReq;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName:
 * @Description: 后台管理，查询商品信息
 * @author Administrator
 * @date 2018年5月28日 下午2:34:32
 */
@ApiModel
public class ProductReq extends PageReq {

	@ApiModelProperty(value = "商品ID")
	int productId;
	@ApiModelProperty(value = "商品名称")
	String productName;
	@ApiModelProperty(value = "商品图片")
	String url;
	@ApiModelProperty(value = "商品价格")
	double price;

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("ProductReq{");
		buffer.append("productId='").append(productId).append("'");
		buffer.append("productName='").append(productName).append("'");
		buffer.append("url='").append(url).append("'");
		buffer.append("price='").append(price).append("'");
		buffer.append("}");
		return buffer.toString();
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
}
