package com.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.server.entity.sys.ConfigDelRequest;
import com.server.entity.sys.ConfigListRequest;
import com.server.entity.sys.ConfigListResponse;
import com.server.entity.sys.ConfigSaveRequest;

/**
 * 系统配置
 * @author Administrator
 *
 */
@Repository
public interface SysConfigDao {

	/**
	 * 系统配置列表
	 * @param configListRequest
	 * @return
	 */
	@Select("<script>"
			+ "SELECT "
			+ "a.id AS 'id',"
			+ "a.code AS 'code',"
			+ "a.name AS 'name',"
			+ "a.value AS 'value',"
			+ "a.status AS 'status',"
			+ "a.create_time AS 'createTime',"
			+ "a.update_time AS 'updateTime'"
			+ " FROM "
			+ "sys_config a"
			+ " order by a.create_time desc"
		  + "</script>")
	List<ConfigListResponse> configList(ConfigListRequest configListRequest);
	
	
	/**
	 * 系统配置添加
	 * @param configSaveRequest
	 * @return
	 */
	@Insert("INSERT INTO sys_config ("
			+ "code,"
			+ "name,"
			+ "value,"
			+ "status,"
			+ "create_time"
			+ ") VALUES ("
			+ "#{code},"
			+ "#{name},"
			+ "#{value},"
			+ "#{status},"
			+ "#{updateDate}"
			+ ")")
	int configAdd(ConfigSaveRequest configSaveRequest);
	
	
	/**
	 * 系统配置修改
	 * @param configSaveRequest
	 * @return
	 */
	@Update("UPDATE sys_config SET "
			+ "code = #{code},"
			+ "name = #{name},"
			+ "value = #{value},"
			+ "status = #{status},"
			+ "update_time = #{updateDate}"
			+ " WHERE "
			+ "id = #{id}")
	int configUpdate(ConfigSaveRequest configSaveRequest);
	
	
	/**
	 * 系统配置删除
	 * @param configDelRequest
	 * @return
	 */
	@Delete("DELETE FROM sys_config WHERE id = #{id}")
	int configDel(ConfigDelRequest configDelRequest);
}
