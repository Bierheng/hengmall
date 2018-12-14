package com.hengmall.goods.model.constitute;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 商品分类模块
 */
@ApiModel
public class CommodityCommodity {

    @ApiModelProperty(value = "商品名称")
    String name;
    @ApiModelProperty(value = "商品图标")
    String iconUrl;
    @ApiModelProperty(value = "点击商品跳转地址")
    String url;


    @Override
    public String toString() {
        return "Commodity{" +
                "name='" + name + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
