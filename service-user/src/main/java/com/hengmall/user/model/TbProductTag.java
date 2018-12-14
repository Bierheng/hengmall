package com.server.entity;

import io.swagger.annotations.ApiModelProperty;

public class TbProductTag {
	
    private Integer id;
	@ApiModelProperty(value = "商品ID")
    private Integer productId;
	@ApiModelProperty(value = "标签ID")
    private Integer tagId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }
}