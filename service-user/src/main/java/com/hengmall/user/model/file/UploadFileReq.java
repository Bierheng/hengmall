package com.server.entity.file;

import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: 上传商品上传参数
 * @Description: TODO
 * @author Administrator
 * @date 2018年5月28日 下午4:57:30
 */
public class UploadFileReq {
	@ApiModelProperty(value = "'1:图片,2:视频")
	protected int type;

	@ApiModelProperty(value = "每页大小")
	protected int pagesize;

}
