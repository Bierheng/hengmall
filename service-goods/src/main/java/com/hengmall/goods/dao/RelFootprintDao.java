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
public interface RelFootprintDao {

    @Select("select id from rel_footprint where user_id=#{user_id} and product_id=#{product_id} limit 1")
    RelCollectionEntity queryByUserIdAndProductId(@Param("user_id") int user_id, @Param("product_id") int product_id);


    //新增
    @Insert("insert into rel_footprint(user_id,product_id,created_time)values(#{user_id},#{product_id},now())")
    void insert(@Param("user_id") int user_id, @Param("product_id") int product_id);


    //新增
    @Insert("insert into rel_footprint(user_id, product_id,created_time) SELECT #{user_id}, #{product_id},now() " +
            "from rel_footprint where not exists (select * from rel_footprint where user_id = #{user_id} and " +
            "product_id=#{product_id} and Date(created_time) = #{today} limit 1) limit 1")
    void removalInsert(@Param("user_id") int user_id, @Param("product_id") int product_id,
                       @Param("today") String today);


    //根据主键ID删除数据
    @Delete("delete from rel_footprint where id in(${id})")
    void delByIds(@Param("id") String id);


}
