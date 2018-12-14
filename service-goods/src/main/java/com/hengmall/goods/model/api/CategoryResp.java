package com.hengmall.goods.model.api;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2018/5/25.
 */
@ApiModel
public class CategoryResp {

    @ApiModelProperty(value = "商品名称")
    String name;
    @ApiModelProperty(value = "商品图片")
    String headimg;
    @ApiModelProperty(value = "尺寸、颜色图片")
    JSONObject attr;
    @ApiModelProperty(value = "价格")
    double price;
    @ApiModelProperty(value = "库存")
    int stock;


    @Override
    public String toString() {
        return "CategoryResp{" +
                "name='" + name + '\'' +
                ", headimg='" + headimg + '\'' +
                ", attr=" + attr +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public JSONObject getAttr() {
        return attr;
    }

    public void setAttr(JSONObject attr) {
        this.attr = attr;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
