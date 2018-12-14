package com.hengmall.goods.model;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PlatformTagEntity {
	
	@ApiModelProperty(value = "编号")
	int id;
	@ApiModelProperty(value = "平台标签图标")
	String url;
	@ApiModelProperty(value = "平台标签名称")
	String name;
	@ApiModelProperty(value = "创建时间")
	Date created_time;
	
}
