package com.hengmall.user.model.constitute;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 资源文件图片或视频
 */
@ApiModel
public class ResourceCarousel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "资源类型；1：图片，2：视频")
	int type;
	@ApiModelProperty(value = "图片地址")
	String path;

	@Override
	public String toString() {
		return "ResourceCarousel{" + "type=" + type + ", path='" + path + '\'' + '}';
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
