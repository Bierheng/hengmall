package com.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.server.dao.SysConfigDao;
import com.server.entity.persistence.Page;
import com.server.entity.sys.ConfigDelRequest;
import com.server.entity.sys.ConfigListRequest;
import com.server.entity.sys.ConfigListResponse;
import com.server.entity.sys.ConfigSaveRequest;
import com.server.service.SysConfigService;

/**
 * 系统配置
 * @author Administrator
 *
 */
@Service
public class SysConfigServiceImpl implements SysConfigService {

	@Autowired
	private SysConfigDao sysConfigDao;
	
	/**
	 * 系统配置列表
	 */
	@Override
	public Page<ConfigListResponse> configList(Page<ConfigListResponse> page, ConfigListRequest configListRequest) {
		PageHelper.startPage(configListRequest.getPage(),configListRequest.getListPage());
		List<ConfigListResponse> list = sysConfigDao.configList(configListRequest);
		page.setData(list);
		page.setCount(new PageInfo<>(list).getTotal());
		return page;
	}

	
	/**
	 * 系统配置添加,修改
	 * @throws Exception 
	 */
	@Override
	public void configSave(ConfigSaveRequest configSaveRequest) throws Exception {
		if(configSaveRequest.isNewRecord()) {
			sysConfigDao.configAdd(configSaveRequest);
		}else {
			if(sysConfigDao.configUpdate(configSaveRequest) == 0) {
				throw new Exception("系统配置id不存在");
			}
		}
	}


	/**
	 * 系统配置删除
	 * @throws Exception 
	 */
	@Override
	public void configDel(ConfigDelRequest configDelRequest) throws Exception {
		if(sysConfigDao.configDel(configDelRequest) == 0) {
			throw new Exception("系统配置id不存在");
		}
	}

}
