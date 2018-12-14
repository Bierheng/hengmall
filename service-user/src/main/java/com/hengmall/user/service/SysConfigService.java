package com.server.service;

import com.server.entity.persistence.Page;
import com.server.entity.sys.ConfigDelRequest;
import com.server.entity.sys.ConfigListRequest;
import com.server.entity.sys.ConfigListResponse;
import com.server.entity.sys.ConfigSaveRequest;

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
