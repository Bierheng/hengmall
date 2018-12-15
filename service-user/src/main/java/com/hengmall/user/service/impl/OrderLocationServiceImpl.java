package com.hengmall.user.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.hengmall.user.dao.OrderLocationDao;
import com.hengmall.user.model.ShopsLocation;
import com.hengmall.user.model.TbSupplier;
import com.hengmall.user.service.OrderLocationService;

@Service	
public class OrderLocationServiceImpl implements OrderLocationService {

	 final static Logger log= LoggerFactory.getLogger(OrderLocationServiceImpl.class);
	
	 @Autowired
	 private OrderLocationDao orderLocationDao;
	 
	@Override
	public JSONObject getLocationList() {
		JSONObject obj = new JSONObject();
		List<ShopsLocation> shopsList = orderLocationDao.orderLocationList();
		List<TbSupplier> supplierList = orderLocationDao.tbSupplierList();
		obj.put("shopsList", shopsList);
		obj.put("supplierList", supplierList);
		return obj;
	}

	@Override
	@Transactional
	public int updateSupplierLocation(int userId ,String lat ,String lng){
		int i=orderLocationDao.updateSupplier( lat , lng, userId);
		return i;
	}

	@Override
	@Transactional
	public int addshops(ShopsLocation shopsLocation) {
		int i = orderLocationDao.insertShops(shopsLocation);
		return i;
	}
}
