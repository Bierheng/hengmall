package com.hengmall.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.hengmall.user.model.Ads;
import com.hengmall.user.model.TbLog;

/**
 * Created by  wuhengbin on 2018/5/24.
 */
@Repository
public interface TbLogDao {
	
	// 根据id查询
	@Select("select * from ads")
	List<Ads> queryForList();

	// 新增
	@Insert("insert into tb_log ( name, type, url, request_type, request_param, user, ip, ip_info, time,  create_date)"
			+ " values ( #{insert.name}, #{insert.type}, #{insert.url},"
			+ " #{insert.request_type}, #{insert.request_param},  #{insert.user}, #{insert.ip}, #{insert.ip_info}, #{insert.time},  #{insert.create_date})")
	int insert(@Param("insert") TbLog insert);

	// 根据主键id删除数据
	@Delete("delete from tb_log where id = #{id}")
	int delById(@Param("id") int id);
	
	/**
	 * 
	 * @return
	 */
	@Select("select count(*) from tb_log")
	int countTbLog();
}
