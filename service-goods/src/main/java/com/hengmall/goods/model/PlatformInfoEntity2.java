package com.hengmall.goods.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PlatformInfoEntity2 {


    @ApiModelProperty(value = "内容")
    JSONArray info;
	
}
