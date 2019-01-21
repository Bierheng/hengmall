package com.hengmall.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hengmall.user.model.SResources;
import com.hengmall.user.model.SResourcesEntity;

/**
 * Created by Administrator on 2018/5/24.
 */
@Mapper
public interface SResourcesDao {

	// 根据商品ID查询（图片、视频）资源地址 用于返回给前端使用的特定格式
	@Select("select DISTINCT a.id as id,a.id as `name`, a.path as url from s_resources a,rel_product_detail b where "
			+ "b.product_id = #{id} and b.type = #{type} and a.id = b.resources_id")
	List<SResources> queryByproductId(@Param("id") int id, @Param("type") int type);
	
	// 根据商品ID查询（图片、视频）资源地址 用于返回给前端使用的特定格式 用于SKU图片
	@Select("select DISTINCT a.id as id,a.id as `name`, a.path as url from s_resources a where a.id = #{id}")
	SResources queryById(@Param("id") int id );

	// 根据商品ID查询（图片、视频）资源地址
	@Select("select DISTINCT a.id, a.path, b.type  from s_resources a,rel_product_detail b where "
			+ "b.product_id = #{id} and b.type = #{type} and a.id = b.resources_id")
	List<SResourcesEntity> queryByproductIdList(@Param("id") int id, @Param("type") int type);
	
	// 根据商品ID查询富文本框的数据资源地址
	@Select("select DISTINCT a.id, a.path, a.type  from s_resources a,rel_product_detail b where "
			+ "b.product_id = #{id} and b.type = #{type} and a.id = b.resources_id")
	SResourcesEntity queryBy(@Param("id") int id, @Param("type") int type);
	
	/** 保存资源信息 */
	@Insert({ "insert into s_resources(path, type) values(#{path}, #{type})" })
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int saveResources(SResourcesEntity resourcesEntity);
	
	/** 根据主键id修改数据 */
	@Update("update s_resources set path =#{edit.path},type = #{edit.type}"+"where id = #{edit.id}" )
	void updateResources(@Param("edit") SResourcesEntity resourcesEntity);
	
	/** 根据主键id删除商品资源数据 */
	@Update("delete from s_resources where id = #{id}" )
	void deleteResources(@Param("id") int id);

	// 根据商品ID查询（图片、视频）资源地址 用于返回给前端使用的特定格式
	@Select("select DISTINCT a.id as id,a.id as `name`, a.path as url from s_resources a,tb_topic_img b where "
			+ "b.topic_id = #{id} and b.type = #{type} and a.id = b.resources_id")
	List<SResources> queryByTopicId(@Param("id") int id,@Param("type") int type);
	
	// 根据商品ID查询（图片、视频）资源地址 用于返回给前端使用的特定格式
	@Select("select DISTINCT a.id as id,a.id as `name`, a.path as url from s_resources a,tb_topic_img b where "
			+ "b.topic_id = #{id} and b.type = #{type} and a.id = b.resources_id")
	List<SResourcesEntity> queryByTopicIdAll(@Param("id") int id,@Param("type") int type);
}
