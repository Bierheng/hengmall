package com.hengmall.goods.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.hengmall.goods.model.ProductReq;
import com.hengmall.goods.model.ShopCarouselEntity;
import com.hengmall.goods.model.ShopsInfo;
import com.hengmall.goods.model.ShopsInfoReq;
import com.hengmall.goods.model.ShopsTypeBean;
import com.hengmall.goods.model.api.Ajax;
import com.hengmall.goods.service.ShopsService;
import com.hengmall.goods.service.TopicService;
import com.hengmall.goods.util.CommonUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "店铺管理", description = "店铺管理")
@RestController
public class ShopsController{

    final static Logger log= LoggerFactory.getLogger(ShopsController.class);
    
    @Autowired
    private ShopsService shopsService;
    
    @Autowired
    private  TopicService topicService;
    
    /**
     * 根据店主ID获取店铺板块商品列表，只显示十条
     * TODO 要做自定义的列表获取
     * @param userId 店铺ID
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/TopproductList",method = RequestMethod.POST)
    @ApiOperation(value = "（前端）根据店主ID获取店铺板块商品列表" ,response = ShopsInfoReq.class)
    public Ajax<?> getTopProductList(@RequestBody ShopsInfoReq shopsInfoReq)throws Exception{
    	log.info("客户端请求数据【/shops/TopproductList】,{}");
    	Ajax<JSONObject> result = new Ajax<>();
    	try {
    		String token = new String();
        	if(CommonUtils.judge(String.valueOf(shopsInfoReq.getToken()))){
        		token = shopsInfoReq.getToken();
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
    		int shopsId  = shopsInfoReq.getId();
    		JSONObject obj = shopsService.getTopProductList(shopsId);
    		result.setCode(0);
    		result.setData(obj);
    		return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(1);
			result.setErrMsg("获取店铺板块商品列表失败！");
    		return result;
		}
    }
    
    /**
     * 根据店主ID获取店铺商品二级分类
     * @param storeId 店铺ID
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/productTypeList",method = RequestMethod.POST)
    @ApiOperation(value = "（前端）根据店主ID获取店铺商品二级分类" ,response = ShopsInfoReq.class)
    public Ajax<?> getShopsProductTypeList(@RequestBody ShopsInfoReq shopsInfoReq)throws Exception{
    	log.info("客户端请求数据【/shops/productTypeList】,{} ");
    	Ajax<List<ShopsTypeBean>> result = new Ajax<>();
    	try {
    		String token = new String();
        	if(CommonUtils.judge(String.valueOf(shopsInfoReq.getToken()))){
        		token = shopsInfoReq.getToken();
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
    		int storeId = shopsInfoReq.getId();//店铺的ID
    		List<ShopsTypeBean> list = shopsService.getProductTypeList(storeId);
    		result.setCode(0);
    		result.setData(list);
    		return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(-1);
			result.setErrMsg("获取店铺商品二级分类失败！");
			return result;
		}
    }
    
    /**
     * （前端）根据店主ID获取店铺商品列表
     * @param storeId 店铺ID
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/productNewList",method = RequestMethod.POST)
    @ApiOperation(value = "（前端）根据店铺ID获取店铺商品列表" ,response = ProductReq.class)
    public Ajax<?> getShopsProductListNew(@RequestBody ShopsInfoReq shopsInfoReq)throws Exception{
    	log.info("客户端请求数据【/shops/productNewList】,{}" );
    	Ajax<List<ProductReq>> result = new Ajax<>();
    	try {
    		String token = new String();
        	if(CommonUtils.judge(String.valueOf(shopsInfoReq.getToken()))){
        		token = shopsInfoReq.getToken();
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
    		int storeId = shopsInfoReq.getId();//店铺的ID
    		int orderStatus = shopsInfoReq.getOrderStatus();
    		List<Integer> typeIdList = shopsInfoReq.getTypeId();
            if (storeId <= 0 || orderStatus <= 0) throw new Exception("请求数据不正确，请重新请求！");
            List<ProductReq> list = shopsService.queryByOrderStatus(storeId, orderStatus,typeIdList);
            result.setData(list);
            result.setCode(0);
    		return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(-1);
			result.setErrMsg("获取店铺商品列表失败！");
			return result;
		}
    }
    
    /**
     * （前端）根据店主ID获取店铺商品列表
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/info",method = RequestMethod.POST)
    @ApiOperation(value = "（前端）根据店主ID获取店铺信息" ,response = ProductReq.class)
    public Ajax<?> getShops(@RequestBody ShopsInfoReq shopsInfoReq)throws Exception{
    	log.info("客户端请求数据【/shops/info】,{}" );
    	Ajax<ShopsInfo> result = new Ajax<>();
    	try {
    		String token = new String();
        	if(CommonUtils.judge(String.valueOf(shopsInfoReq.getToken()))){
        		token = shopsInfoReq.getToken();
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
    		int storeId  = shopsInfoReq.getId();
            if (storeId <= 0) throw new Exception("请求数据不正确，请重新请求！");
            ShopsInfo shopsInfo = shopsService.queryShops(userId,storeId);
            List<ShopCarouselEntity> list = shopsService.getShopsCarousel(storeId);
            shopsInfo.setCarouselList(list);
            result.setData(shopsInfo);
            result.setCode(0);
    		return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(-1);
			result.setErrMsg("根据店主ID获取店铺信息失败！");
			return result;
		}
    }
    
    /**
     * （前端）根据分类ID获取商品信息列表
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/TypeList",method = RequestMethod.POST)
    @ApiOperation(value = "（前端）根据分类ID获取商品信息列表" ,response = ProductReq.class)
    public Ajax<?> getShopsTypeList(@RequestBody ShopsInfoReq shopsInfoReq)throws Exception{
    	log.info("客户端请求数据【/shops/TypeList】,{}" );
    	Ajax<List<ProductReq>> result = new Ajax<>();
    	try {
    		String token = new String();
        	if(CommonUtils.judge(String.valueOf(shopsInfoReq.getToken()))){
        		token = shopsInfoReq.getToken();
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
    		List<Integer> typeList = shopsInfoReq.getTypeId();
    		int storeId  = shopsInfoReq.getId();
            if (storeId <= 0) throw new Exception("请求数据不正确，请重新请求！");
            List<ProductReq> list = shopsService.queryShopsTypeList(storeId,typeList);
            result.setData(list);
            result.setCode(0);
    		return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(-1);
			result.setErrMsg("根据店主ID获取店铺信息失败！");
			return result;
		}
    }
    
    /**
     * 店铺关注
     * @param storeId  店铺ID
     * @param token    用户Token用于确定是哪个用户
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/attention",method = RequestMethod.POST)
    @ApiOperation(value = "(前端)店铺关注")
    public Ajax<?> attention(@RequestBody ShopsInfoReq shopsInfoReq)throws Exception{
    	log.info("客户端请求数据【/shops/attention】,{}"+shopsInfoReq.toString());
    	Ajax<ShopsInfo> result = new Ajax<>();
    	int storeId = 0;
    	String token = new String();
    	if(CommonUtils.judge(String.valueOf(shopsInfoReq.getToken()))){
    		token = shopsInfoReq.getToken();
    	}
    	if(CommonUtils.judge(String.valueOf(shopsInfoReq.getId()))){
    		storeId = shopsInfoReq.getId();
    	}
    	try {
    		//此处的逻辑暂时为，拿到这两个ID去指定的表中查询是否有对应的记录，如有就删除，如果没有就新建，同时需要对表的两个两个字段设为联合主键来防止重复添加
			int userId = 0;
			if(!StringUtil.isEmpty(token)){
				//获取用户ID
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
			//关注的起调方法
    		int i = shopsService.attention(storeId,userId);
    		if(i == 0){
    			result.setCode(0);
    		}else{
    			result.setCode(1);
    		}
    		ShopsInfo shopsInfo = new  ShopsInfo();
    		Boolean a = shopsService.isAttention(storeId, userId);
    		if(a){
    			shopsInfo.setIsAttention(1);
    		}else{
    			shopsInfo.setIsAttention(2);
    		}
    		result.setData(shopsInfo);
        	return result;
		} catch (Exception e){
			e.printStackTrace();
			result.setCode(1);
			return result;
		}
    }
}
