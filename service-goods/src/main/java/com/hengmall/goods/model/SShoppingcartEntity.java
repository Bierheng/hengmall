package com.hengmall.goods.model;

import lombok.Data;

/**
 * 购物车 实体类
 */
@Data
public class SShoppingcartEntity {

    private int id;
    private int product_id;     //购物车中商品id
    private String attr;       //选择的商品颜色
    private int sum;        //选择的数量
    private int user_id;         //用户id
    private int price;         //商品价格
    private String name;
    private String headimg;
    private String created_time;
    private int shops_id;
    private int sku_index;
}
