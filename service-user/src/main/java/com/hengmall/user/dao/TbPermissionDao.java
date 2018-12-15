package com.hengmall.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.hengmall.user.model.TbPermission;

/**
 * Created by  wuhengbin on 2018/5/24.
 */
@Repository
public interface TbPermissionDao {
	
	/**
	 * 根据角色ID或者权限列表
	 * @param id
	 * @return
	 */
	@Select("SELECT p.id FROM ${platform}.tb_role r JOIN ${platform}.tb_role_perm rp ON rp.role_id = r.id "
			+ "JOIN ${platform}.tb_permission p ON rp.permission_id = p.id WHERE r.id = #{id}")
	List<Integer> getPermsIdByRoleId(@Param("id") int id);

	// 根据id查询
	@Select("select * from ${platform}.tb_permission")
	List<TbPermission> selectForList();
	
	// 新增
	@Insert("insert into ${platform}.tb_permission (id, name, permission) values (#{insert.id}, #{insert.name}, #{insert.permission})")
	int insert(@Param("insert") TbPermission insert);
	
	//修改
	@Update("update ${platform}.tb_permission set  name = #{record.name}, permission = #{record.permission} where id=#{record.id}")
	int updateById(@Param("record") TbPermission record);

	// 根据主键id删除数据
	@Delete("delete from ${platform}.tb_permission where id = #{id}")
	int delById(@Param("id") int id);
	
	/**
	 * 获取用户总数
	 * @return
	 */
	@Select("select count(*) from ${platform}.tb_permission")
	int countTbPermission();

}
