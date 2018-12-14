package com.hengmall.goods.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class ShopsInfo {
	
	@ApiModelProperty(value = "店铺ID")
    private Integer id;
	@ApiModelProperty(value = "店铺主人ID")
    private Integer shopsId;
	@ApiModelProperty(value = "店铺头像")
    private String url;
	@ApiModelProperty(value = "国籍")
    private String national;
	@ApiModelProperty(value = "粉丝数")
    private Integer fansNum;
	@ApiModelProperty(value = "是否已关注：1是，2否")
    private Integer isAttention;
	@ApiModelProperty(value = "店铺名称")
    private String shopsName;
	@ApiModelProperty(value = "店铺描述")
    private String describe;
	@ApiModelProperty(value = "店铺轮播图")
    private List<ShopCarouselEntity> carouselList;
	

	
	@Override
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("ShopsInfo{");
		buffer.append("shopsId='").append(shopsId).append("'");
		buffer.append("url='").append(url).append("'");
		buffer.append("fansNum='").append(fansNum).append("'");
		buffer.append("national='").append(national).append("'");
		buffer.append("isAttention='").append(isAttention).append("'");
		buffer.append("shopsName='").append(shopsName).append("'");
		buffer.append("describe='").append(describe).append("'");
		buffer.append("}");
		return buffer.toString();
	}



	public List<ShopCarouselEntity> getCarouselList() {
		return carouselList;
	}



	public void setCarouselList(List<ShopCarouselEntity> carouselList) {
		this.carouselList = carouselList;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getShopsId() {
		return shopsId;
	}



	public void setShopsId(Integer shopsId) {
		this.shopsId = shopsId;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public String getNational() {
		return national;
	}



	public void setNational(String national) {
		this.national = national;
	}
	
	

	public Integer getFansNum() {
		return fansNum;
	}



	public void setFansNum(Integer fansNum) {
		this.fansNum = fansNum;
	}

	public Integer getIsAttention() {
		return isAttention;
	}



	public void setIsAttention(Integer isAttention) {
		this.isAttention = isAttention;
	}



	public String getShopsName() {
		return shopsName;
	}



	public void setShopsName(String shopsName) {
		this.shopsName = shopsName;
	}



	public String getDescribe() {
		return describe;
	}



	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
}