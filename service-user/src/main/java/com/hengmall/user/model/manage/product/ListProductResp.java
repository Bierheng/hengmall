package com.server.entity.manage.product;

import java.util.List;

import com.server.entity.manage.PageResq;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: 后台管理，查询商品列表返回参数
 * @Description:
 * @author Administrator
 * @date 2018年5月28日 下午2:34:32
 */
@ApiModel
public class ListProductResp extends PageResq {
	@ApiModelProperty(value = "商品列表")
	List<ProductBean2> productList;

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("ProductListResp{");
		buffer.append("productList='").append(productList).append("'");
		buffer.append("total='").append(total).append("'");
		buffer.append("}");
		return buffer.toString();
	}

	public List<ProductBean2> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductBean2> productList) {
		this.productList = productList;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
