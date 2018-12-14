package com.server.entity.persistence;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 分页类
 * @author Administrator
 * @param <T>
 *
 */
public class Page<T> {

	@ApiModelProperty(value = "数据列表")
	private List<T> data;
	
	@ApiModelProperty(value = "总记录条数")
	private long count;

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
}
