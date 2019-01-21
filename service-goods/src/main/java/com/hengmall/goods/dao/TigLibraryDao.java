package com.hengmall.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.hengmall.goods.model.constitute.TigLibrary;

/**
 * Created by Administrator on 2018/5/24.
 */
@Repository
@Mapper
public interface TigLibraryDao {

    //按上级ID查询
    @Select("select * from tig_library where parent_id=#{pid}")
    List<TigLibrary> queryByPId(@Param("pid") int pid);
    
    //查询以及分类
    @Select("select * from tig_library where level =#{level}")
    List<TigLibrary> queryByLevel(@Param("level") int level);

}
