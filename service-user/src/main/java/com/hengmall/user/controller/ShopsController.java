package com.hengmall.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hengmall.user.model.DataTablesResult;
import com.hengmall.user.model.Result;
import com.hengmall.user.model.SOrderEntity;
import com.hengmall.user.model.ShopsInfo;
import com.hengmall.user.model.ShopsInfoReq;
import com.hengmall.user.model.ShopsTypeBean;
import com.hengmall.user.model.ShopsValue;
import com.hengmall.user.model.TbShopsType;
import com.hengmall.user.model.TbShopsTypeAddReq;
import com.hengmall.user.model.TbShopsTypeDelReq;
import com.hengmall.user.model.Users;
import com.hengmall.user.model.api.Ajax;
import com.hengmall.user.model.manage.product.ProductBean;
import com.hengmall.user.model.manage.product.ProductReq;
import com.hengmall.user.model.manage.product.SProductBean;
import com.hengmall.user.model.persistence.Page;
import com.hengmall.user.model.shops.AddShopsRequest;
import com.hengmall.user.model.shops.AppraiseDelRequest;
import com.hengmall.user.model.shops.AppraiseListRequest;
import com.hengmall.user.model.shops.AppraiseListResponse;
import com.hengmall.user.model.shops.AppraiseSubRequest;
import com.hengmall.user.model.shops.CarouselShopsDelRequest;
import com.hengmall.user.model.shops.CarouselShopsListRequest;
import com.hengmall.user.model.shops.CarouselShopsListResponse;
import com.hengmall.user.model.shops.CarouselShopsSaveRequest;
import com.hengmall.user.model.shops.SearchShopsProductRequest;
import com.hengmall.user.model.shops.ShopsAddRequest;
import com.hengmall.user.model.shops.ShopsAttentionRequest;
import com.hengmall.user.model.shops.ShopsAttentionResponse;
import com.hengmall.user.model.shops.ShopsDelRequest;
import com.hengmall.user.model.shops.ShopsOrderRequest;
import com.hengmall.user.model.shops.ShopsOrderResponse;
import com.hengmall.user.model.shops.ShopsOrderSaveRequest;
import com.hengmall.user.model.shops.ShopsPlateDelRequest;
import com.hengmall.user.model.shops.ShopsPlateRequest;
import com.hengmall.user.model.shops.ShopsPlateResponse;
import com.hengmall.user.model.shops.ShopsPlateSaveRequest;
import com.hengmall.user.model.shops.ShopsPlateproductAddRequest;
import com.hengmall.user.model.shops.ShopsPlateproductDelRequest;
import com.hengmall.user.model.shops.ShopsPlateproductRequest;
import com.hengmall.user.model.shops.ShopsPlateproductResponse;
import com.hengmall.user.model.shops.ShopsProductListRequest;
import com.hengmall.user.model.shops.ShopsProductListResponse;
import com.hengmall.user.model.shops.ShopsProductRequest;
import com.hengmall.user.model.shops.ShopsProductResponse;
import com.hengmall.user.model.shops.ShopsRequest;
import com.hengmall.user.model.shops.ShopsUserRequest;
import com.hengmall.user.model.shops.ShopsUserResponse;
import com.hengmall.user.model.shops.TbShopsOrderRequest;
import com.hengmall.user.model.shops.TbShopsOrderResponse;
import com.hengmall.user.model.shops.UpdateShopsRequest;
import com.hengmall.user.model.shops.examine.ConfirmRequest;
import com.hengmall.user.model.shops.examine.ExamineListRequest;
import com.hengmall.user.model.shops.examine.ExamineRequest;
import com.hengmall.user.model.shops.examine.ExamineResponse;
import com.hengmall.user.model.shops.shopEntity.ShopsResponse;
import com.hengmall.user.service.ShopsService;
import com.hengmall.user.util.ResultUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "店铺管理", description = "店铺管理")
@RestController
public class ShopsController{

    final static Logger log= LoggerFactory.getLogger(ShopsController.class);
    
