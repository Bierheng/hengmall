package com.hengmall.user.service;

import com.hengmall.user.model.persistence.Page;
import com.hengmall.user.model.sys.ConfigDelRequest;
import com.hengmall.user.model.sys.ConfigListRequest;
import com.hengmall.user.model.sys.ConfigListResponse;
import com.hengmall.user.model.sys.ConfigSaveRequest;

/**
 * 系统配置
 * @author Administrator
 *
 */
public interface SysConfigService {

	/**
	 * 系统配置列表
	 * @param page
	 * @param configListRequest
	 * @return
	 */
	Page<ConfigListResponse> configList(Page<ConfigListResponse> page,ConfigListRequest configListRequest);
	
	
	/**
	 * 系统配置添加,修改
	 * @param configSaveRequest
	 * @throws Exception 
	 */
	void configSave(ConfigSaveRequest configSaveRequest) throws Exception;
	
	/**
	 * 系统配置删除
	 * @param configDelRequest
	 * @throws Exception 
	 */
	void configDel(ConfigDelRequest configDelRequest) throws Exception;
}
