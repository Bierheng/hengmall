package com.hengmall.goods.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2018/5/25.
 */
@ApiModel
public class ProductSearchResp {

    @ApiModelProperty(value = "需要搜索的关键词")
    String keys;

    @Override
    public String toString() {
        return "ProductSerarchResp{" +
                "keys='" + keys + '\'' +
                '}';
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }
}
