package com.server.controller.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.server.entity.ApplyRefund;
import com.server.entity.ApplyRefundReq;
import com.server.entity.DataTablesResult;
import com.server.entity.Result;
import com.server.entity.SOrderEntity;
import com.server.entity.SOrderExtraEntity;
import com.server.entity.api.Ajax;
import com.server.entity.order.DispatchRequest;
import com.server.entity.order.DispatchResponse;
import com.server.entity.order.OrderDistributionRequest;
import com.server.entity.order.OrderDistributionResponse;
import com.server.entity.order.OrderLiveRequest;
import com.server.entity.order.OrderLiveResponse;
import com.server.entity.order.SaveUserRequest;
import com.server.entity.order.UsersRequest;
import com.server.entity.order.UsersResponse;
import com.server.entity.persistence.Page;
import com.server.service.OrderService;
import com.server.service.UserService;
import com.server.utils.HttpReqUtil;
import com.server.utils.JedisConnectUtil;
import com.server.utils.ResultUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "订单管理", description = "订单管理")
@RestController
public class OrderController{

    final static Logger log= LoggerFactory.getLogger(CommonController.class);
    
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;
    
    /**
     * 获取所有订单列表
     * @param draw  当前页码
     * @param length  页面大小
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/order/list",method = RequestMethod.GET)
    @ApiOperation(value = "获取所有订单列表" ,response = SOrderEntity.class)
    public DataTablesResult getOrderList(int draw,int length,Integer orderId )throws Exception{
    	log.info("客户端请求数据【/order/list】,{}" );
    	try {
    		DataTablesResult result = orderService.getOrderList(draw, length,orderId);
    		return result;
		} catch (Exception e) {
			e.printStackTrace();
			DataTablesResult result = new DataTablesResult();
			result.setSuccess(false);
			result.setError("获取所有订单列表失败！");
			return result;
		}
    }
    
    /**
     * 根据订单获取所有子订单列表
     * @param orderId  订单ID
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/order/orderExtraList",method = RequestMethod.GET)
    @ApiOperation(value = "根据订单获取所有子订单列表" ,response = SOrderExtraEntity.class)
    public DataTablesResult getOrderExtraListById( int orderId)throws Exception{
    	log.info("客户端请求数据【/order/orderExtraList】,{}" + orderId );
    	try {
    		DataTablesResult result = orderService.getOrderExtraListById(orderId);
    		return result;
		} catch (Exception e) {
			DataTablesResult result = new DataTablesResult();
			result.setSuccess(false);
			result.setError("根据订单获取所有子订单列表失败！");
			e.printStackTrace();
			return result;
		}
    }
    
    /**
     * 修改子订单的状态
     * @param id   订单ID
     * @param status  要转为的状态码
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/order/updateOrderExtraById",method = RequestMethod.GET)
    @ApiOperation(value = "修改子订单的状态",response = SOrderExtraEntity.class)
    public DataTablesResult updateOrderExtraById( int id ,int status,int shops_id ,String tracking_num,String express )throws Exception{
    	log.info("客户端请求数据【/order/orderExtraList】,{}"+ id);
    	try {
    		DataTablesResult result = orderService.updateOrderExtraById(id, status,shops_id,tracking_num,express);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			DataTablesResult result = new DataTablesResult();
			result.setSuccess(false);
			result.setError("修改子订单的状态失败！");
			return result;
		}
    }
    
    /**
     * 获取订单记录总数
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/order/count",method = RequestMethod.GET)
    @ApiOperation(value = "获取订单记录总数")
    public Result<Object> countOrder() throws Exception{
    	log.info("客户端请求数据【/order/count】,{}");
    	try {
    		Result<Object> result = orderService.countOrder();
            return result;
		} catch (Exception e) {
			e.printStackTrace();
			Result<Object> result = new Result<Object>();
			result.setSuccess(false);
			result.setErrMsg("获取总数失败！");
			return result;
		}
    }
    
    /**
     * 获取订单记录总数
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/order/countRefund",method = RequestMethod.GET)
    @ApiOperation(value = "获取订单记录总数")
    public Result<Object> countRefundOrder() throws Exception{
    	log.info("客户端请求数据【/order/countRefund】,{}");
    	try {
    		Result<Object> result = orderService.countRefundOrder();
            return result;
		} catch (Exception e) {
			e.printStackTrace();
			Result<Object> result = new Result<Object>();
			result.setSuccess(false);
			result.setErrMsg("获取总数失败！");
			return result;
		}
    }
    
    /**
     * 获取所有退款订单列表
     * @param draw  当前页码
     * @param length  页面大小
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/order/refundList",method = RequestMethod.GET)
    @ApiOperation(value = "获取所有退款订单列表" ,response = ApplyRefund.class)
    public DataTablesResult getOrderRefundList(int draw,int length )throws Exception{
    	log.info("客户端请求数据【/order/refundList】,{}" );
    	try {
    		DataTablesResult result = orderService.getOrderRefundList(draw, length);
    		return result;
		} catch (Exception e) {
			e.printStackTrace();
			DataTablesResult result = new DataTablesResult();
			result.setSuccess(false);
			result.setError("获取所有退款订单列表！");
			return result;
		}
    }
    
    /**
     * 订单退款审核通过
     * @param draw  当前页码
     * @param length  页面大小
     * @return
     * @throws Exception
     */
    @Transactional
    @RequestMapping(value = "/order/approval",method = RequestMethod.POST)
    @ApiOperation(value = "订单退款审核通过" ,response = ApplyRefund.class)
    public DataTablesResult updateRefundApproval(@RequestBody ApplyRefundReq applyRefundReq)throws Exception{
    	log.info("客户端请求数据【/order/approval】,{}" );
    	try {
    		DataTablesResult result = new DataTablesResult();
    		String token = request.getHeader("Authorization");
    		String url = request.getServletPath();
        	result = orderService.dealApproval( applyRefundReq , token, url);
        	return result;
		} catch (Exception e) {
			e.printStackTrace();
			DataTablesResult result = new DataTablesResult();
			result.setSuccess(false);
			result.setError("订单退款审核通过失败！");
			return result;
		}
    }
    
