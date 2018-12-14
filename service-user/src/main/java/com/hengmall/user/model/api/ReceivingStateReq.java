package com.server.entity.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2018/5/25.
 */
@ApiModel
public class ReceivingStateReq extends PagingReq {

	@ApiModelProperty(value = "0:待付款，1:待发货，2:待收货，3:已收货，99:所有订单")
	int receivingFlag;

	public int getReceivingFlag() {
		return receivingFlag;
	}

	public void setReceivingFlag(int receivingFlag) {
		this.receivingFlag = receivingFlag;
	}

	@Override
	public String toString() {
		return "ReceivingStateReq{" + "receivingFlag=" + receivingFlag + '}' + "，" + super.toString();
	}

	@Override
	public String getToken() {
		return super.getToken();
	}

	@Override
	public void setToken(String token) {
		super.setToken(token);
	}

	@Override
	public int getPage() {
		return super.getPage();
	}

	@Override
	public void setPage(int page) {
		super.setPage(page);
	}

	@Override
	public int getLimit() {
		return super.getLimit();
	}

	@Override
	public void setLimit(int limit) {
		super.setLimit(limit);
	}
}
