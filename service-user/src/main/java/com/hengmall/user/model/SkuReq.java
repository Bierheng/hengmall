package com.hengmall.user.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class SkuReq {
	
	@ApiModelProperty(value = "SKU属性值")
    private List<String> vals;
	@ApiModelProperty(value = "SKU属性名")
    private String name;
	
	public List<String> getVals() {
		return vals;
	}
	public void setVals(List<String> vals) {
		this.vals = vals;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}