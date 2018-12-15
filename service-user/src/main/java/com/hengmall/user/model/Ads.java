package com.hengmall.user.model;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class Ads {
    private Integer id;
	@ApiModelProperty(value = "产品ID")
    private Integer productId;
	@ApiModelProperty(value = "店铺ID")
    private Integer shopsId;
	@ApiModelProperty(value = "资源")
    private String resource;
	@ApiModelProperty(value = "资源类型；默认1：图片，2：视频")
    private Integer type;
	@ApiModelProperty(value = "一级分类类别ID")
    private Integer categoryId;
	@ApiModelProperty(value = "创建时间")
    private Date createdTime;
	@ApiModelProperty(value = "店铺名称")
    private String shopsName;
	

    public String getShopsName() {
		return shopsName;
	}

	public void setShopsName(String shopsName) {
		this.shopsName = shopsName;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource == null ? null : resource.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

	public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getShopsId() {
		return shopsId;
	}

	public void setShopsId(Integer shopsId) {
		this.shopsId = shopsId;
	}
}