package com.hengmall.goods.model.api;

import java.io.Serializable;
import java.util.List;

import com.hengmall.goods.model.PlatformTagEntity;
import com.hengmall.goods.model.constitute.Ads;
import com.hengmall.goods.model.constitute.Carousel;
import com.hengmall.goods.model.constitute.SCategory;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 首页展示
 */
@Data
@ApiModel
public class IndexResp implements Serializable {

    @ApiModelProperty(value = "图片轮播")
    List<Carousel> carousels;
    @ApiModelProperty(value = "一级分类小图标")
    List<SCategory> sCategories;
    @ApiModelProperty(value = "自定义活动模块")
    List<Ads> activityModules;
    @ApiModelProperty(value = "首页商品")
    List<?> productList;
    @ApiModelProperty(value = "首页平台资质")
    List<PlatformTagEntity> platformTagList;
}
