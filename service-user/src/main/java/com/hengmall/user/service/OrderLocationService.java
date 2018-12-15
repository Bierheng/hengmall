package com.hengmall.user.service;

import com.alibaba.fastjson.JSONObject;
import com.hengmall.user.model.ShopsLocation;

public interface OrderLocationService {

	JSONObject getLocationList();

	int updateSupplierLocation(int userId ,String lat ,String lng);

	int addshops(ShopsLocation shopsLocation);
	
}
