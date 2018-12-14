package com.server.entity.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class PagingReq {

	@ApiModelProperty("token认证")
	private String token;
	@ApiModelProperty(value = "当前页数")
	protected int page;
	@ApiModelProperty(value = "每页大小")
	protected int limit;

	@Override
	public String toString() {
		return "PagingReq{" + "token='" + token + '\'' + ", page=" + page + ", limit=" + limit + '}';
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
}
