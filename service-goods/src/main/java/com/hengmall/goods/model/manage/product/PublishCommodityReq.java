package com.hengmall.goods.model.manage.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by Administrator on 2018/5/25.
 */
@ApiModel
public class PublishCommodityReq {

    @ApiModelProperty(value = "商品名称")
    String name;
    @ApiModelProperty(value = "商品描述")
    String desc;
    @ApiModelProperty(value = "商品展示图")
    List<String> showPic;
    @ApiModelProperty(value = "商品热点")
    String hotspot;
    @ApiModelProperty(value = "商品一级类目")
    int oneLevel;
    @ApiModelProperty(value = "商品二级类目")
    int twoLevel;
    @ApiModelProperty(value = "商品三级类目")
    int threeLevel;
    @ApiModelProperty(value = "国际店")
    int internationalStore;
    @ApiModelProperty(value = "商品图片集")
    List<String> commodityPic;


    @Override
    public String toString() {
        return "PublishCommodityReq{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", showPic=" + showPic +
                ", hotspot='" + hotspot + '\'' +
                ", oneLevel=" + oneLevel +
                ", twoLevel=" + twoLevel +
                ", threeLevel=" + threeLevel +
                ", internationalStore=" + internationalStore +
                ", commodityPic=" + commodityPic +
                '}';
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getShowPic() {
        return showPic;
    }

    public void setShowPic(List<String> showPic) {
        this.showPic = showPic;
    }

    public String getHotspot() {
        return hotspot;
    }

    public void setHotspot(String hotspot) {
        this.hotspot = hotspot;
    }

    public int getOneLevel() {
        return oneLevel;
    }

    public void setOneLevel(int oneLevel) {
        this.oneLevel = oneLevel;
    }

    public int getTwoLevel() {
        return twoLevel;
    }

    public void setTwoLevel(int twoLevel) {
        this.twoLevel = twoLevel;
    }

    public int getThreeLevel() {
        return threeLevel;
    }

    public void setThreeLevel(int threeLevel) {
        this.threeLevel = threeLevel;
    }

    public int getInternationalStore() {
        return internationalStore;
    }

    public void setInternationalStore(int internationalStore) {
        this.internationalStore = internationalStore;
    }

    public List<String> getCommodityPic() {
        return commodityPic;
    }

    public void setCommodityPic(List<String> commodityPic) {
        this.commodityPic = commodityPic;
    }
}
