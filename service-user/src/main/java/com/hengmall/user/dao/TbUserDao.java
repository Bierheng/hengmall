package com.server.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.server.entity.TbPermission;
import com.server.entity.TbUser;

/**
 * Created by  wuhengbin on 2018/5/24.
 */
@Repository
public interface TbUserDao {

	// 根据id查询
	@Select("select * from ${platform}.tb_user where id = #{id}")
	TbUser queryById(@Param("id") int id);
	
	/**
	 * 根据用户名查询用户信息
	 * @param username
	 * @return
	 */
	@Select("SELECT "
			+ "a.*,"
			+ "a.role_id AS 'roleId',"
			+ "a.`user_id` AS 'userId'"
		+ " FROM ${platform}.tb_user a"
		+ " WHERE username = #{username}")
	TbUser queryByusername(@Param("username") String username);

	
		/**
		 * 根据角色获取权限
		 * @param 
		 * @return
		 */
		@Select("SELECT "
					+ "p.id,"
					+ "p.`name` AS 'name',"
					+ "p.permission AS 'permission'"
			+ " FROM ${platform}.tb_role_perm r"
			+ " LEFT JOIN ${platform}.tb_permission p ON r.permission_id = p.id "
			+ " WHERE "
					+ "r.role_id = #{roleId}")
		List<TbPermission> tbPermissionList(@Param("roleId") Integer roleId);

	
	// 根据用户名查询用户信息
	@Select("select * from ${platform}.tb_user where phone = #{phone}")
	List<TbUser> queryByphone(@Param("phone") String phone);
	
	// 根据用户名查询用户信息
	@Select("select * from ${platform}.tb_user where email = #{email}")
	List<TbUser> queryByemaill(@Param("email") String email);
	
	/**
	 * 根据用户名获取权限
	 * @param userName
	 * @return
	 */
	@Select("SELECT p.permission FROM ${platform}.tb_user u JOIN ${platform}.tb_role r ON u.role_id = r.id"
			+ "  JOIN  ${platform}.tb_role_perm rp ON rp.role_id = u.role_id JOIN ${platform}.tb_permission p ON rp.permission_id = p.id"
			+ "  WHERE u.username = #{username}")
	Set<String> getPermissions(@Param("username") String username);
	
	/**
	 * 根据用户名获取角色
	 * @param userName
	 * @return
	 */
	@Select("SELECT r. NAME FROM ${platform}.tb_user u JOIN ${platform}.tb_role r ON u.role_id = r.id WHERE u.username = #{username}")
	Set<String> getRoles(@Param("username") String username);
	
	/**
	 * 根据角色ID或者权限列表
	 * @param id
	 * @return
	 */
	@Select("SELECT p.id FROM ${platform}.tb_role r JOIN ${platform}.tb_role_perm rp ON rp.role_id = r.id "
			+ "JOIN ${platform}.tb_permission p ON rp.permission_id = p.id WHERE r.id = #{id}")
	List<Integer> getPermsIdByRoleId(@Param("id") int id);

	// 根据id查询
	@Select("select * from ${platform}.tb_user")
	List<TbUser> selectForList();
	
	// 新增
	@Insert(" insert into ${platform}.tb_user (username, password,  phone, email, sex,  address, state, description, role_id, file, created, updated)"
			+ "values (#{insert.username}, #{insert.password}, #{insert.phone}, #{insert.email},"
			+ " #{insert.sex},  #{insert.address}, #{insert.state}, #{insert.description},  #{insert.roleId}, #{insert.file}, #{insert.created}, #{insert.updated})")
	int insert(@Param("insert") TbUser insert);
	
	//修改
	@Update("update ${platform}.tb_user set username = #{record.username}, password = #{record.password},phone = #{record.phone}, "
			+ "email = #{record.email}, sex = #{record.sex}, address = #{record.address},"
			+ "state = #{record.state}, description = #{record.description}, role_id = #{record.roleId},"
			+ "file = #{record.file},created = #{record.created},updated = #{record.updated} where id=#{record.id}")
	int updateById(@Param("record") TbUser record);

	// 根据主键id删除数据
	@Delete("delete from ${platform}.tb_user where id = #{id}")
	int delById(@Param("id") int id);
	
	/**
	 * 获取用户总数
	 * @return
	 */
	@Select("select count(*) from ${platform}.tb_user")
	int countTbUser();
}
