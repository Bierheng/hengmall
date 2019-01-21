package com.hengmall.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.hengmall.goods.model.constitute.SCategory;

/**
 * Created by Administrator on 2018/5/24.
 */
@Repository
@Mapper
public interface SCategoryDao {

    //按上级ID查询
    @Select("select * from s_category where pid=#{pid}")
    List<SCategory> queryByPId(@Param("pid") int pid);
    
    //查询以及分类
    @Select("select * from s_category where level =#{pid} and id != 1 and id != 7")
    List<SCategory> queryByLevel(@Param("pid") int pid);


    //查询热门品类
    @Select("select * from s_category a, hot_commodities b where b.category_id =a.id and b.type = #{type}")
    List<SCategory> queryHotCommoditiesByType(@Param("type") int type);


    //查询热门品类
    @Select("select * from s_category where pid=#{pid} and is_hot = 1")
    List<SCategory> getHotCategoryByPId(@Param("pid") int pid);


}
