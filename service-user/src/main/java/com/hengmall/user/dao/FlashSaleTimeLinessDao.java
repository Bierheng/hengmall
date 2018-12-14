package com.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.server.entity.FlashSaleTimeLiness;

/**
 * Created by Administrator on 2018/5/24.
 */
@Repository
public interface FlashSaleTimeLinessDao {

	// 根据id查询
	@Select("select * from flash_sale_timeliness where id = #{id}")
	FlashSaleTimeLiness queryById(@Param("id") int id);

	// 新增
	@Insert("insert into flash_sale_timeliness(id,`desc`,start_time,end_time,created_time)"
			+ "values(#{insert.id},#{insert.desc},#{insert.start_time},#{insert.end_time},now())")
	int insert(@Param("insert") FlashSaleTimeLiness insert);
	
	/**
	 * @Title: 查询抢购活动列表
	 * @Description: TODO
	 */
	@Select({ "<script>", "select id,`desc`,start_time,end_time,created_time,updated_time from flash_sale_timeliness",
			"order by created_time desc", "</script>" })
	List<FlashSaleTimeLiness> flashSaleTimeLinessList();

	// 根据主键id删除数据
	@Delete("delete from flash_sale_timeliness where id = #{id}")
	int delById(@Param("id") int id);
       
	// 根据主键id修改数据
	@Update("update flash_sale_timeliness set `desc` = #{edit.desc},"
			+ "start_time =#{edit.start_time} ,end_time=#{edit.end_time},"
			+ "updated_time = now() " + "where id = #{edit.id}")
	int updateById(@Param("edit") FlashSaleTimeLiness edit);
	
	/**
	 * 获取抢购活动时间段记录总数
	 * @return
	 */
	@Select("select count(*) from flash_sale_timeliness")
	int countFlashSaleTimeLiness();
}
