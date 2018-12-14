package com.hengmall.goods.service;

import com.alibaba.fastjson.JSONObject;
import com.hengmall.goods.model.ShopsLocation;

public interface OrderLocationService {

	JSONObject getLocationList();

	int updateSupplierLocation(int userId ,String lat ,String lng);

	int addshops(ShopsLocation shopsLocation);
	
}
