package com.hengmall.goods.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.hengmall.goods.dao.OrderLocationDao;
import com.hengmall.goods.model.ShopsLocation;
import com.hengmall.goods.model.SupplierLocation;
import com.hengmall.goods.model.UsersEntity;
import com.hengmall.goods.model.api.Ajax;
import com.hengmall.goods.model.api.ApiRequest;
import com.hengmall.goods.model.constitute.ShopsLocationOrder;
import com.hengmall.goods.service.OrderLocationService;
import com.hengmall.goods.service.TopicService;
import com.hengmall.goods.util.CommonUtils;
import com.hengmall.goods.util.HttpReqUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wuhengbin
 */
@RestController
@Api(description = "订单定位管理")
public class OrderLocationController{

    final static Logger log= LoggerFactory.getLogger(OrderLocationController.class);

    @Autowired
    private OrderLocationService orderLocationService;
    @Autowired
    private TopicService topicService;
	 @Autowired
	 private OrderLocationDao orderLocationDao;

    /** 获取所有定位信息列表，包括供应商定位信息以及店铺定位信息
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/orderLocation/list",method = RequestMethod.POST)
    @ApiOperation(value = "获取所有定位信息列表" ,response = ShopsLocation.class)
    public Ajax<?> getLocationList()throws Exception {
    	log.info("客户端请求数据【/orderLocation/list】,{}");
    	Ajax<JSONObject> result = new Ajax<>();
    	try {
    		    JSONObject obj = orderLocationService.getLocationList();
    		    result.setCode(0);
    		    result.setData(obj);
    			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}
    }
    
    /** 一键生成店铺新增接口
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/add",method = RequestMethod.POST)
    @ApiOperation(value = "一键生成店铺新增接口" ,response = SupplierLocation.class)
    public Ajax<?> addshops(@RequestBody SupplierLocation supplierLocation)throws Exception {
    	log.info("客户端请求数据【/shops/add】,{}");
    	Ajax<JSONObject> result = new Ajax<>();
    	try {
    		String token = new String();
    		String address = new String();
        	if(CommonUtils.judge(String.valueOf(supplierLocation.getToken()))){
        		token = supplierLocation.getToken();
        	}
        	if(CommonUtils.judge(String.valueOf(supplierLocation.getToken()))){
        		address = supplierLocation.getAddress();
        	}
			int userId = 0;
			if(!StringUtil.isEmpty(token)){
				userId = topicService.searchUser(token);
	    		if(userId == 0){
	   				result.setCode(-9);
	   				result.setErrMsg("用户token有误");
	   				return result;
	    		}
			}else{
				result.setCode(-9);
   				result.setErrMsg("用户token有误");
   				return result;
			}
        	ShopsLocation shopsLocation = new ShopsLocation();
        	shopsLocation.setAddress(address);
        	shopsLocation.setUser_id(userId);
        	StringBuffer url = new StringBuffer();
        	url.append("http://api.map.baidu.com/geocoder/v2/?address=")
        	   .append(address)
        	   .append("&output=json&ak=hKSq4jsczk9n4qGZHdRbjaWmacgIZefl");
        	String httpResult =  HttpReqUtil.doGet(url.toString());
        	JSONObject jsonObj = JSONObject.parseObject(httpResult);
        	if(jsonObj.getIntValue("status") == 0){
	        	JSONObject jsonObj2 = JSONObject.parseObject(jsonObj.get("result").toString());
	        	String lng = jsonObj2.getString("lng");
	        	String lat = jsonObj2.getString("lat");
	        	shopsLocation.setLat(lat);
	        	shopsLocation.setLng(lng);
        	}
		    System.out.println("请求前端的退款接口返回状态为："+jsonObj.getIntValue("status"));
    		    int i = orderLocationService.addshops(shopsLocation);
    		    if(i==1){
        		    result.setCode(0);
    		    }else{
    		    	result.setCode(1);
    		    }
    			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}
    }

    /**
     * 修改代理的地理位置
     * @param supplierLocation  代理的实体类
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/supplier/updateLocation",method = RequestMethod.POST)
    @ApiOperation(value = "修改供应商的地理位置")
    public Ajax<?> updateLocation(@RequestBody SupplierLocation supplierLocation)throws Exception{
    	log.info("客户端请求数据【/supplier/updateLocation】,{}" + supplierLocation.toString());
    	Ajax<JSONObject> result = new Ajax<>();
    	try {
    		String token = new String();
    		String lat = new String();
    		String lng = new String();
        	if(CommonUtils.judge(String.valueOf(supplierLocation.getToken()))){
        		token = supplierLocation.getToken();
        	}
        	if(CommonUtils.judge(String.valueOf(supplierLocation.getLat()))){
        		lat = supplierLocation.getLat();
        	}
        	if(CommonUtils.judge(String.valueOf(supplierLocation.getLng()))){
        		lng = supplierLocation.getLng();
        	}
			int userId = 0;
			if(!StringUtil.isEmpty(token)){
				userId = topicService.searchUser(token);
	    		if(userId == 0){
	   				result.setCode(-9);
	   				result.setErrMsg("用户token有误");
	   				return result;
	    		}
			}else{
				result.setCode(-9);
   				result.setErrMsg("用户token有误");
   				return result;
			}
			//注意这里返回的是数据库中match 的条数而不是被影响的条数，需要在jdbc的连接出多加一个参数useAffectedRows=true
    		int i = orderLocationService.updateSupplierLocation( userId, lat,lng);
		    if(i==1){
    		    result.setCode(0);
        		result.setData(null);
		    }else{
		    	result.setCode(1);
	    		result.setData(null);
		    }
            return result;
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}
    }
    
    @ApiOperation(value = "订单弹框", notes = "订单弹框",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = ShopsLocationOrder.class)
    @ResponseBody
    @RequestMapping(value = "/orderLocation/shopsLocationOrder", method = RequestMethod.POST)
    public Ajax shopsLocationOrder(@RequestBody ApiRequest apiRequest) throws Exception {
    	log.info("客户端请求数据【/orderLocation/shopsLocationOrder】,{}" + apiRequest.toString());
    	Ajax<List<ShopsLocationOrder>> result = new Ajax<>();
//        UsersEntity userInfo = auth(apiRequest.getToken());
//        int user_id = userInfo.getId();
        List<ShopsLocationOrder> list = orderLocationDao.locationOrderList();
        result.setCode(0);
        result.setData(list);
    	return result;
    }
    
    // 测试生成小程序二维码的接口
    @ResponseBody
    @RequestMapping(value = "/orderLocation/shopsOrder", method = RequestMethod.POST)
    public Ajax shopsOrder(HttpServletRequest request) throws Exception {
    	log.info("客户端请求数据,{}");
    	Ajax<String> result = new Ajax<>();
    	String downloadUrl = CommonUtils.createwxaqrcode(request);
    	System.out.println(downloadUrl);
        result.setCode(0);
        result.setData(downloadUrl);
    	return result;
    }
    
}
