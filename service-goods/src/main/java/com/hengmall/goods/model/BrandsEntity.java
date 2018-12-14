package com.hengmall.goods.model;


import lombok.Data;

/**
 * 品牌店 实体类
 */
@Data
public class BrandsEntity {

    private int id;
    private String name;
    private String icon;
    private String desc;
    private String created_time;
    private int flag;

}
