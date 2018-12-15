package com.hengmall.user.service;

import java.util.List;

import com.hengmall.user.model.tigLibrary.TigLibraryAddReq;
import com.hengmall.user.model.tigLibrary.TigLibraryDelReq;
import com.hengmall.user.model.tigLibrary.TigLibraryRes;

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
