package com.hengmall.user.model.platform;

import java.util.Arrays;

import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.NotEmpty;

import com.hengmall.user.model.basics.BasicsSaveBean;

import io.swagger.annotations.ApiModelProperty;


/**
 * 平台模板修改、添加 entity  请求需要的参数
 * @author Administrator
 *
 */
public class PlatformSaveRequest extends BasicsSaveBean{
	
	@NotEmpty(message="模板内容不能为空")
	@ApiModelProperty(value="模板内容",required=true)
	private String content;
	
	@NotEmpty(message="类型不能为空")
	@ApiModelProperty(value="类型，默认1：文字，2：图片",required=true)
	private String type;
	
	@Digits (integer=6, fraction=0,message="序号只能是正整数并且不能大于{integer}位数")
	@ApiModelProperty(value="序号(添加时不填)",required=true)
	private int orderNum;
	
	@NotEmpty(message="必须选择一个一级分类")
	@ApiModelProperty(value="一级分类id",required=true)
	private String typeId;

	@ApiModelProperty(value = "平台商品id，多个")
	private String[] platformIds;
	
	@NotEmpty(message="必须选择一个店铺")
	@ApiModelProperty(value="一级分类id",required=true)
	private String shops_id;
	
	public String getShops_id() {
		return shops_id;
	}

	public void setShops_id(String shops_id) {
		this.shops_id = shops_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getPlatformIds() {
		return Arrays.toString(platformIds);
	}

	public void setPlatformIds(String[] platformIds) {
		this.platformIds = platformIds;
	}
	
	
}