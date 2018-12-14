package com.server.service;

import java.util.Set;

import com.server.entity.persistence.Page;
import com.server.entity.sensitive.SensitiveDelRequest;
import com.server.entity.sensitive.SensitiveWordNewRequest;
import com.server.entity.sensitive.SensitiveWordRequest;
import com.server.entity.sensitive.SensitiveWordResponse;

/**
 * 敏感词库service
 * @author Administrator
 *
 */
public interface SensitiveWordService {
	
	/**
	 * 获取所有敏感词库
	 * @return
	 */
	Set<String> setSensitiveWord();
	
	/**
	 * 获取所有敏感词库 list列表
	 * @return
	 */
	Page<SensitiveWordResponse> getList(SensitiveWordRequest sensitiveWordRequest);
	
	/**
	 * 新增敏感词
	 */
	int newSensitiveWord(SensitiveWordNewRequest sensitiveWordNewRequest);
	
	/**
	 * 删除敏感词
	 * @param sensitiveDelRequest
	 * @return
	 */
	int delSensitiveWord(SensitiveDelRequest sensitiveDelRequest);
}
