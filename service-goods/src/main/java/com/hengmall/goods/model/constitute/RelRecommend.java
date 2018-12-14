package com.hengmall.goods.model.constitute;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 推荐商品 实体类
 */
@ApiModel
public class RelRecommend {

    @ApiModelProperty(value = "推荐商品Id")
    private int productid;
    @ApiModelProperty(value = "商品展示图")
    private String headimg;
    @ApiModelProperty(value = "商品名称")
    private String name;
    @ApiModelProperty(value = "商品价格")
    private double price;

    @Override
    public String toString() {
        return "RelRecommend{" +
                "productid=" + productid +
                ", headimg='" + headimg + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
