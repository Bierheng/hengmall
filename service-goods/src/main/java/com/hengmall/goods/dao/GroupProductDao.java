package com.hengmall.goods.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.hengmall.goods.model.ShopsCommend;

@Repository
@Mapper
public interface GroupProductDao {

    //根据id查询
    @Select("select * from shops_commend where id = #{id}")
    ShopsCommend queryById(@Param("id") int id);
}
