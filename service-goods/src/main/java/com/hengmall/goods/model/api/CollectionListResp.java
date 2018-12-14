package com.hengmall.goods.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel
public class CollectionListResp extends ProductListResp {

    @ApiModelProperty(value = "收藏夹主键ID")
    int collection_id;

    @Override
    public String toString() {
        return "CollectionListResp{" +
                "collection_id=" + collection_id +
                '}' + "，" + super.toString();
    }

    public int getCollection_id() {
        return collection_id;
    }

    public void setCollection_id(int collection_id) {
        this.collection_id = collection_id;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getHeadimg() {
        return super.getHeadimg();
    }

    @Override
    public void setHeadimg(String headimg) {
        super.setHeadimg(headimg);
    }

    @Override
    public double getPrice() {
        return super.getPrice();
    }

    @Override
    public void setPrice(double price) {
        super.setPrice(price*1.0/100);
    }
}
