package com.hengmall.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.hengmall.goods.model.SResourcesEntity;
import com.hengmall.goods.model.constitute.ResourceCarousel;

/**
 * Created by Administrator on 2018/5/24.
 */
@Repository
@Mapper
public interface SResourcesDao {

    //根据商品ID查询（图片、视频）资源地址
    @Select("select a.path,a.type from s_resources a,rel_product_detail b where " +
            "b.product_id = #{id} and b.type = #{type} and a.id = b.resources_id")
    List<ResourceCarousel> queryByproductId(@Param("id") int id, @Param("type") int type);

    /**
     * 保存资源信息
     */
    @Insert("insert into s_resources(path,type,ctime)values(#{path},#{type},now())")
    @Options(useGeneratedKeys=true, keyProperty = "s.id")
    void saveResources(@Param("path") String path, @Param("type") int access_url,
                       @Param("s") SResourcesEntity resourcesEntity);
}
