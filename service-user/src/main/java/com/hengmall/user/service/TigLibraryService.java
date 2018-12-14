package com.server.service;

import java.util.List;

import com.server.entity.tigLibrary.TigLibraryRes;
import com.server.entity.tigLibrary.TigLibraryAddReq;
import com.server.entity.tigLibrary.TigLibraryDelReq;

/**
 * 标签库管理 Service
 * @author Administrator
 *
 */
public interface TigLibraryService {

	/**
	 * 标签库列表
	 * @param tigLibraryReq
	 * @return
	 */
	List<TigLibraryRes> tigLibraryList();
	
	/**
	 * 标签库添加
	 * @param tigLibrarySaveReq
	 * @return
	 */
	int tigLibraryAdd(TigLibraryAddReq tigLibraryAddReq);
	
	
	/**
	 * 标签库删除
	 * @param tigLibraryDelReq
	 * @return
	 * @throws Exception 
	 */
	void tigLibraryDel(TigLibraryDelReq tigLibraryDelReq) throws Exception;
}