    @Autowired
    private ShopsService shopsService;


    
    /**
     * 根据店主ID获取所有店铺订单列表
     * @param draw  当前页码
     * @param length  页面大小
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/orderList",method = RequestMethod.GET)
    @ApiOperation(value = "根据店主ID获取所有店铺订单列表" ,response = SOrderEntity.class)
    public DataTablesResult getShopsOrderList(int draw,int length,int userId)throws Exception{
    	log.info("客户端请求数据【/shops/orderList】,{}" );
    	try {
    		DataTablesResult result = shopsService.getShopsOrderList(draw, length, userId);
    		return result;
		} catch (Exception e) {
			e.printStackTrace();
			DataTablesResult result = new DataTablesResult();
			result.setSuccess(false);
			result.setError("根据店主ID获取所有店铺订单列表失败！");
			return result;
		}
    }
    
    /**
     * 根据店主ID获取所有店铺订单列表总数
     * @param userId  用户ID
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/orderCount",method = RequestMethod.GET)
    @ApiOperation(value = "根据店主ID获取所有店铺订单列表总数" )
    public Result<Object> getShopsOrderCountById( int userId)throws Exception{
    	log.info("客户端请求数据【/shops/orderCount】,{}" + userId );
    	try {
    		int result = shopsService.getShopsOrderCountById(userId);
    		return new ResultUtil<Object>().setData(result);
		} catch (Exception e) {
			e.printStackTrace();
			Result<Object> result = new Result<Object>();
			result.setSuccess(false);
			result.setErrMsg("根据店主获取所有店铺订单列表总数失败！");
			return result;
		}
    }
    
    /**
     * 根据店主ID获取所有店铺会员列表
     * @param draw  当前页码
     * @param length  页面大小
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/memberList",method = RequestMethod.GET)
    @ApiOperation(value = "根据店主ID获取所有店铺会员列表" ,response = Users.class)
    public DataTablesResult getShopsMemberList(int draw,int length,int userId)throws Exception{
    	log.info("客户端请求数据【/shops/memberList】,{}"+userId );
    	try {
    		DataTablesResult result = shopsService.getShopsMemberList(draw, length, userId);
    		return result;
		} catch (Exception e) {
			e.printStackTrace();
			DataTablesResult result = new DataTablesResult();
			result.setSuccess(false);
			result.setError("根据店主ID获取所有店铺会员列表失败！");
			return result;
		}
    }
    
    /**
     * 根据店主ID获取所有店铺会员列表总数
     * @param userId  用户ID
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/memberCount",method = RequestMethod.GET)
    @ApiOperation(value = "根据店主ID获取所有店铺会员列表总数" )
    public Result<Object> getShopsMemberCountById( int userId)throws Exception{
    	log.info("客户端请求数据【/shops/memberCount】,{}" + userId );
    	try {
    		int result = shopsService.getShopsMemberCountById(userId);
    		return new ResultUtil<Object>().setData(result);
		} catch (Exception e) {
			e.printStackTrace();
			Result<Object> result = new Result<Object>();
			result.setSuccess(false);
			result.setErrMsg("根据店主获取所有店铺订单列表总数失败！");
			return result;
		}
    }
    
    /**
     * 根据店主ID获取所有店铺获得佣金记录
     * @param draw  当前页码
     * @param length  页面大小
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/valueList",method = RequestMethod.GET)
    @ApiOperation(value = "根据店主ID获取店铺佣金记录" ,response = ShopsValue.class)
    public DataTablesResult getShopsValueList(int draw,int length,int userId)throws Exception{
    	log.info("客户端请求数据【/shops/memberList】,{}"+userId );
    	try {
    		DataTablesResult result = shopsService.getShopsValueList(draw, length, userId);
    		return result;
		} catch (Exception e) {
			e.printStackTrace();
			DataTablesResult result = new DataTablesResult();
			result.setSuccess(false);
			result.setError("根据店主ID获取店铺佣金记录失败！");
			return result;
		}
    }
    
    /**
     * 根据店主ID获取所有店铺总佣金
     * @param userId  用户ID
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/valueCount",method = RequestMethod.GET)
    @ApiOperation(value = "根据店主ID获取店铺总佣金")
    public Result<Object> getShopsValueCountById( int userId)throws Exception{
    	log.info("客户端请求数据【/shops/valueCount】,{}" + userId );
    	try {
    		int result = shopsService.getShopsValueCountById(userId);
    		return new ResultUtil<Object>().setData((double)(result/100));
		} catch (Exception e) {
			e.printStackTrace();
			Result<Object> result = new Result<Object>();
			result.setSuccess(false);
			result.setErrMsg("根据店主获取店铺总佣金失败！");
			return result;
		}
    }
    
    /**
     * 根据店主ID获取所有店铺标签列表
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/shopsTypeList",method = RequestMethod.GET)
    @ApiOperation(value = "根据店铺ID获取所有店铺标签列表" ,response = SProductBean.class)
    public Ajax<List<TbShopsType>> getShopsTypeList(int shops_id)throws Exception{
    	log.info("客户端请求数据【/shops/shopsTypeList】,{}"+shops_id );
    	Ajax<List<TbShopsType>> ajax = new Ajax<>();
    	try {
    		List<TbShopsType> result = shopsService.getShopsTypeList(shops_id);
    		ajax.setCode(0);
			ajax.setData(result);
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("店铺标签错误："+e);
			ajax.setCode(-1);
			ajax.setErrMsg("根据店铺ID获取所有店铺标签失败！");
			return ajax;
		}
    }
    
    
    /**
     * 店铺标签库添加、修改
     * @param tbShopsTypeAddReq 请求新增的对象
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/shopsTypeSave",method = RequestMethod.POST)
    @ApiOperation(value = "店铺标签库添加、修改" ,response = Ajax.class)
    public Ajax<Object> tbShopsTypeAdd(@RequestBody @Valid TbShopsTypeAddReq tbShopsTypeAddReq,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/shops/shopsTypeSave】,{}" );
    	Ajax<Object> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
        		StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else{
    			int id = shopsService.tbShopsTypeAdd(tbShopsTypeAddReq);
    			ajax.setData(id);
        		ajax.setCode(0);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("店铺标签库添加、修改失败");
			return ajax;
		}
    }
	
	/**
	 * 店铺标签库删除
	 * @param tbShopsTypeDelReq  请求删除的对象
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/shops/shopsTypeDel",method = RequestMethod.POST)
    @ApiOperation(value = "店铺标签库删除" ,response = Ajax.class)
    public Ajax<Object> tbShopsTypeDel(@RequestBody @Valid TbShopsTypeDelReq tbShopsTypeDelReq,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/shops/shopsTypeDel】,{}" );
    	Ajax<Object> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
        		StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else{
    			int shops_id = tbShopsTypeDelReq.getShops_id();
    			shopsService.tbShopsTypeDel(tbShopsTypeDelReq, shops_id);
    		}
    		ajax.setCode(0);
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg(e.getMessage());
			return ajax;
		}
    }
    
    /**
     * 根据店主ID获取所有店铺商品总数
     * @param draw  当前页码
     * @param length  页面大小
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/productCount",method = RequestMethod.GET)
    @ApiOperation(value = "根据店主ID获取所有店铺商品总数" ,response = SProductBean.class)
    public Result<Object> getShopsProductCountById(int draw,int length,int userId)throws Exception{
    	log.info("客户端请求数据【/shops/productCount】,{}"+userId );
    	try {
    		int result = shopsService.getShopsProductCountById(userId);
    		return new ResultUtil<Object>().setData(result);
		} catch (Exception e) {
			e.printStackTrace();
			Result<Object> result = new Result<Object>();
			result.setSuccess(false);
			result.setErrMsg("根据店主ID获取所有店铺商品总数失败！");
			return result;
		}
    }
    
    /**
     * 根据店主ID获取店铺爆款商品列表，只显示十条
     * TODO 要做自定义的列表获取
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/productList",method = RequestMethod.POST)
    @ApiOperation(value = "（前端）根据店主ID获取店铺爆款商品列表" ,response = SProductBean.class)
    public DataTablesResult getProductList(@RequestBody ShopsInfoReq shopsInfoReq)throws Exception{
    	log.info("客户端请求数据【/shops/productList】,{}");
    	try {
    		int userId  = shopsInfoReq.getId();
    		int draw = 1;
    		int length = 8;
    		DataTablesResult result = shopsService.getProductList(draw, length, userId);
    		return result;
		} catch (Exception e) {
			e.printStackTrace();
			DataTablesResult result = new DataTablesResult();
			result.setSuccess(false);
			result.setError("根据店主ID获取店铺爆款商品列表失败！");
			return result;
		}
    }
    
    /**
     * 根据店主ID获取店铺商品二级分类
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/productTypeList",method = RequestMethod.POST)
    @ApiOperation(value = "（前端）根据店主ID获取店铺商品二级分类" ,response = ShopsTypeBean.class)
    public Ajax<List<ShopsTypeBean>> getShopsProductTypeList(@RequestBody ShopsInfoReq shopsInfoReq)throws Exception{
    	log.info("客户端请求数据【/shops/productTypeList】,{} ");
    	Ajax<List<ShopsTypeBean>> result = new Ajax<>();
    	try {
    		int userId  = shopsInfoReq.getId();
    		List<ShopsTypeBean> list = shopsService.getProductTypeList( userId);
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
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/productNewList",method = RequestMethod.POST)
    @ApiOperation(value = "（前端）根据店主ID获取店铺商品列表" ,response = ProductReq.class)
    public Ajax<List<ProductReq>> getShopsProductListNew(@RequestBody ShopsInfoReq shopsInfoReq)throws Exception{
    	log.info("客户端请求数据【/shops/productNewList】,{}" );
    	Ajax<List<ProductReq>> ajax = new Ajax<>();
    	try {
    		int userId  = shopsInfoReq.getId();
    		int orderStatus = shopsInfoReq.getOrderStatus();
    		List<Integer> typeIdList = shopsInfoReq.getTypeId();
            if (userId <= 0 || orderStatus <= 0) throw new Exception("请求数据不正确，请重新请求！");
            List<ProductReq> list = shopsService.queryByOrderStatus(userId, orderStatus,typeIdList);
            ajax.setData(list);
            ajax.setCode(0);
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("获取店铺商品列表失败！");
			return ajax;
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
    public Ajax<ShopsInfo> getShops(@RequestBody ShopsInfoReq shopsInfoReq)throws Exception{
    	log.info("客户端请求数据【/shops/info】,{}" );
    	Ajax<ShopsInfo> ajax = new Ajax<>();
    	try {
    		int storeId  = shopsInfoReq.getId();
    		int userId  = shopsInfoReq.getUserId();
            if (storeId <= 0) throw new Exception("请求数据不正确，请重新请求！");
            ShopsInfo list = shopsService.queryShops(storeId,userId);
            ajax.setData(list);
            ajax.setCode(0);
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("根据店主ID获取店铺信息失败！");
			return ajax;
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
    public Ajax<List<ProductReq>> getShopsTypeList(@RequestBody ShopsInfoReq shopsInfoReq)throws Exception{
    	log.info("客户端请求数据【/shops/TypeList】,{}" );
    	Ajax<List<ProductReq>> ajax = new Ajax<>();
    	try {
    		List<Integer> typeList = shopsInfoReq.getTypeId();
    		int storeId  = shopsInfoReq.getId();
            if (storeId <= 0) throw new Exception("请求数据不正确，请重新请求！");
            List<ProductReq> list = shopsService.queryShopsTypeList(storeId,typeList);
            ajax.setData(list);
            ajax.setCode(0);
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("根据店主ID获取店铺信息失败！");
			return ajax;
		}
    }
    
    
    /****************************************************************************************************************/
    
    
    /**
     * 店铺列表
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/shopList",method = RequestMethod.POST)
    @ApiOperation(value = "店铺列表" ,response = ShopsResponse.class)
    public Ajax<Page<ShopsResponse>> shopList(@RequestBody ShopsRequest shopsRequest,HttpServletRequest request)throws Exception{
    	log.info("客户端请求数据【/shops/shopList】,{}" );
    	Ajax<Page<ShopsResponse>> ajax = new Ajax<>();
    	try {
    		shopsRequest.setToken(request.getHeader("Authorization"));
    		Page<ShopsResponse> shopList = shopsService.shopList(new Page<ShopsResponse>(),shopsRequest);
    		ajax.setData(shopList);
    		ajax.setCode(0);
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("获取店铺列表失败！");
			return ajax;
		}
    }
    
    /**
     * 店铺粉丝列表
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/shopAttentionList",method = RequestMethod.POST)
    @ApiOperation(value = "店铺粉丝列表" ,response = ShopsAttentionResponse.class)
    public Ajax<Page<ShopsAttentionResponse>> shopAttentionList(@RequestBody @Valid ShopsAttentionRequest shopsAttentionRequest,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/shops/shopAttentionList】,{}" );
    	Ajax<Page<ShopsAttentionResponse>> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
    			StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else {
    			Page<ShopsAttentionResponse> shopAttentionList = shopsService.shopAttentionList(new Page<ShopsAttentionResponse>(),shopsAttentionRequest);
    			ajax.setCode(0);
    			ajax.setData(shopAttentionList);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("获取店铺粉丝列表失败！");
			return ajax;
		}
    }
    
    
    
    /**
     * 新增店铺
     * @param shopsRequest
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/newShop",method = RequestMethod.POST)
    @ApiOperation(value = "新增店铺" ,response = Ajax.class)
    public Ajax<Object> newShop(@RequestBody @Valid AddShopsRequest addShopsRequest,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/shops/newShop】,{}" );
    	Ajax<Object> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
        		StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else{
    			shopsService.newShop(addShopsRequest);
        		ajax.setCode(0);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("新增店铺失败");
			return ajax;
		}
    }
    
    
    /**
     * 修改店铺
     * @param shopsRequest
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/updateShop",method = RequestMethod.POST)
    @ApiOperation(value = "修改店铺" ,response = Ajax.class)
    public Ajax<Object> updateShop(@RequestBody @Valid UpdateShopsRequest updateShopsRequest,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/shops/updateShop】,{}" );
    	Ajax<Object> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
        		StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else{
    			int num = shopsService.updateShop(updateShopsRequest);
    			if(num < 1) {
    				ajax.setCode(-1);
    				ajax.setErrMsg("修改店铺失败,找不到该店铺");
    				return ajax;
    			}
        		ajax.setCode(0);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("修改店铺失败");
			return ajax;
		}
    }
    
    
    
    /**
     * 店铺会员列表
     * @param shopsAttentionRequest
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/shopUserList",method = RequestMethod.POST)
    @ApiOperation(value = "店铺会员列表" ,response = ShopsAttentionResponse.class)
    public Ajax<Page<ShopsUserResponse>> shopUserList(@RequestBody @Valid ShopsUserRequest shopsUserRequest,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/shops/shopUserList】,{}" );
    	Ajax<Page<ShopsUserResponse>> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
    			StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else {
    			Page<ShopsUserResponse> shopUserList = shopsService.shopUserList(shopsUserRequest);
    			ajax.setCode(0);
    			ajax.setData(shopUserList);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("获取店铺会员列表失败！");
			return ajax;
		}
    }
    
    
    /**
     * 店铺板块列表
     * @param shopsUserRequest
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/shopPlateList",method = RequestMethod.POST)
    @ApiOperation(value = "店铺板块列表" ,response = ShopsPlateResponse.class)
    public Ajax<Page<ShopsPlateResponse>> shopPlateList(@RequestBody @Valid ShopsPlateRequest shopsPlateRequest,BindingResult result,HttpServletRequest request)throws Exception{
    	log.info("客户端请求数据【/shops/shopPlateList】,{}" );
    	Ajax<Page<ShopsPlateResponse>> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
    			StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else {
    			shopsPlateRequest.setToken(request.getHeader("Authorization"));
    			Page<ShopsPlateResponse> shopPlateList = shopsService.shopPlateList(new Page<ShopsPlateResponse>(),shopsPlateRequest);
    			ajax.setCode(0);
    			ajax.setData(shopPlateList);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("获取店铺板块列表失败！");
			return ajax;
		}
    }
    
    
    /**
     * 店铺板块增改
     * @param shopsPlateproductDelRequest
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/shopsPlateSave",method = RequestMethod.POST)
    @ApiOperation(value = "店铺板块增改" ,response = Ajax.class)
    public Ajax<Object> shopPlateSave(@RequestBody @Valid ShopsPlateSaveRequest shopsPlateSaveRequest,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/shops/shopsPlateSave】,{}" );
    	Ajax<Object> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
        		StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else{
    			shopsService.shopPlateSave(shopsPlateSaveRequest);
        		ajax.setCode(0);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("店铺板块增改失败");
			return ajax;
		}
    }
    
    /**
     * 店铺板块删除
     * @param shopsPlateproductDelRequest
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/shopsPlateSaveDel",method = RequestMethod.POST)
    @ApiOperation(value = "店铺板块删除" ,response = Ajax.class)
    public Ajax<Object> shopPlateSaveDel(@RequestBody @Valid ShopsPlateDelRequest shopsPlateDelRequest,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/shops/shopsPlateSaveDel】,{}" );
    	Ajax<Object> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
        		StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else{
    			int num = shopsService.shopsPlateSaveDel(shopsPlateDelRequest);
    			if(num < 1) {
    				ajax.setCode(-1);
    				ajax.setErrMsg("店铺板块删除失败,没有这个店铺板块");
    				return ajax;
    			}
        		ajax.setCode(0);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("店铺板块删除失败");
			return ajax;
		}
    }
    
    
    /**
     * 店铺板块商品列表
     * @param shopsPlateRequest
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/shopPlateproductList",method = RequestMethod.POST)
    @ApiOperation(value = "店铺板块商品列表" ,response = ShopsPlateproductResponse.class)
    public Ajax<Page<ShopsPlateproductResponse>> shopPlateproductList(@RequestBody @Valid ShopsPlateproductRequest shopsPlateproductRequest,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/shops/shopPlateproductList】,{}" );
    	Ajax<Page<ShopsPlateproductResponse>> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
    			StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else {
    			Page<ShopsPlateproductResponse> shopPlateproductList = shopsService.shopPlateproductList(shopsPlateproductRequest);
    			ajax.setCode(0);
    			ajax.setData(shopPlateproductList);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("获取店铺板块商品列表失败！");
			return ajax;
		}
    }
    
    
    /**
     * 移除店铺板块商品
     * @param updateShopsRequest
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/shopPlateproductDel",method = RequestMethod.POST)
    @ApiOperation(value = "移除店铺板块商品" ,response = Ajax.class)
    public Ajax<Object> updateShop(@RequestBody @Valid ShopsPlateproductDelRequest shopsPlateproductDelRequest,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/shops/shopPlateproductDel】,{}" );
    	Ajax<Object> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
        		StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else{
    			int num = shopsService.shopPlateproductDel(shopsPlateproductDelRequest);
    			if(num < 1) {
    				ajax.setCode(-1);
    				ajax.setErrMsg("移除店铺板块商品失败,没有这个商品");
    				return ajax;
    			}
        		ajax.setCode(0);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("修改店铺失败");
			return ajax;
		}
    }
    
    /**
     * 添加店铺板块商品
     * @param shopsPlateproductDelRequest
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/addPrpducts",method = RequestMethod.POST)
    @ApiOperation(value = "添加店铺板块商品" ,response = Ajax.class)
    public Ajax<Object> addPrpducts(@RequestBody @Valid ShopsPlateproductAddRequest shopsPlateproductAddRequest,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/shops/shopPlateproductDel】,{}" );
    	Ajax<Object> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
        		StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else{
    			int num = shopsService.addPrpducts(shopsPlateproductAddRequest);
    			if(num < 1) {
    				ajax.setCode(-1);
    				ajax.setErrMsg("添加店铺板块商品失败：未增加记录");
    				return ajax;
    			}
        		ajax.setCode(0);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("添加店铺板块商品失败");
			return ajax;
		}
    }
    
    
    /**
     * 获取商品列表（公用）
     * @param shopsPlateproductRequest
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/shopsProductList",method = RequestMethod.POST)
    @ApiOperation(value = "获取商品列表（公用）" ,response = ShopsProductListResponse.class)
    public Ajax<Page<ShopsProductListResponse>> shopsProductList(@RequestBody @Valid ShopsProductListRequest shopsProductListRequest,BindingResult result
    		,HttpServletRequest request)throws Exception{
    	log.info("客户端请求数据【/shops/shopsProductList】,{}" );
    	Ajax<Page<ShopsProductListResponse>> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
    			StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else {
    			//自定义验证
    			String shopsId = shopsProductListRequest.getShopsId();
    			String plateId = shopsProductListRequest.getPlateId();
    			if(plateId != null && !plateId.equals("")) {
    				if(shopsId == null || shopsId.equals("")) {
    					ajax.setCode(-1);
    	    			ajax.setErrMsg("选择板块之后必须要选择一个店铺");
    	    			return ajax;
    				}
    			}
    			Page<ShopsProductListResponse> shopsProductList = shopsService.shopsProductList(shopsProductListRequest);
    			ajax.setCode(0);
    			ajax.setData(shopsProductList);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("获取商品列表失败！");
			return ajax;
		}
    }
    
    
    /**
     * 店铺商品列表
     * @param shopsProductListRequest
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/shopsProduct",method = RequestMethod.POST)
    @ApiOperation(value = "店铺商品列表" ,response = ShopsProductResponse.class)
    public Ajax<Page<ShopsProductResponse>> shopsProduct(@RequestBody @Valid ShopsProductRequest shopsProductRequest,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/shops/shopsProduct】,{}" );
    	Ajax<Page<ShopsProductResponse>> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
    			StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else {
    			Page<ShopsProductResponse> shopsProduct = shopsService.shopsProduct(shopsProductRequest);
    			ajax.setCode(0);
    			ajax.setData(shopsProduct);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("获取店铺商品列表失败！");
			return ajax;
		}
    }
    
	/**
	 * @return 
	 * @Title: 店铺商品搜索
	 * @Description: 
	 */
	@ApiOperation(value = "店铺商品搜索", notes = "商品搜索", response = ProductBean.class, httpMethod = "POST")
	@ResponseBody
	@RequestMapping(value = "/shops/searchShopsProduct", method = RequestMethod.POST)
	public  Ajax<Page<ShopsProductListResponse>> searchShopsProduct(@RequestBody @Valid SearchShopsProductRequest searchShopsProductRequest,BindingResult result)throws Exception {
		log.info("客户端请求数据【manage/product/searchProduct】,{}");
		Ajax<Page<ShopsProductListResponse>> ajax = new Ajax<>();
		try {
    		if(result.hasErrors()) {
    			StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else {
    			Page<ShopsProductListResponse> shopsProduct = shopsService.searchShopsProduct(searchShopsProductRequest);
				ajax.setCode(0);
				ajax.setData(shopsProduct);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("获取店铺商品搜索列表失败！");
			return ajax;
		}
	}
    
    /**
     * 移除店铺商品
     * @param shopsPlateproductDelRequest
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/shopDel",method = RequestMethod.POST)
    @ApiOperation(value = "移除店铺商品" ,response = Ajax.class)
    public Ajax<Object> shopDel(@RequestBody @Valid ShopsDelRequest shopsDelRequest,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/shops/shopPlateproductDel】,{}" );
    	Ajax<Object> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
        		StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else{
    			int num = shopsService.shopDel(shopsDelRequest);
    			if(num < 1) {
    				ajax.setCode(-1);
    				ajax.setErrMsg("移除店铺商品,没有这个商品");
    				return ajax;
    			}
        		ajax.setCode(0);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("移除店铺商品失败");
			return ajax;
		}
    }
    
    
    /**
     * 添加店铺商品
     * @param shopsDelRequest
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/shopAdd",method = RequestMethod.POST)
    @ApiOperation(value = "添加店铺商品" ,response = Ajax.class)
    public Ajax<Object> shopAdd(@RequestBody @Valid ShopsAddRequest shopsAddRequest,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/shops/shopAdd】,{}" );
    	Ajax<Object> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
        		StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else{
    			shopsService.shopAdd(shopsAddRequest);
        		ajax.setCode(0);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("添加商品失败");
			return ajax;
		}
    }
    
    
    /**
     * 店铺佣金列表
     * @param shopsUserRequest
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/shopsOrder",method = RequestMethod.POST)
    @ApiOperation(value = "店铺佣金列表" ,response = ShopsOrderResponse.class)
    public Ajax<Page<ShopsOrderResponse>> shopsOrder(@RequestBody @Valid ShopsOrderRequest shopsOrderRequest,BindingResult result,HttpServletRequest request)throws Exception{
    	log.info("客户端请求数据【/shops/shopsOrder】,{}" );
    	Ajax<Page<ShopsOrderResponse>> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
    			StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else {
    			shopsOrderRequest.setToken(request.getHeader("Authorization"));
    			Page<ShopsOrderResponse> shopsOrder = shopsService.shopsOrder(new Page<ShopsOrderResponse>(),shopsOrderRequest);
    			ajax.setCode(0);
    			ajax.setData(shopsOrder);    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("获取店铺佣金列表失败！");
			return ajax;
		}
    }
    
    
    /**
     * 店铺商品评论列表
     * @param shopsOrderRequest
     * @param result
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/appraiseList",method = RequestMethod.POST)
    @ApiOperation(value = "店铺商品评论列表" ,response = AppraiseListResponse.class)
    public Ajax<Page<AppraiseListResponse>> appraiseList(@RequestBody @Valid AppraiseListRequest appraiseListRequest,BindingResult result,HttpServletRequest request)throws Exception{
    	log.info("客户端请求数据【/shops/appraiseList】,{}" );
    	Ajax<Page<AppraiseListResponse>> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
    			StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else {
    			appraiseListRequest.setToken(request.getHeader("Authorization"));    				
    			Page<AppraiseListResponse> appraiseList = shopsService.appraiseList(new Page<AppraiseListResponse>(),appraiseListRequest);
    			ajax.setCode(0);
    			ajax.setData(appraiseList);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("获取店铺商品评论列表失败！");
			return ajax;
		}
    }
    
    
    @RequestMapping(value = "/shops/appraiseSave",method = RequestMethod.POST)
    @ApiOperation(value = "店铺商品评论增改" ,response = Ajax.class)
    public Ajax<Object> appraiseSave(@RequestBody @Valid AppraiseSubRequest appraiseSubRequest,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/shops/appraiseSave】,{}" );
    	Ajax<Object> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
        		StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else{
    			shopsService.appraiseSave(appraiseSubRequest);
        		ajax.setCode(0);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg(e.getMessage());
			return ajax;
		}
    }
    
    
    /**
     * 店铺商品评论删除
     * @param shopsDelRequest
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/appraiseDel",method = RequestMethod.POST)
    @ApiOperation(value = "店铺商品评论删除" ,response = Ajax.class)
    public Ajax<Object> appraiseDel(@RequestBody @Valid AppraiseDelRequest appraiseDelRequest,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/shops/appraiseDel】,{}" );
    	Ajax<Object> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
        		StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else{
    			int num = shopsService.appraiseDel(appraiseDelRequest);
    			if(num < 1) {
    				ajax.setCode(-1);
    				ajax.setErrMsg("店铺商品评论删除失败,没有这个评论");
    				return ajax;
    			}
        		ajax.setCode(0);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("店铺商品评论删除失败");
			return ajax;
		}
    }
    
    
    /**
     * 店铺轮播图列表
     * @param appraiseListRequest
     * @param result
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/carouselShopsList",method = RequestMethod.POST)
    @ApiOperation(value = "店铺轮播图列表" ,response = CarouselShopsListResponse.class)
    public Ajax<Page<CarouselShopsListResponse>> carouselShopsList(@RequestBody @Valid CarouselShopsListRequest carouselShopsListRequest,BindingResult result,HttpServletRequest request)throws Exception{
    	log.info("客户端请求数据【/shops/carouselShopsList】,{}" );
    	Ajax<Page<CarouselShopsListResponse>> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
    			StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else {
    			carouselShopsListRequest.setToken(request.getHeader("Authorization"));    				
    			Page<CarouselShopsListResponse> carouselShopsList = shopsService.carouselShopsList(new Page<CarouselShopsListResponse>(),carouselShopsListRequest);
    			ajax.setCode(0);
    			ajax.setData(carouselShopsList);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("获取店铺轮播图列表失败！");
			return ajax;
		}
    }
    
    
    /**
     * 店铺轮播图增改
     * @param appraiseDelRequest
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/carouselShopsSave",method = RequestMethod.POST)
    @ApiOperation(value = "店铺轮播图增改" ,response = Ajax.class)
    public Ajax<Object> carouselShopsSave(@RequestBody @Valid CarouselShopsSaveRequest carouselShopsSaveRequest,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/shops/carouselShopsSave】,{}" );
    	Ajax<Object> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
        		StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else{
    			shopsService.carouselShopsSave(carouselShopsSaveRequest);
        		ajax.setCode(0);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("店铺轮播图增加或修改失败");
			return ajax;
		}
    }
    
    
    /**
     * 店铺轮播图删除
     * @param carouselShopsSaveRequest
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/carouselShopsDel",method = RequestMethod.POST)
    @ApiOperation(value = "店铺轮播图删除" ,response = Ajax.class)
    public Ajax<Object> carouselShopsDel(@RequestBody @Valid CarouselShopsDelRequest carouselShopsDelRequest,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/shops/carouselShopsDel】,{}" );
    	Ajax<Object> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
        		StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else{
    			int num = shopsService.carouselShopsDel(carouselShopsDelRequest);
    			if(num < 1) {
    				ajax.setCode(-1);
    				ajax.setErrMsg("店铺轮播图删除失败:没有这个轮播图");
    				return ajax;
    			}
        		ajax.setCode(0);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("店铺轮播图增加或修改失败");
			return ajax;
		}
    }
    
    
    /**
     * 店铺订单列表
     * @param carouselShopsListRequest
     * @param result
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/shopsOrderList",method = RequestMethod.POST)
    @ApiOperation(value = "店铺订单列表" ,response = TbShopsOrderResponse.class)
    public Ajax<Page<TbShopsOrderResponse>> shopsOrderList(@RequestBody @Valid TbShopsOrderRequest tbShopsOrderRequest,BindingResult result,HttpServletRequest request)throws Exception{
    	log.info("客户端请求数据【/shops/shopsOrderList】,{}" );
    	Ajax<Page<TbShopsOrderResponse>> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
    			StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else {
    			tbShopsOrderRequest.setToken(request.getHeader("Authorization"));    				
    			Page<TbShopsOrderResponse> shopsOrderList = shopsService.shopsOrderList(new Page<TbShopsOrderResponse>(),tbShopsOrderRequest);
    			ajax.setCode(0);
    			ajax.setData(shopsOrderList);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("获取店铺订单列表失败！");
			return ajax;
		}
    }
    
    
    @RequestMapping(value = "/shops/shopsOrderSave",method = RequestMethod.POST)
    @ApiOperation(value = "店铺订单操作对外发布" ,response = Ajax.class)
    public Ajax<Object> shopsOrderSave(@RequestBody @Valid ShopsOrderSaveRequest shopsOrderSaveRequest,BindingResult result,HttpServletRequest request)throws Exception{
    	log.info("客户端请求数据【/shops/shopsOrderSave】,{}" );
    	Ajax<Object> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
        		StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else{
    			shopsOrderSaveRequest.setToken(request.getHeader("Authorization"));
    			shopsService.shopsOrderSave(shopsOrderSaveRequest);
    		}
    		ajax.setCode(0);
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg(e.getMessage());
			return ajax;
		}
    }

    
    
    /**
     * 店铺审核列表
     * @param examineReq
     * @param result
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/examineList",method = RequestMethod.POST)
    @ApiOperation(value = "店铺审核列表" ,response = ExamineResponse.class)
    public Ajax<Page<ExamineResponse>> examineList(@RequestBody @Valid ExamineListRequest examineListRequest,BindingResult result,HttpServletRequest request)throws Exception{
    	log.info("客户端请求数据【/shops/examineList】,{}" );
    	Ajax<Page<ExamineResponse>> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
    			StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else {
    			examineListRequest.setToken(request.getHeader("Authorization"));    				
    			Page<ExamineResponse> shopsOrderList = shopsService.examineList(new Page<ExamineResponse>(),examineListRequest);
    			ajax.setCode(0);
    			ajax.setData(shopsOrderList);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("获取店铺审核列表失败！");
			return ajax;
		}
    }
    
    
    /**
     * 店铺审核提交申请
     * @param examineRequest
     * @param result
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/examineSub",method = RequestMethod.POST)
    @ApiOperation(value = "店铺审核提交申请" ,response = Ajax.class)
    public Ajax<Object> examineSub(@RequestBody @Valid ExamineRequest examineRequest,BindingResult result,HttpServletRequest request)throws Exception{
    	log.info("客户端请求数据【/shops/examineSub】,{}" );
    	Ajax<Object> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
        		StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else{
    			examineRequest.setToken(request.getHeader("Authorization"));
    			int num = shopsService.examineSub(examineRequest);
    			if(num < 1) {
    				ajax.setCode(-1);
    				ajax.setErrMsg("提交申请失败:申请类型不存在");
    				return ajax;
    			}
        		ajax.setCode(0);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg(e.getMessage());
			return ajax;
		}
    }
    
    
    /**
     * 店铺审核操作
     * @param confirmRequest
     * @param result
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shops/isAgree",method = RequestMethod.POST)
    @ApiOperation(value = "店铺审核操作" ,response = Ajax.class)
    public Ajax<Object> isAgree(@RequestBody @Valid ConfirmRequest confirmRequest,BindingResult result,HttpServletRequest request)throws Exception{
    	log.info("客户端请求数据【/shops/isAgree】,{}" );
    	Ajax<Object> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
        		StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else{
    			shopsService.isAgree(confirmRequest);
        		ajax.setCode(0);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg(e.getMessage());
			return ajax;
		}
    }
}
