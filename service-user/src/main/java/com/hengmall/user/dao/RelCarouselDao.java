package com.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.server.entity.RelCarouselEntity;

/**
 * Created by Administrator on 2018/5/24.
 */
@Repository
public interface RelCarouselDao {

	// 查询
//	@Select("select a.relid,b.path from rel_carousel a,s_resources b where a.relid=b.id and a.type = #{type}")
//	List<Carousel> queryByType(@Param("type") int type);
	
	/**
	 * @Title: 查询轮播图列表
	 * @Description: TODO
	 */
	@Select({ "<script>"
			+ "SELECT "
				+ "a.id, "
				+ "a.s_category_id,"
				+ "a.shops_id,"
				+ "a.shops_name,"
				+ "a.path,"
				+ "a.created_time,"
				+ "a.product_id,"
				+ "c.name AS 'categoryName',"
				+ "p.name AS 'productName'"
			+ " FROM "
				+ "rel_carousel a"
			+ " LEFT JOIN s_category c ON a.s_category_id = c.id"
			+ " LEFT JOIN s_product p ON a.product_id = p.id"
			+" order by a.created_time desc limit #{page},#{pagesize}"
			+"</script>" })
	List<RelCarouselEntity> carouselList(Map<String, Object> params);
	
	/**
	 *  新增轮播图
	 * @param sid  资源ID	
	 * @param type 轮播图类型
	 */
	@Insert("insert into rel_carousel(s_category_id,path,product_id,created_time,shops_id,shops_name)"
			+ "values(#{relCarouselEntity.s_category_id},#{relCarouselEntity.path},#{relCarouselEntity.product_id},"
			+ " now(),#{relCarouselEntity.shops_id},#{relCarouselEntity.shops_name})")
	int insert(@Param("relCarouselEntity") RelCarouselEntity relCarouselEntity);
	
	// 根据主键id删除数据
	@Delete("delete from rel_carousel where id = #{id}")
	int delById(@Param("id") int id);
       
	// 根据主键id修改数据
	@Update("update rel_carousel set s_category_id = #{edit.s_category_id},product_id =#{edit.product_id},path =#{edit.path},"
			+ " shops_id =#{edit.shops_id},shops_name =#{edit.shops_name} "
	+"where id = #{edit.id}")
	void updateById(@Param("edit") RelCarouselEntity editRelCarouselEntity);

	/**
	 * 获取轮播图记录总数
	 * @return
	 */
	@Select("select count(*) from rel_carousel")
	int countCarousel();
}
