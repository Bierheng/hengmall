package com.hengmall.goods.model;

import io.swagger.annotations.ApiModelProperty;


//给前端传输数据使用模版
public class ShopsTypeBean {
	

	@ApiModelProperty(value = "平台分类ID")
    private Integer typeId;
	@ApiModelProperty(value = "平台分类名称")
    private String typeName;
	@ApiModelProperty(value = "图标")
    private String url;
	@ApiModelProperty(value = "店铺主人ID")
    private Integer shopsId;


	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("ShopsTypeBean{");
		buffer.append("typeId='").append(typeId).append("'");
		buffer.append("typeName='").append(typeName).append("'");
		buffer.append("url='").append(url).append("'");
		buffer.append("shopsId='").append(shopsId).append("'");
		buffer.append("}");
		return buffer.toString();
	}


	public Integer getTypeId() {
		return typeId;
	}


	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}


	public String getTypeName() {
		return typeName;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public Integer getShopsId() {
		return shopsId;
	}


	public void setShopsId(Integer shopsId) {
		this.shopsId = shopsId;
	}
	
    
}