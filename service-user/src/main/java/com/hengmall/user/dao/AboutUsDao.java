package com.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.server.entity.aboutUs.AboutUsDelRequest;
import com.server.entity.aboutUs.AboutUsResponse;
import com.server.entity.aboutUs.AboutUsSaveRequest;

/**
 * 关于我们
 * @author Administrator
 *
 */
@Repository
public interface AboutUsDao {

	/**
	 * 关于我们列表
	 * @param aboutUsRequest
	 * @return
	 */
	@Select("SELECT "
				+ "a.`id` AS 'id',"
				+ "a.`info` AS 'info',"
				+ "a.`created_time` AS 'createdTime'"
			+ " FROM "
				+ "platform_info a"
			+ " ORDER BY a.created_time DESC")
	List<AboutUsResponse> aboutUsList();
	
	
	/**
	 * 新增关于我们
	 * @param aboutUsSaveRequest
	 * @return
	 */
	@Insert("INSERT INTO platform_info (info,created_time) VALUES (#{info},#{updateDate})")
	int aboutUsAdd(AboutUsSaveRequest aboutUsSaveRequest);
	
	
	/**
	 * 修改关于我们
	 * @param aboutUsSaveRequest
	 * @return
	 */
	@Update("UPDATE platform_info SET info = #{info} WHERE id = #{id}")
	int aboutUsUpdate(AboutUsSaveRequest aboutUsSaveRequest);
	
	
	/**
	 * 删除关于我们
	 * @param aboutUsDelRequest
	 * @return
	 */
	@Delete("DELETE FROM platform_info WHERE id = #{id}")
	int aboutUsDel(AboutUsDelRequest aboutUsDelRequest);
}
