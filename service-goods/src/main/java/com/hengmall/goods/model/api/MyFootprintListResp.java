package com.hengmall.goods.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel
public class MyFootprintListResp extends ProductListResp {

    @ApiModelProperty(value = "浏览记录主键ID")
    int footprintId;
    @ApiModelProperty(value = "浏览时间")
    String browseDate;


    @Override
    public String toString() {
        return "MyFootprintListResp{" +
                "footprintId=" + footprintId +
                ", browseDate='" + browseDate + '\'' +
                '}' + "，" + super.toString();
    }

    public int getFootprintId() {
        return footprintId;
    }

    public void setFootprintId(int footprintId) {
        this.footprintId = footprintId;
    }

    public String getBrowseDate() {
        return browseDate;
    }

    public void setBrowseDate(String browseDate) {
        this.browseDate = browseDate;
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
