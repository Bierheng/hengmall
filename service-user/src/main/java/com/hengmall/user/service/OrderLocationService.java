package com.server.service;

import com.alibaba.fastjson.JSONObject;
import com.server.entity.ShopsLocation;

public interface OrderLocationService {

	JSONObject getLocationList();

	int updateSupplierLocation(int userId ,String lat ,String lng);

	int addshops(ShopsLocation shopsLocation);
	
}
