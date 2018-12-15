package com.hengmall.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.hengmall.user.model.Ads;

/**
 * Created by  wuhengbin on 2018/5/24.
 */
@Repository
public interface AdsDao {

	// 根据id查询
	@Select("select "
			+ "id AS \"id\","
			+ "product_id AS \"productId\","
			+ "resource AS \"resource\","
			+ "type AS \"type\","
			+ "shops_id AS \"shopsId\","
			+ "category_id AS \"categoryId\","
			+ "created_time AS \"createdTime\""
			+ "from ads where id = #{id}")
	Ads queryById(@Param("id") int id);
	
	// 根据id查询
	@Select("select "
			+ "a.id AS \"id\","
			+ "a.product_id AS \"productId\","
			+ "a.resource AS \"resource\","
			+ "a.type AS \"type\","
			+ "a.shops_id AS \"shopsId\","
			+ "b.shops_name AS \"shopsName\","
			+ "a.category_id AS \"categoryId\","
			+ "a.created_time AS \"createdTime\""
			+ " from ads a left join shops_location b on a.shops_id = b.id ORDER BY a.created_time DESC")
	List<Ads> queryForList();

	// 新增
	@Insert("insert into ads ( product_id, resource, type,shops_id, category_id , created_time)"
			+ "values ( #{insert.productId}, #{insert.resource}, #{insert.type},#{insert.shopsId}, #{insert.categoryId}, now())")
	int insert(@Param("insert") Ads insert);

	// 根据主键id删除数据
	@Delete("delete from ads where id = #{id}")
	int delById(@Param("id") int id);
       
	// 根据主键id修改数据
	@Update("update ads set product_id = #{record.productId},shops_id = #{record.shopsId},"
			+ "resource = #{record.resource},type = #{record.type},"
			+ "category_id  = #{record.categoryId} where id=#{record.id}")
	int updateById(@Param("record") Ads record);
	
	/**
	 * 获取抢购活动总数
	 * @return
	 */
	@Select("select count(*) from ads")
	int countAds();
}
