package com.hengmall.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.hengmall.goods.model.ShopsCommend;

@Repository
public interface ShopsCommendDao {

    //根据状态码查询
    @Select("select * from shops_commend where shops_id = #{shops_id} and JSON_CONTAINS(product_ids ,'"+"${id}"+"');")
    List<ShopsCommend> queryByShopsId(@Param("shops_id") int shops_id,@Param("id") int id);

}
