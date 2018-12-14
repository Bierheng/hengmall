package com.hengmall.goods.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PlatformInfoEntity {

	@ApiModelProperty(value = "id")
    String id;
    @ApiModelProperty(value = "内容")
    String info;
	
}
