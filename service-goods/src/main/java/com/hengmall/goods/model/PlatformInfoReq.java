package com.hengmall.goods.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PlatformInfoReq {

	@ApiModelProperty(value = "token认证")
    String token;
    @ApiModelProperty(value = "平台一级分类ID")
    Integer typeId;
	
}
