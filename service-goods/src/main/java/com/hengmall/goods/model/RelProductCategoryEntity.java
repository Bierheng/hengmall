package com.hengmall.goods.model;

import lombok.Data;

/**
 * 商品表和类别表的关联表 实体类
 */
@Data
public class RelProductCategoryEntity {

    private int product_id;     //商品id
    private int category_id;   //商品类别id
    
}
