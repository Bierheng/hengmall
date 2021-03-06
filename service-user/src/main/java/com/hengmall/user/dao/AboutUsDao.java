package com.hengmall.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hengmall.user.model.aboutUs.AboutUsDelRequest;
import com.hengmall.user.model.aboutUs.AboutUsResponse;
import com.hengmall.user.model.aboutUs.AboutUsSaveRequest;

/**
 * 关于我们
 * @author Administrator
 *
 */
@Mapper
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
