package com.hengmall.goods.model;

import lombok.Data;

import java.util.Date;

/**
 * 收藏夹 实体类
 */
@Data
public class RelCollectionEntity {

    private int id;
    private int user_id;
    private int product_id;
    private String created_time;

}
