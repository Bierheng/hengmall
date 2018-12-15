package com.hengmall.user.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2018/5/25.
 */
@ApiModel
public class ProductListReq {

	@ApiModelProperty(value = "token认证")
	String token;
    @ApiModelProperty(value = "商品类别主键ID、类目商品列表接口；1：首页，2：免税店，3：轻奢，4：女装，5：鞋包，6：母婴....")
    int id;
    @ApiModelProperty(value = "排序标识；默认1：综合，2：新品，3：销量，4：价格")
    int orderStatus;

	@Override
	public String toString() {
		return "ProductListReq{" + "token='" + token + '\'' + ", id=" + id + '}';
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
}
