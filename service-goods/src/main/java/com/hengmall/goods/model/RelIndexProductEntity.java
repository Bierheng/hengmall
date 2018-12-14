package com.hengmall.goods.model;

import lombok.Data;

import java.util.Date;

/**
 * 首页商品（关联表） 实体类
 */
@Data
public class RelIndexProductEntity {

    private int id;
    private int uid;   //代理id
    private int productid;   //代理选择的商品id

 }
