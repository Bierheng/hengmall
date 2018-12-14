package com.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.server.entity.TbRole;
import com.server.entity.TbRolePerm;

/**
 * Created by  wuhengbin on 2018/5/24.
 */
@Repository
public interface TbRolePermDao {

	// 根据id查询
	@Select("select * from ${platform}.tb_role_perm where role_id = #{id}")
	List<TbRolePerm> queryByRoleId(@Param("id") int id);

	// 根据id查询
	@Select("select * from ${platform}.tb_role_perm where id = #{id}")
	List<TbRolePerm> queryById(@Param("id") int id);
	
	// 新增
	@Insert("<script>"
			+ "insert into ${platform}.tb_role_perm ( role_id, permission_id)"
			+ " values "
			+ "<foreach collection='role.roles' index='index' item='i' separator=','>"
				+ "(#{role.id},#{i})"
			+ "</foreach>"
			+ "</script>")
	int insert(@Param("role") TbRole role);

	// 根据主键id删除数据
	@Delete("<script>"
			+ "delete from ${platform}.tb_role_perm where id in"
				+ "<foreach collection='tbRolePerm' index='index' item='tr' open='(' close=')' separator=','>"
					+ "#{tr.id}"
				+ "</foreach>"
			+ "</script>")
	int delById(@Param("tbRolePerm") List<TbRolePerm> tbRolePerm);
	
	
	@Delete("<script>"
			+ "delete from ${platform}.tb_role_perm where id = #{id}"
			+ "</script>")
	int delById2(@Param("id") int id);
	
	/**
	 * 获取用户总数
	 * @return
	 */
	@Select("select count(*) from ${platform}.tb_user")
	int countTbUser();
	
	

}
