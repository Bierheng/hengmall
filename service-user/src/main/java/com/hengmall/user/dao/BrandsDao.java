package com.hengmall.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.hengmall.user.model.Brands;

/**
 * Created by  wuhengbin on 2018/5/24.
 */
@Repository
public interface BrandsDao {

	// 根据id查询
	@Select("select * from brands where id = #{id}")
	Brands queryById(@Param("id") int id);
	
	// 根据id查询
	@Select("select * from brands")
	List<Brands> queryForList();

	// 新增
	@Insert("insert into brands (id, name, icon, `desc`, created_time, flag)"
			+ "values (#{insert.id}, #{insert.name}, #{insert.icon}, #{insert.desc}, now(), #{insert.flag})")
	int insert(@Param("insert") Brands insert);

	// 根据主键id删除数据
	@Delete("delete from brands where id = #{id}")
	int delById(@Param("id") int id);
       
	// 根据主键id修改数据
	@Update("update brands set  name = #{record.name}, "
			+ "icon = #{record.icon}, `desc` = #{record.desc}, flag = #{record.flag} where id=#{record.id}")
	int updateById(@Param("record") Brands record);
	
	/**
	 * 获取抢购活动总数
	 * @return
	 */
	@Select("select count(*) from brands")
	int countBrands();
}
