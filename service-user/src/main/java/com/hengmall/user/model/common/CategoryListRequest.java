package com.server.entity.common;

import com.server.entity.basics.BasicsBean;

import io.swagger.annotations.ApiModelProperty;


/**
 * 商品分类列表 entity  请求需要的参数
 * @author Administrator
 *
 */
public class CategoryListRequest extends BasicsBean{
	
	@ApiModelProperty(value = "类别级别1或2(需要按商品分类级别查找就填)")
    private Integer level;

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	
}