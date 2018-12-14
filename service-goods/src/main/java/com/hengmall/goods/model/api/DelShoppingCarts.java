package com.hengmall.goods.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by Administrator on 2018/5/25.
 */
@ApiModel
public class DelShoppingCarts {

    @ApiModelProperty(value = "token认证")
    String token;
    @ApiModelProperty(value = "需要删除购物车的订单ID集合")
    List<Integer> ids;

    @Override
    public String toString() {
        return "DelShoppingCarts{" +
                "token='" + token + '\'' +
                ", ids=" + ids +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
