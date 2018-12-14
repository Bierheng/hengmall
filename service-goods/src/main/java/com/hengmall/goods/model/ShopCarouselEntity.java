package com.hengmall.goods.model;

import lombok.Data;

import java.util.Date;

/**
 * 店铺图片轮播 实体类
 */
@Data
public class ShopCarouselEntity {

	private int id;   
    private int product_id;     //商品关联的id
    private String path;   //图片地址
    private int shops_id;    //店铺id
    private Date created_time;    //创建时间


}
