package com.hengmall.user.dao;


import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.hengmall.user.model.sensitive.SensitiveDelRequest;
import com.hengmall.user.model.sensitive.SensitiveWordNewRequest;
import com.hengmall.user.model.sensitive.SensitiveWordRequest;
import com.hengmall.user.model.sensitive.SensitiveWordResponse;

/**
 * 敏感词库 Dao
 */
@Repository
public interface SensitiveWordDao {

	/**
	 * 获取所有敏感词
	 * @return Set集合
	 */
	@Select("SELECT txt FROM sensitive_word")
	Set<String> setSensitiveWord();
	
	
	/**
	 * 获取所有敏感词
	 * @param sensitiveWordRequest
	 * @return List 集合
	 */
	@Select("<script>"
			+ "SELECT "
				+ "id AS 'id',"
				+ "txt AS 'txt',"
				+ "insert_date AS 'insertDate'"
			+ " FROM "
				+ "sensitive_word"
			+ "<if test='id != null and id != \"\"'>"
				+" WHERE "
				+ "id = #{id}"
			+ "</if>"
		+ "</script>")
	List<SensitiveWordResponse> getList(SensitiveWordRequest sensitiveWordRequest);
	
	
	/**
	 * 新增敏感词
	 * @return
	 */
	@Insert("INSERT INTO sensitive_word (txt,insert_date) VALUES (#{txt},#{insertDate})")
	int newSensitiveWord(SensitiveWordNewRequest sensitiveWordNewRequest);
	
	
	
	/**
	 * 删除敏感词
	 * @return
	 */
	@Delete("DELETE FROM sensitive_word WHERE id = #{id}")
	int delSensitiveWord(SensitiveDelRequest sensitiveDelRequest);
}
