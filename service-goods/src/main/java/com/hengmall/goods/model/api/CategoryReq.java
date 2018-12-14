package com.hengmall.goods.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2018/5/25.
 */
@ApiModel
public class CategoryReq {

    @ApiModelProperty(value = "token认证")
    String token;
    @ApiModelProperty(value = "类别等级，如：1、2")
    int categoryLevel;

    @Override
    public String toString() {
        return "CategoryReq{" +
                "token='" + token + '\'' +
                ", categoryLevel=" + categoryLevel +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getCategoryLevel() {
        return categoryLevel;
    }

    public void setCategoryLevel(int categoryLevel) {
        this.categoryLevel = categoryLevel;
    }
}
