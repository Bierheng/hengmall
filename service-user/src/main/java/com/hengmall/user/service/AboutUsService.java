package com.server.service;


import java.util.List;

import com.server.entity.aboutUs.AboutUsDelRequest;
import com.server.entity.aboutUs.AboutUsResponse;
import com.server.entity.aboutUs.AboutUsSaveRequest;

/**
 * 关于我们
 * @author Administrator
 *
 */
public interface AboutUsService {

	/**
	 * 关于我们列表
	 * @param aboutUsRequest
	 * @return
	 */
	List<AboutUsResponse> aboutUsList();
	
	
	/**
	 * 新增或修改关于我们
	 * @param aboutUsRequest
	 * @return
	 */
	int aboutUsSave(AboutUsSaveRequest aboutUsSaveRequest);
	
	
	/**
	 * 删除关于我们
	 * @param aboutUsDelRequest
	 * @return
	 */
	public int aboutUsDel(AboutUsDelRequest aboutUsDelRequest);
}
