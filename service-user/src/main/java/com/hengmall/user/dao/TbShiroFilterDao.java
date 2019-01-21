package com.hengmall.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hengmall.user.model.TbShiroFilter;

/**
 * Created by  wuhengbin on 2018/5/24.
 */
@Mapper
public interface TbShiroFilterDao {

	// 根据id查询
	@Select("select * from tb_shiro_filter order by sort_order")
	List<TbShiroFilter>  queryByOrder();

	// 新增
	@Insert("insert into ${platform}.tb_shiro_filter ( name, perms, sort_order)"
			+ "values ( #{insert.name}, #{insert.perms},  #{insert.sort_order})")
	int insert(@Param("insert") TbShiroFilter insert);

	// 根据主键id删除数据
	@Delete("delete from tb_shiro_filter where id = #{id}")
	int delById(@Param("id") int id);
       
	// 根据主键id修改数据
	@Update("update tb_shiro_filter set name = #{record.name}, perms = #{record.perms}, "
			+ "sort_order = #{record.sort_order} where id=#{record.id}")
	int updateById(@Param("record") TbShiroFilter record);
	
	/**
	 * 获取总数
	 * @return
	 */
	@Select("select count(*) from tb_shiro_filter")
	int countTbShiroFilter();
}
