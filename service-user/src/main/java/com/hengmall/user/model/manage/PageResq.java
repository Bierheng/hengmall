package com.hengmall.user.model.manage;

import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: 分页参数返回
 * @Description: TODO
 * @author Administrator
 * @date 2018年5月28日 下午4:57:30
 */
public class PageResq {
	@ApiModelProperty(value = "记录总数")
	protected int total;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
