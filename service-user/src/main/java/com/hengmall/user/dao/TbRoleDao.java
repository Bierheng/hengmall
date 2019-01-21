package com.hengmall.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hengmall.user.model.TbRole;

/**
 * Created by  wuhengbin on 2018/5/24.
 */
@Mapper
public interface TbRoleDao {

	// 根据id查询
	@Select("select * from ${platform}.tb_role where id = #{id}")
	TbRole queryById(@Param("id") int id);
	
	// 根据id查询
	@Select("select * from ${platform}.tb_role where name = #{roleName}")
	List<TbRole> queryByRoleName(@Param("roleName") String roleName);

	// 根据id查询
	@Select("select * from ${platform}.tb_role")
	List<TbRole> selectForList();
	
	// 新增
	@Insert("insert into ${platform}.tb_role (id, name, description)"
			+ "values (#{insert.id}, #{insert.name}, #{insert.description})")
	int insert(@Param("insert") TbRole insert);

	// 根据主键id删除数据
	@Delete("delete from ${platform}.tb_role where id = #{id}")
	int delById(@Param("id") int id);
       
	// 根据主键id修改数据
	@Update(" update ${platform}.tb_role set name = #{record.name}, description = #{record.description} where id=#{record.id}")
	int updateById(@Param("record") TbRole record);
	
	@Select(" SELECT r.`name` FROM ${platform}.tb_user u JOIN ${platform}.tb_role r ON u.role_id = r.id WHERE r.id = #{id}")
	List<String> getUsedRoles(@Param("id") int id);
	
	/**
	 * 获取角色总数
	 * @return
	 */
	@Select("select count(*) from ${platform}.tb_role")
	int countTbRole();
	
	

}
