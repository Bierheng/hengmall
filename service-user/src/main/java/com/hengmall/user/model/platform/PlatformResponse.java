package com.hengmall.user.model.platform;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 平台模板列表 entity
 * @author Administrator
 *
 */
public class PlatformResponse{
	
	@ApiModelProperty(value = "平台模版ID")
	private String id;
	
	@ApiModelProperty(value = "文字的内容，或图片的存放地址")
	private String content;
	
	@ApiModelProperty(value = "类型，默认1：文字，2：图片")
	private int type;
	
	@ApiModelProperty(value = "分类id")
	private int typeId;
	
	@ApiModelProperty(value = "创建时间")
	private Date createdTime;
	
	@ApiModelProperty(value = "序号")
	private int orderNum;
	
	@ApiModelProperty(value = "平台一级分类")
	private String typeName;
	
	@ApiModelProperty(value = "店铺名称")
	private String shops_name;
	
	@ApiModelProperty(value = "平台模板商品")
	private List<PlatformProduct> platformProduct = new ArrayList<PlatformProduct>();

	@ApiModelProperty(value = "平台模板商品ids",hidden=true)
	private String[] platformIds;  //商品ids 
	
	@ApiModelProperty(value = "店铺ID")
	private int shops_id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShops_name() {
		return shops_name;
	}

	public void setShops_name(String shops_name) {
		this.shops_name = shops_name;
	}

	public int getShops_id() {
		return shops_id;
	}

	public void setShops_id(int shops_id) {
		this.shops_id = shops_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String[] getPlatformIds() {
		return platformIds;
	}

	public void setPlatformIds(String platformIds) {
		String[] platformIdsArr = {};
		if(platformIds != null && !platformIds.equals("")) {
			platformIds = platformIds.substring(0+1,platformIds.length()-1);
			platformIdsArr = platformIds.split(",");
		}
		this.platformIds = platformIdsArr;
	}

	public List<PlatformProduct> getPlatformProduct() {
		return platformProduct;
	}

	public void setPlatformProduct(List<PlatformProduct> platformProduct) {
		this.platformProduct = platformProduct;
	}

}