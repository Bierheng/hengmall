package com.hengmall.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.hengmall.goods.model.api.ActivityModule;

@Repository
@Mapper
public interface ActivityModuleDao {

    //根据级别id查询活动模块
//    @Select("select * from activity_module where category_id=#{category_id}")
//    List<ActivityModule> queryByCategoryId(@Param("category_id") int category_id);
    
    //根据级别id查询活动模块
    @Select("select * from ads where category_id=#{category_id}")
    List<ActivityModule> queryByCategoryId(@Param("category_id") int category_id);


}
