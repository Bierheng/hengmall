package com.hengmall.user.service;

import java.util.Set;

import com.hengmall.user.model.persistence.Page;
import com.hengmall.user.model.sensitive.SensitiveDelRequest;
import com.hengmall.user.model.sensitive.SensitiveWordNewRequest;
import com.hengmall.user.model.sensitive.SensitiveWordRequest;
import com.hengmall.user.model.sensitive.SensitiveWordResponse;

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
