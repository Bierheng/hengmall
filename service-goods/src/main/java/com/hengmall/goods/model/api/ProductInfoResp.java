package com.hengmall.goods.model.api;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.hengmall.goods.model.constitute.ResourceCarousel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Administrator on 2018/5/25.
 */
@Data
@ApiModel
public class ProductInfoResp implements Serializable {

    @ApiModelProperty(value = "店铺商品ID")
    int shops_product_id;
    @ApiModelProperty(value = "商品名称")
    String name;
    @ApiModelProperty(value = "商品图片或视频资源轮播")
    List<ResourceCarousel> resourceCarousels;
    @ApiModelProperty(value = "尺寸、颜色图片")
    JSONObject attr;
    @ApiModelProperty(value = "价格")
    double price;
    @ApiModelProperty(value = "美元价格")
    double newprice;
    @ApiModelProperty(value = "原价")
    double oldprice;
    @ApiModelProperty(value = "快递运费")
    double freight;
    @ApiModelProperty(value = "库存")
    int stock;
    @ApiModelProperty(value = "拼单需满足的人数")
    int combineNum;
    @ApiModelProperty(value = "是否支持退货；1：支持，0：不支持")
    int isAllowrefund;
    @ApiModelProperty(value = "收藏状态；0：未收藏，1：已收藏")
    int collectionStatus;
    @ApiModelProperty(value = "暂做缩略图")
    String headImg;
    @ApiModelProperty(value = "商品说明；如：[{\"title\":\"退\",\"content\":\"7天无理由退换\"}]")
    List desc;
    @ApiModelProperty(value = "店铺商品国旗")
    String state_icon;
    @ApiModelProperty(value = "店铺商品发货国籍")
    String state_name;
    @ApiModelProperty(value = "月销量")
    int sales;
    @ApiModelProperty(value = "商品底部图片")
    String endimg;
    @ApiModelProperty(value = "用户ID")
    int user_id;
    
    
    @ApiModelProperty(value = "限时抢购时，所显示的实际支付价格")
    double actualPrice;
    @ApiModelProperty(value = "限时抢购状态标识，0：未抢购，1：已抢购")
    int flag;
    @ApiModelProperty(value = "店铺Id")
    int shopsId;
    @ApiModelProperty(value = "店铺名称")
    String shopsName;
    @ApiModelProperty(value = "店铺头像")
    String shopsImg;
    @ApiModelProperty(value = "商品资质数据")
    List<?> shopsRigthList;
    @ApiModelProperty(value = "店铺商品组合")
    List<?> shopsCommendList;
    @ApiModelProperty(value = "店铺商品猜你喜欢推荐列表")
    List<?> sProductEntities;
    @ApiModelProperty(value = "店铺商品详情图片资源")
    List<?> detailResource;
    @ApiModelProperty(value = "店铺商品所有评价")
    List<?> sAppraiseResp;

}
