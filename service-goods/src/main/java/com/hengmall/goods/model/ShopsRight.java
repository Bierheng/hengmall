package com.hengmall.goods.model;

import lombok.Data;

@Data
public class ShopsRight {

	private int id;    //店铺资质标签ID
    private String url;    //店铺资质图标
    private String name;//资质名称
	private Data created_time;//创建时间
	private String content; //资质说明内容
	private int shops_id; //店铺ID
}
