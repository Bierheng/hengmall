package com.hengmall.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.hengmall.user.model.FlashSale;
import com.hengmall.user.model.FlashSaleBean;

/**
 * Created by  wuhengbin on 2018/5/24.
 */
@Repository
public interface FlashSaleDao {

	// 根据id查询
	@Select("select * from flash_sale where id = #{id}")
	FlashSale queryById(@Param("id") int id);
	
	// 根据id查询
	@Select("select * from flash_sale where id = #{id}")
	FlashSaleBean queryByIdString(@Param("id") int id);

	// 新增
	@Insert("insert into flash_sale(id,product_id,title,price,attribute,icon,status,stock,timeliness_id,created_time,shops_id,product_name,shops_name)"
			+ "values(#{insert.id},#{insert.product_id},#{insert.title},#{insert.price},convert("+"\'${insert.attribute}\'"+" using utf8mb4),#{insert.icon},"
					+ " #{insert.status},#{insert.stock},#{insert.timeliness_id},now(),#{insert.shops_id},#{insert.product_name},#{insert.shops_name})")
	int insert(@Param("insert") FlashSale insert);
	
	/**
	 * @Title: 查询抢购活动列表
	 * @Description: TODO
	 */
	@Select({ "<script>", "select a.id,a.product_id,a.title,a.price,a.attribute,a.icon,a.status,a.stock,a.timeliness_id,a.updated_time,a.created_time,a.product_name,"
			+ " a.shops_name,a.shops_id from flash_sale a LEFT JOIN tb_shops_product b ON a.product_id = b.id  ",
			  " order by a.created_time desc", "</script>" })
	List<FlashSale> flashSaleList();

	// 根据主键id删除数据
	@Delete("delete from flash_sale where id = #{id}")
	int delById(@Param("id") int id);
       
	// 根据主键id修改数据
	@Update("update flash_sale set title=#{edit.title},price = #{edit.price},shops_id = #{edit.shops_id},product_name = #{edit.product_name}"
			+ "icon =#{edit.icon} ,status=#{edit.status},shops_name = #{edit.shops_name}, "
			+ "stock = #{edit.stock},timeliness_id=#{edit.timeliness_id},updated_time= now()" + "where id = #{edit.id}")
	int updateById(@Param("edit") FlashSale edit);
	
	/**
	 * 获取抢购活动总数
	 * @return
	 */
	@Select("select count(*) from flash_sale")
	int countFlashSale();
}
