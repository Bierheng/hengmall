package com.hengmall.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.hengmall.goods.model.ShopsRight;

@Repository
@Mapper
public interface ShopsRightDao {

    //根据状态码查询
    @Select("select * from shops_right where shops_id = #{shops_id}")
    List<ShopsRight> queryByShopsId(@Param("shops_id") int shops_id);

    //根据状态码查询
    @Select("select * from shops_right where id in ( ${rights} )")
    List<ShopsRight> queryByShopsId2(@Param("rights") String rights);
}
