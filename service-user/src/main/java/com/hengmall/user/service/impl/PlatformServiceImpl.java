package com.hengmall.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hengmall.user.dao.PlatformDao;
import com.hengmall.user.dao.ShopsDao;
import com.hengmall.user.model.persistence.Page;
import com.hengmall.user.model.platform.OrderDelRequest;
import com.hengmall.user.model.platform.OrderRequest;
import com.hengmall.user.model.platform.OrderResponse;
import com.hengmall.user.model.platform.OrderSaveRequest;
import com.hengmall.user.model.platform.PlatformDelRequest;
import com.hengmall.user.model.platform.PlatformLvResponse;
import com.hengmall.user.model.platform.PlatformRequest;
import com.hengmall.user.model.platform.PlatformResponse;
import com.hengmall.user.model.platform.PlatformSaveRequest;
import com.hengmall.user.model.platform.RightDelRequest;
import com.hengmall.user.model.platform.RightRequest;
import com.hengmall.user.model.platform.RightResponse;
import com.hengmall.user.model.platform.RightSaveRequest;
import com.hengmall.user.model.platform.StateDelRequest;
import com.hengmall.user.model.platform.StateRequest;
import com.hengmall.user.model.platform.StateResponse;
import com.hengmall.user.model.platform.StateSaveRequest;
import com.hengmall.user.model.platform.TagDelRequest;
import com.hengmall.user.model.platform.TagListRequest;
import com.hengmall.user.model.platform.TagListResponse;
import com.hengmall.user.model.platform.TagSaveRequest;
import com.hengmall.user.service.PlatformService;
import com.hengmall.user.service.common.BaseService;

/**
 * 平台管理service
 * @author Administrator
 *
 */
@Service
public class PlatformServiceImpl extends BaseService implements PlatformService {

	@Autowired
	private PlatformDao platformDao;
	@Autowired
	private ShopsDao shopsDao;
	
	/**
	 * 平台模板列表
	 */
	@Override
	public Page<PlatformResponse> platformList(Page<PlatformResponse> page,PlatformRequest platformRequest) {
		PageHelper.startPage(platformRequest.getPage(),platformRequest.getListPage());
		List<PlatformResponse> list = platformDao.platformList(platformRequest);
		for (int i = 0; i < list.size(); i++) {
			int shopsId = list.get(i).getShops_id();
			String shops_name = shopsDao.getShopsName(String.valueOf(shopsId));
			list.get(i).setPlatformProduct(platformDao.plName(list.get(i).getPlatformIds()));
			list.get(i).setShops_name(shops_name);
		}
		
		page.setData(list);
		page.setCount(new PageInfo<>(list).getTotal());
		return page;
	}

	
	/**
	 * 获取一级分类列表
	 */
	@Override
	public List<PlatformLvResponse> lvOneList() {
		return platformDao.lvOneList();
	}


	/**
	 * 平台模板修改、添加
	 */
	@Override
	public int platformSave(PlatformSaveRequest platformSaveRequest) {
		if(platformSaveRequest.isNewRecord()) {
			return platformDao.platformAdd(platformSaveRequest);
		}else {
			return platformDao.platformUpdate(platformSaveRequest);
		}
	}
	
	/**
	 * 平台模板删除
	 */
	public int platformDel(PlatformDelRequest platformDelRequest) {
		return platformDao.platformDel(platformDelRequest);
	}


	/**
	 * 产品货源地国籍列表
	 */
	@Override
	public Page<StateResponse> stateList(Page<StateResponse> page,StateRequest stateRequest) {
		//是否分页
		if(stateRequest.getIsPage()) {
			PageHelper.startPage(stateRequest.getPage(),stateRequest.getListPage());
			List<StateResponse> list = platformDao.stateList(stateRequest);
			page.setData(list);
			page.setCount(new PageInfo<>(list).getTotal());
			return page;
		}else {
			List<StateResponse> list = platformDao.stateList(stateRequest);
			page.setData(list);
			return page;
		}
	}


	/**
	 * 产品货源地国籍添加、修改
	 */
	@Override
	public int stateSave(StateSaveRequest stateSave) {
		if(stateSave.isNewRecord()) {
			return platformDao.stateAdd(stateSave);
		}else {
			return platformDao.stateUpdate(stateSave);
		}
	}


	/**
	 * 店铺资质列表
	 */
	@Override
	public Page<RightResponse> rightList(Page<RightResponse> page,RightRequest rightRequest) {
		PageHelper.startPage(rightRequest.getPage(),rightRequest.getListPage());
		List<RightResponse> list = platformDao.rightList(rightRequest);
		page.setData(list);
		page.setCount(new PageInfo<>(list).getTotal());
		return page;
	}


	/**
	 * 店铺资质添加、修改
	 */
	@Override
	public int rightSave(RightSaveRequest rightSaveRequest) {
		if(rightSaveRequest.isNewRecord()) {
			return platformDao.rightAdd(rightSaveRequest);
		}else {
			return platformDao.rightUpdate(rightSaveRequest);
		}
	}


	/**
	 * 店铺资质删除
	 */
	@Override
	public int rightDel(RightDelRequest rightDelRequest) {
		return platformDao.rightDel(rightDelRequest);
	}


	/**
	 * 产品货源地国籍删除
	 */
	@Override
	public int stateDel(StateDelRequest stateDelRequest) {
		return platformDao.stateDel(stateDelRequest);
	}


	/**
	 * 平台标签列表
	 */
	@Override
	public Page<TagListResponse> tagList(Page<TagListResponse> page, TagListRequest tagListRequest) {
		PageHelper.startPage(tagListRequest.getPage(),tagListRequest.getListPage());
		List<TagListResponse> list = platformDao.tagList(tagListRequest);
		page.setData(list);
		page.setCount(new PageInfo<>(list).getTotal());
		return page;
	}


	/**
	 * 平台标签添加、修改
	 */
	@Override
	public int tagSave(TagSaveRequest tagSaveRequest) {
		if(tagSaveRequest.isNewRecord()) {
			return platformDao.tagAdd(tagSaveRequest);
		}else {
			return platformDao.tagUpdate(tagSaveRequest);
		}
	}


	/**
	 * 平台标签删除
	 */
	@Override
	public int tagDel(TagDelRequest tagDelRequest) {
		return platformDao.tagDel(tagDelRequest);
	}


	/**
	 * 定位地图购物信息弹框列表
	 */
	@Override
	public Page<OrderResponse> orderList(Page<OrderResponse> page, OrderRequest orderRequest) {
		PageHelper.startPage(orderRequest.getPage(),orderRequest.getListPage());
		List<OrderResponse> list = platformDao.orderList(orderRequest);
		page.setData(list);
		page.setCount(new PageInfo<>(list).getTotal());
		return page;
	}


	/**
	 * 定位地图购物信息弹框添加、修改
	 */
	@Override
	public int orderSave(OrderSaveRequest orderSaveRequest) {
		if(orderSaveRequest.isNewRecord()) {
			return platformDao.orderAdd(orderSaveRequest);
		}else {
			return platformDao.orderUpdate(orderSaveRequest);
		}
	}


	/**
	 * 定位地图购物信息弹框删除
	 */
	@Override
	public int orderDel(OrderDelRequest orderDelRequest) {
		return platformDao.orderDel(orderDelRequest);
	}

}
