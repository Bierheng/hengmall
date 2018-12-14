package com.hengmall.goods.model.constitute;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 首页商品（关联表） 实体类
 */
@ApiModel
public class RelIndexProduct {

    @ApiModelProperty(value = "代理选择的商品id")
    private int productid;
    @ApiModelProperty(value = "商品展示图")
    private String headimg;
    @ApiModelProperty(value = "商品名称")
    private String name;

    @Override
    public String toString() {
        return "RelIndexProduct{" +
                "productid=" + productid +
                ", headimg='" + headimg + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
