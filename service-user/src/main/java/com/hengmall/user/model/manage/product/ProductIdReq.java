package com.hengmall.user.model.manage.product;

import com.hengmall.user.model.manage.PageReq;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName:
 * @Description: 后台管理，查询商品列表信息
 * @author Administrator
 * @date 2018年5月28日 下午2:34:32
 */
@ApiModel
public class ProductIdReq extends PageReq {

	@ApiModelProperty(value = "分类ID")
	int typeId;

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("ProductReq{");
		buffer.append("typeId='").append(typeId).append("'");
		buffer.append("}");
		return buffer.toString();
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
}
