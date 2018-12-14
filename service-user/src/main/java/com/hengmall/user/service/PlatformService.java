package com.server.service;


import java.util.List;

import com.server.entity.persistence.Page;
import com.server.entity.platform.OrderDelRequest;
import com.server.entity.platform.OrderRequest;
import com.server.entity.platform.OrderResponse;
import com.server.entity.platform.OrderSaveRequest;
import com.server.entity.platform.PlatformDelRequest;
import com.server.entity.platform.PlatformLvResponse;
import com.server.entity.platform.PlatformRequest;
import com.server.entity.platform.PlatformResponse;
import com.server.entity.platform.PlatformSaveRequest;
import com.server.entity.platform.RightDelRequest;
import com.server.entity.platform.RightRequest;
import com.server.entity.platform.RightResponse;
import com.server.entity.platform.RightSaveRequest;
import com.server.entity.platform.StateDelRequest;
import com.server.entity.platform.StateRequest;
import com.server.entity.platform.StateResponse;
import com.server.entity.platform.StateSaveRequest;
import com.server.entity.platform.TagDelRequest;
import com.server.entity.platform.TagListRequest;
import com.server.entity.platform.TagListResponse;
import com.server.entity.platform.TagSaveRequest;

/**
 * 平台管理
 * @author Administrator
 *
 */
public interface PlatformService {

	/**
	 * 平台模板列表
	 * @param aboutUsRequest
	 * @return
	 */
	Page<PlatformResponse> platformList(Page<PlatformResponse> page,PlatformRequest platformRequest);
	
	
	/**
	 * 获取一级分类列表
	 * @param platformLvRequest
	 * @return
	 */
	List<PlatformLvResponse> lvOneList();
	
	
	/**
	 * 平台模板修改、添加
	 * @return
	 */
	int platformSave(PlatformSaveRequest platformSaveRequest);
	
	/**
	 * 平台模板删除
	 * @return
	 */
	int platformDel(PlatformDelRequest platformDelRequest);
	
	/**
	 * 产品货源地国籍列表
	 * @param stateRequest
	 * @return
	 */
	Page<StateResponse> stateList(Page<StateResponse> page,StateRequest stateRequest);
	
	
	/**
	 * 产品货源地国籍添加、修改
	 * @param stateSave
	 * @return
	 */
	int stateSave(StateSaveRequest stateSave);
	
	/**
	 * 产品货源地国籍删除
	 * @return
	 */
	int stateDel(StateDelRequest stateDelRequest);
	
	/**
	 * 店铺资质列表
	 * @return
	 */
	Page<RightResponse> rightList(Page<RightResponse> page,RightRequest rightRequest);
	
	
	/**
	 * 店铺资质添加、修改
	 * @param rightSaveRequest
	 * @return
	 */
	int rightSave(RightSaveRequest rightSaveRequest);
	
	
	/**
	 * 店铺资质删除
	 * @return
	 */
	int rightDel(RightDelRequest rightDelRequest);
	
	
	/**
	 * 平台标签列表
	 * @param page
	 * @param tagListRequest
	 * @return
	 */
	Page<TagListResponse> tagList(Page<TagListResponse> page,TagListRequest tagListRequest);
	
	
	/**
	 * 平台标签添加、修改
	 * @param tagSaveRequest
	 * @return
	 */
	int tagSave(TagSaveRequest tagSaveRequest);
	
	
	/**
	 * 平台标签删除
	 * @return
	 */
	int tagDel(TagDelRequest tagDelRequest);
	
	
	/**
	 * 定位地图购物信息弹框列表
	 * @param page
	 * @param orderRequest
	 * @return
	 */
	Page<OrderResponse> orderList(Page<OrderResponse> page,OrderRequest orderRequest);
	
	
	/**
	 * 定位地图购物信息弹框添加、修改
	 * @param orderSaveRequest
	 * @return
	 */
	int orderSave(OrderSaveRequest orderSaveRequest);
	
	
	/**
	 * 定位地图购物信息弹框删除
	 * @param orderDelRequest
	 * @return
	 */
	int orderDel(OrderDelRequest orderDelRequest);
}
