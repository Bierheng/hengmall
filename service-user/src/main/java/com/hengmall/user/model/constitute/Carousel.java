package com.server.entity.constitute;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 轮播图模块
 */
@ApiModel
public class Carousel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "商品关联ID")
	int relid;
	@ApiModelProperty(value = "图片地址")
	String path;

	@Override
	public String toString() {
		return "Carousel{" + "relid=" + relid + ", path='" + path + '\'' + '}';
	}

	public int getRelid() {
		return relid;
	}

	public void setRelid(int relid) {
		this.relid = relid;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
