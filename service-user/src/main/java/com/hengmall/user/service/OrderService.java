package com.server.service;

import java.util.List;

import com.server.entity.ApplyRefundReq;
import com.server.entity.DataTablesResult;
import com.server.entity.Result;
import com.server.entity.SOrderExtraEntity;
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

public interface OrderService {
		
    /**
     * 修改子订单的状态
     * @param id   订单ID
     * @param status  要转为的状态码
     * @return
     * @throws Exception
     */
	DataTablesResult updateOrderExtraById(int id, int status,int shops_id,String tracking_num,String express) throws Exception;
	
   	
	 /**
     * 获取所有订单列表
     * @param draw  当前页码
     * @param length  页面大小
     * @return
     * @throws Exception
     */
	DataTablesResult getOrderList(int draw, int length,Integer orderId) throws Exception;
	
    /**
     * 根据订单获取所有子订单列表
     * @param orderId  订单ID
     * @return
     * @throws Exception
     */
	DataTablesResult getOrderExtraListById(int orderId) throws Exception;

	 /**
     * 获取订单记录总数
     * @return
     * @throws Exception
     */
	Result<Object> countOrder() throws Exception;

	/**
	 * 获取订单退款的列表
	 * @param draw
	 * @param length
	 * @return
	 */
	DataTablesResult getOrderRefundList(int draw, int length);

	/**
	 * 退款审核通过
	 * @param id
	 * @param userName
	 * @return
	 */
	DataTablesResult updateRefundApproval(int id, String userName);

	/**
	 * 
	 * @return
	 */
	Result<Object> countRefundOrder();

	/**
	 * 
	 * @param id
	 * @param userName
	 * @return
	 */
	DataTablesResult updateRefundRefuse(int id, String userName, String refuse_reason);
	
	
	/**
	 * 分销收益订单列表
	 * @param page
	 * @param orderDistributionRequest
	 * @return
	 */
	Page<OrderDistributionResponse> distributionList(Page<OrderDistributionResponse> page,OrderDistributionRequest orderDistributionRequest);
	
	
	/**
	 * 直播推销员订单列表
	 * @param page
	 * @param orderLiveRequest
	 * @return
	 */
	Page<OrderLiveResponse> liveList(Page<OrderLiveResponse> page,OrderLiveRequest orderLiveRequest);
	
	
	/**
	 * 派单管理列表
	 * @param page
	 * @param dispatchRequest
	 * @return
	 */
	Page<DispatchResponse> dispatchList(Page<DispatchResponse> page,DispatchRequest dispatchRequest);
	
	
	/**
	 * 派单员列表
	 * @param usersRequest
	 * @return
	 */
	List<UsersResponse> usersList(UsersRequest usersRequest);
	
	
	/**
	 * 派单
	 * @param saveUserRequest
	 * @return
	 * @throws Exception 
	 */
	void saveUser(SaveUserRequest saveUserRequest) throws Exception;

	SOrderExtraEntity queryPaymethod(String out_trade_no)throws Exception;


	void dealCombine(String out_trade_no)throws Exception;


	SOrderExtraEntity queryPaymethod(int shops_id, int order_id) throws Exception;


	DataTablesResult dealApproval(ApplyRefundReq applyRefundReq,String token,String url)throws Exception;
	
}
