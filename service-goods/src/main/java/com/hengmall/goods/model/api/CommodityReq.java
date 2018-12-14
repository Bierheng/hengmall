package com.hengmall.goods.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2018/5/25.
 */
@ApiModel
public class CommodityReq {

    @ApiModelProperty(value = "token认证")
    String token;
    @ApiModelProperty(value = "订单ID")
    int id;

    @Override
    public String toString() {
        return "ProductListReq{" +
                "token='" + token + '\'' +
                ", id=" + id +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
