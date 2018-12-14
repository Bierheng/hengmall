package com.hengmall.goods.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.hengmall.goods.model.RelCollectionEntity;

/**
 * Created by Administrator on 2018/5/24.
 */
@Repository
public interface RelCollectionDao {

    @Select("select id from rel_collection where user_id=#{user_id} and product_id=#{product_id} limit 1")
    RelCollectionEntity queryByUserIdAndProductId(@Param("user_id") int user_id, @Param("product_id") int product_id);


    //新增
    @Insert("insert into rel_collection(user_id,product_id,created_time)values(#{user_id},#{product_id},now())")
    void insert(@Param("user_id") int user_id, @Param("product_id") int product_id);


    //根据主键ID删除数据
    @Delete("delete from rel_collection where id in(${id})")
    void delByIds(@Param("id") String id);


    //查询是否收藏过该商品
    @Select("select count(*) from rel_collection where user_id=#{user_id} and product_id=#{product_id} limit 1")
    int queryIsCollection(@Param("user_id") int user_id, @Param("product_id") int id);


    //删除收藏的商品
    @Delete("delete from rel_collection where user_id=#{user_id} and product_id=#{id}")
    void delByProductId(@Param("user_id") int user_id, @Param("id") int id);
}
