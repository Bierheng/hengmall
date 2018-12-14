package com.hengmall.goods.model;

import java.util.List;

import lombok.Data;

@Data
public class ShopsCommend {

	private int id;    //店铺组合ID
	private String product_ids;    //模版商品ID数组
    private Double group_price;    //组合商品总价
    private String group_name;//组合名称
	private Data created_time;//创建时间
	private int status; //组合状态
	private int shops_id; //店铺ID
	private List<?> productList; //商品ID数组
}
