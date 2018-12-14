package com.hengmall.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.hengmall.goods.model.constitute.Ads;


@Repository
public interface AdsDao {

    //根据状态码查询
    @Select("select b.id as productId,a.resource,a.type,a.shops_id from ads a left join tb_shops_product b on a.product_id = b.product_id and a.shops_id = b.shops_id where category_id = #{category_id}")
    List<Ads> queryByCategoryId(@Param("category_id") int category_id);



}