    /**
     * 订单退款审核通过
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/order/approvalRefuse",method = RequestMethod.POST)
    @ApiOperation(value = "订单退款审核拒绝" ,response = ApplyRefund.class)
    public DataTablesResult updateRefundRefuse(@RequestBody ApplyRefundReq ApplyRefundReq)throws Exception{
    	log.info("客户端请求数据【/order/approvalRefuse】,{}" );
    	try {
    		DataTablesResult result = new DataTablesResult();
    		String token = request.getHeader("Authorization");
    		JedisConnectUtil jedisConnectUtil = new JedisConnectUtil();
        	Boolean result1 =  jedisConnectUtil.exists(token);
        	int  id = ApplyRefundReq.getId();
        	String  refuse_reason = ApplyRefundReq.getRefuse_reason();
        	if(result1){
            	String url = request.getServletPath();
            	System.out.println(token);
            	System.out.println(url);
            	Boolean result2 = userService.judgeRole(token, url);
            	if(!result2){
            		result.setSuccess(false);
 	        		result.setError("权限校验失败，你没有该操作权限！");
 	        		return result;
            	}
        		String userName = JedisConnectUtil.getnx(token);
        		result = orderService.updateRefundRefuse(id,userName,refuse_reason);
        		return result;
        	}else{
        		result.setSuccess(false);
        		result.setError("用户token有误！");
        		return result;
        	}
		} catch (Exception e) {
			e.printStackTrace();
			DataTablesResult result = new DataTablesResult();
			result.setSuccess(false);
			result.setError("订单退款审核拒绝失败！");
			return result;
		}
    }
    
    @RequestMapping(value = "/order/test",method = RequestMethod.GET)
    @ApiOperation(value = "测试发送POST请求")
    public Result<Object> testToken(){
    	   try {
    		   JSONObject obj = new JSONObject();
    		   obj.put("token", "42854a4417a060e3cdc808c7730366d6");
			   String result =  HttpReqUtil.doPost("https://wmall.mfish.cn/store/api/refund", obj.toString());
			   System.out.println(result);
	    	   return new ResultUtil<Object>().setData(result);
		} catch (Exception e) {
			e.printStackTrace();
	    return new ResultUtil<Object>().setData(null);
		}
       }
    
    
    @RequestMapping(value = "/order/distributionList",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ApiOperation(value = "分销收益订单列表" ,response = OrderDistributionResponse.class)
    public Ajax<Page<OrderDistributionResponse>> distributionList(@RequestBody @Valid OrderDistributionRequest orderDistributionRequest,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/order/distributionList】,{}" );
    	Ajax<Page<OrderDistributionResponse>> ajax = new Ajax<>();
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
    			orderDistributionRequest.setToken(request.getHeader("Authorization"));
    			Page<OrderDistributionResponse> distributionList = orderService.distributionList(new Page<OrderDistributionResponse>(),orderDistributionRequest);
    			ajax.setCode(0);
    			ajax.setData(distributionList);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("获取分销收益订单列表失败！");
			return ajax;
		}
    }
    
    
    
    @RequestMapping(value = "/order/liveList",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ApiOperation(value = "直播推销员订单列表" ,response = OrderLiveResponse.class)
    public Ajax<Page<OrderLiveResponse>> liveList(@RequestBody @Valid OrderLiveRequest orderLiveRequest,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/order/liveList】,{}" );
    	Ajax<Page<OrderLiveResponse>> ajax = new Ajax<>();
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
    			orderLiveRequest.setToken(request.getHeader("Authorization"));
    			Page<OrderLiveResponse> liveList = orderService.liveList(new Page<OrderLiveResponse>(),orderLiveRequest);
    			ajax.setCode(0);
    			ajax.setData(liveList);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("获取直播推销员订单列表失败！");
			return ajax;
		}
    }
    
    
    @RequestMapping(value = "/order/dispatchList",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ApiOperation(value = "派单管理列表" ,response = DispatchResponse.class)
    public Ajax<Page<DispatchResponse>> dispatchList(@RequestBody @Valid DispatchRequest dispatchRequest,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/order/dispatchList】,{}" );
    	Ajax<Page<DispatchResponse>> ajax = new Ajax<>();
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
    			dispatchRequest.setToken(request.getHeader("Authorization"));
    			Page<DispatchResponse> dispatchList = orderService.dispatchList(new Page<DispatchResponse>(),dispatchRequest);
    			ajax.setCode(0);
    			ajax.setData(dispatchList);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("获取派单管理列表失败！");
			return ajax;
		}
    }
    
    
    @RequestMapping(value = "/order/usersList",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ApiOperation(value = "派单员列表" ,response = UsersResponse.class)
    public Ajax<List<UsersResponse>> usersList(@RequestBody @Valid UsersRequest usersRequest,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/order/usersList】,{}" );
    	Ajax<List<UsersResponse>> ajax = new Ajax<>();
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
    			usersRequest.setToken(request.getHeader("Authorization"));
    			List<UsersResponse> usersList = orderService.usersList(usersRequest);
    			ajax.setCode(0);
    			ajax.setData(usersList);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("获取派单员列表失败！");
			return ajax;
		}
    }
    
    
    @RequestMapping(value = "/order/saveUser",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ApiOperation(value = "派单" ,response = Ajax.class)
    public Ajax<Object> saveUser(@RequestBody @Valid SaveUserRequest saveUserRequest,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/order/saveUser】,{}" );
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
    		}else {
    			orderService.saveUser(saveUserRequest);
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
