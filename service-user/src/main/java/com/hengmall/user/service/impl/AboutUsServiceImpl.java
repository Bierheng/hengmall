package com.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.dao.AboutUsDao;
import com.server.entity.aboutUs.AboutUsDelRequest;
import com.server.entity.aboutUs.AboutUsResponse;
import com.server.entity.aboutUs.AboutUsSaveRequest;
import com.server.service.AboutUsService;

/**
 * 关于我们service
 * @author Administrator
 *
 */
@Service
public class AboutUsServiceImpl implements AboutUsService {

	@Autowired
	private AboutUsDao aboutUsDao;
	
	/**
	 * 关于我们列表
	 */
	@Override
	public List<AboutUsResponse> aboutUsList() {
		return aboutUsDao.aboutUsList();
	}

	
	/**
	 * 新增或修改关于我们
	 */
	@Override
	public int aboutUsSave(AboutUsSaveRequest aboutUsSaveRequest) {
		if(aboutUsSaveRequest.isNewRecord()) {
			return aboutUsDao.aboutUsAdd(aboutUsSaveRequest);
		}else {
			return aboutUsDao.aboutUsUpdate(aboutUsSaveRequest);			
		}
	}
	
	
	/**
	 * 删除关于我们
	 * @param aboutUsDelRequest
	 * @return
	 */
	@Override
	public int aboutUsDel(AboutUsDelRequest aboutUsDelRequest) {
		return aboutUsDao.aboutUsDel(aboutUsDelRequest);
	}
}
