package com.server.entity.manage.product;

import com.server.entity.manage.PageReq;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: 后台管理，查询商品列表请求参数
 * @Description: 后台管理，展示商品列表(筛选，排序，分页等)
 * @author Administrator
 * @date 2018年5月28日 下午2:34:32
 */
@ApiModel
public class ListProductReq extends PageReq {

	@ApiModelProperty(value = "token认证")
	String token;
	@ApiModelProperty(value = "商品名称")
	String productName;

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("ProductListReq{");
		buffer.append("token='").append(token).append("'");
		buffer.append("productName='").append(productName).append("'");
		buffer.append("page='").append(page).append("'");
		buffer.append("pagesize='").append(pagesize).append("'");
		buffer.append("}");
		return buffer.toString();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}
