package com.hengmall.goods.model;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.Date;

/**
 * 商品详情 （主要展示图片和视频） 实体类
 */
@Data
public class RelProductDetailEntity {


    //1商品介绍资源，2商品详情资源，3商品图片
    public static final int Introduce_Type = 1;
    public static final int Details_Type = 2;
    public static final int Commodity_Type = 3;

    private int id;
    private int product_id;    //商品id
    private int resources_id;    //资源id
    private int productid;    //商品id
    private int resourcesid;    //资源id
    private int type;    //1商品介绍资源，2商品详情资源

}
