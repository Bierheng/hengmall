package com.hengmall.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.hengmall.goods.model.RelProductDetailEntity;

@Repository
@Mapper
public interface RelProductDetailDao {

    //根据状态码查询
    @Select("select product_id productid ,resources_id resourcesid,type from rel_product_detail " +
            "where product_id = #{product_id} and type = #{type}")
    List<RelProductDetailEntity> findByType(@Param("product_id") int product_id, @Param("type") int type);


}
