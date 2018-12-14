package com.hengmall.goods.model.manage.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: 上传商品返回 
 * @Description: TODO
 * @author Administrator
 * @date 2018年5月30日 上午11:25:32 
 */
@ApiModel
public class UploadProductResp
{
	@ApiModelProperty(value = "上传生成的商品记录信息")
	ProductBean productBean;

	@Override
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("UploadProductResp{");
		buffer.append("productBean='").append(productBean).append("'");
		buffer.append("}");
		return buffer.toString();
	}

	public ProductBean getProductBean()
	{
		return productBean;
	}

	public void setProductBean(ProductBean productBean)
	{
		this.productBean = productBean;
	}
}
