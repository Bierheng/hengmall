package com.hengmall.user.model.file;

import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: 上传商品返回参数
 * @Description: TODO
 * @author Administrator
 * @date 2018年5月28日 下午4:57:30
 */
public class UploadFileResq {
	@ApiModelProperty(value = "资源id")
	private int resourcesid;

	@ApiModelProperty(value = "资源地址，对象存储地址")
	protected int path;

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("UploadFileResq{");
		buffer.append("resourcesid='").append(resourcesid).append("'");
		buffer.append("path='").append(path).append("'");
		buffer.append("}");
		return buffer.toString();
	}
}
