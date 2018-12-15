package com.hengmall.user.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.hengmall.user.dao.ApplyRefundDao;
import com.hengmall.user.dao.SOrderDao;
import com.hengmall.user.model.ApplyRefund;
import com.hengmall.user.model.ApplyRefundReq;
import com.hengmall.user.model.CombineDetailsResp;
import com.hengmall.user.model.CombineSaleEntity;
import com.hengmall.user.model.DataTablesResult;
import com.hengmall.user.model.DataTablesResultShops;
import com.hengmall.user.model.Result;
import com.hengmall.user.model.SOrderEntity;
import com.hengmall.user.model.SOrderExtraEntity;
import com.hengmall.user.model.order.DispatchRequest;
import com.hengmall.user.model.order.DispatchResponse;
import com.hengmall.user.model.order.OrderDistributionRequest;
import com.hengmall.user.model.order.OrderDistributionResponse;
import com.hengmall.user.model.order.OrderLiveRequest;
import com.hengmall.user.model.order.OrderLiveResponse;
import com.hengmall.user.model.order.SaveUserRequest;
import com.hengmall.user.model.order.UsersRequest;
import com.hengmall.user.model.order.UsersResponse;
import com.hengmall.user.model.persistence.Page;
import com.hengmall.user.service.OrderService;
import com.hengmall.user.service.UserService;
import com.hengmall.user.service.common.BaseService;
import com.hengmall.user.util.CommonUtils;
import com.hengmall.user.util.HttpReqUtil;
import com.hengmall.user.util.JedisConnectUtil;
import com.hengmall.user.util.ResultUtil;
import com.hengmall.user.util.config.Global;

@Service
public class OrderServiceImpl extends BaseService implements OrderService {

	 final static Logger log= LoggerFactory.getLogger(OrderServiceImpl.class);
	

    @Autowired
    private SOrderDao sOrderDao;
    @Autowired
    private ApplyRefundDao applyRefundDao;
    
    @Autowired
    private PlatformTransactionManager transactionManager;
	
    @Autowired
    private UserService userService;
    /**
     * 修改子订单的状态
     * @param id   订单ID
     * @param status  要转为的状态码
     * @return
     * @throws Exception
     */
	@Override
	@Transactional
    public DataTablesResult updateOrderExtraById( int id ,int status,int shops_id,String tracking_num,String express)throws Exception{
		DataTablesResult result = new DataTablesResult();
		if(CommonUtils.judge(String.valueOf(id)) && CommonUtils.judge(String.valueOf(status))){
    		sOrderDao.updateOrderExtraAll(status, id,shops_id,tracking_num,express);
			result.setSuccess(true);
			result.setError("修改子订单的状态成功！");
            return result;
		}else{
			result.setSuccess(false);
			result.setError("修改子订单的状态失败！");
			return result;
		}
    }	
	
	 /**
     * 获取所有订单列表
     * @param draw  当前页码
     * @param length  页面大小
     * @return
     * @throws Exception
     */
	@Override
	@Transactional
    public DataTablesResult getOrderList(int draw,int length,Integer orderId )throws Exception{
    		if(CommonUtils.judge(String.valueOf(draw))&&CommonUtils.judge(String.valueOf(length))){
        		Map<String, Object> param = new HashMap<>();
        		DataTablesResult result = new DataTablesResult();
        		param.put("page", (draw-1)*length);
        		param.put("pagesize", length);
        		param.put("orderId", orderId);
        		List<SOrderEntity> orderList =sOrderDao.orderList(param);
        		result.setData(orderList);
        		result.setSuccess(true);
                return result;
    		}else{
        		DataTablesResult result = new DataTablesResult();
    			result.setSuccess(false);
    			result.setError("传入参数为空！");
    			return result;
    		}
    }
	
    /**
     * 根据订单获取所有子订单列表
     * @param orderId  订单ID
     * @return
     * @throws Exception
     */
	@Override
	@Transactional
    public DataTablesResult getOrderExtraListById(int orderId)throws Exception{
    		DataTablesResult result = new DataTablesResult();
    		if(CommonUtils.judge(String.valueOf(orderId))){
    			
    			List<DataTablesResultShops> shopsList = sOrderDao.shopsList(orderId);
    			List<SOrderExtraEntity> orderExtraList =sOrderDao.orderExtraList(orderId);
    			for (DataTablesResultShops ent : shopsList) {
    				List<SOrderExtraEntity> list = new ArrayList<>();
    				int status  =  0;
    				int orderExtraId = 0;
					for(SOrderExtraEntity ord : orderExtraList) {
						ord.setPrice(ord.getPrice()/100*1.0);
						if(ent.getId().equals(String.valueOf(ord.getShopsId()))) {
							list.add(ord);
							status = ord.getStatus();
							orderExtraId = ord.getOrder_id();
						}
					}
					ent.setPlatformList(list);
					ent.setStatus(status);
					ent.setOrderExtraId(orderExtraId);
				}
        		result.setData(shopsList);
        		result.setSuccess(true);
                return result;
    		}else{
    			result.setError("订单ID为空或不可用");
    			return result;
    		}
    }
	
	 /**
     * 获取订单记录总数
     * @return
     * @throws Exception
     */
	@Override
	@Transactional
    public Result<Object> countOrder() throws Exception{
    		int result = sOrderDao.countOrder();
            return new ResultUtil<Object>().setData(result);
    }
	
	@Override
	public DataTablesResult getOrderRefundList(int draw, int length) {
		DataTablesResult result = new DataTablesResult();
		List<ApplyRefund> list = applyRefundDao.refundList();
		result.setData(list);
		result.setSuccess(true);
		return result;
	}
	
	@Override
	public DataTablesResult updateRefundApproval(int id, String userName) {
		DataTablesResult result = new DataTablesResult();
		ApplyRefund applyRefund = applyRefundDao.getRefundById(id);
		if(applyRefund.getStatus() == ApplyRefund.APPROVAL_STATUS){
			applyRefund.setApproval_user(userName);
			applyRefund.setStatus(ApplyRefund.PASS_STATUS);
			applyRefundDao.updateById(applyRefund);
		}else{
			result.setSuccess(false);
			result.setError("退款订单状态错误");
			return result;
		}
		result.setSuccess(true);
		return result;
	}
	
	@Override
	public DataTablesResult updateRefundRefuse(int id, String userName,String refuse_reason) {
		DataTablesResult result = new DataTablesResult();
		ApplyRefund applyRefund = applyRefundDao.getRefundById(id);
		if(applyRefund.getStatus() == ApplyRefund.APPROVAL_STATUS){
			applyRefund.setApproval_user(userName);
			applyRefund.setRefuse_reason(refuse_reason);
			applyRefund.setStatus(ApplyRefund.REFUSE_STATUS);
			applyRefundDao.updateById(applyRefund);
		}else{
			result.setSuccess(false);
			result.setError("退款订单状态错误");
			return result;
		}
		result.setSuccess(true);
		return result;
	}
	
	 /**
     * 获取退款订单记录总数
     * @return
     * @throws Exception
     */
	@Override
	public Result<Object> countRefundOrder() {
		int result = applyRefundDao.countRefund();
        return new ResultUtil<Object>().setData(result);
	}
	
	
	/**
	 * 分销收益订单列表
	 */
	@Override
	public Page<OrderDistributionResponse> distributionList(Page<OrderDistributionResponse> page,
			OrderDistributionRequest orderDistributionRequest) {
		//数据过滤
		orderDistributionRequest.getSqlMap().put("sql", dataScopeFilter(orderDistributionRequest.getToken(),"u"));
												
		PageHelper.startPage(orderDistributionRequest.getPage(),orderDistributionRequest.getListPage());
		List<OrderDistributionResponse> list = applyRefundDao.distributionList(orderDistributionRequest);
		page.setData(list);
		page.setCount(new PageInfo<>(list).getTotal());
		return page;
	}
	
	
	/**
	 * 直播推销员订单列表
	 */
	@Override
	public Page<OrderLiveResponse> liveList(Page<OrderLiveResponse> page, OrderLiveRequest orderLiveRequest) {
		//数据过滤
		orderLiveRequest.getSqlMap().put("sql", dataScopeFilter(orderLiveRequest.getToken(),"tu"));
														
		PageHelper.startPage(orderLiveRequest.getPage(),orderLiveRequest.getListPage());
		List<OrderLiveResponse> list = applyRefundDao.liveList(orderLiveRequest);
		page.setData(list);
		page.setCount(new PageInfo<>(list).getTotal());
		return page;
	}
	
	
	/**
	 * 派单管理列表
	 */
	@Override
	public Page<DispatchResponse> dispatchList(Page<DispatchResponse> page, DispatchRequest dispatchRequest) {
		//数据过滤
		dispatchRequest.getSqlMap().put("sql", dataScopeFilter(dispatchRequest.getToken(),"tu"));
																
		PageHelper.startPage(dispatchRequest.getPage(),dispatchRequest.getListPage());
		List<DispatchResponse> list = applyRefundDao.dispatchList(dispatchRequest);
		page.setData(list);
		page.setCount(new PageInfo<>(list).getTotal());
		return page;
	}
	
	
	/**
	 * 派单员列表
	 */
	@Override
	public List<UsersResponse> usersList(UsersRequest usersRequest) {
		return applyRefundDao.usersList(usersRequest);
	}
	
	
	/**
	 * 派单
	 * @throws Exception 
	 */
	@Override
	public void saveUser(SaveUserRequest saveUserRequest) throws Exception {
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			//检查订单与用户是否存在
			int order = applyRefundDao.inspectOrder(saveUserRequest.getOrderId());
			int user = applyRefundDao.inspectUser(saveUserRequest.getUserId());
			if(order==0 || user==0) {
				throw new Exception("订单或派单员id不存在");
			}
			if(applyRefundDao.isOnLine(saveUserRequest.getUserId()) == 1) {
				throw new Exception("派单员没在线，暂无法派单");
			}
			
			applyRefundDao.saveUser(saveUserRequest);
			applyRefundDao.saveState(saveUserRequest.getOrderId());
			transactionManager.commit(status);
		} catch (Exception e) {
			transactionManager.rollback(status);
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public SOrderExtraEntity queryPaymethod(String out_trade_no) throws Exception {
		SOrderExtraEntity orderExtraEntity = new SOrderExtraEntity();
		orderExtraEntity = sOrderDao.queryPaymethodByNo(out_trade_no);
		return orderExtraEntity;
	}
	
	@Override
	public SOrderExtraEntity queryPaymethod(int shops_id, int order_id) throws Exception {
		SOrderExtraEntity orderExtraEntity = new SOrderExtraEntity();
		orderExtraEntity = sOrderDao.queryPaymethodById(shops_id, order_id);
		return orderExtraEntity;
	}

	// 处理退款后续对应的拼单订单需要修改的
	@Override
	@Transactional
	public void dealCombine(String out_trade_no) {
		sOrderDao.updateCombineOrder(out_trade_no, 2);
        SOrderEntity sOrderEntity =  sOrderDao.findByOutTradeNo(out_trade_no);
        int initiator_id = sOrderEntity.getInitiator_id();
        int combine_sale_id = sOrderEntity.getCombine_sale_id();
        int user_id = sOrderEntity.getUser_id();
        CombineSaleEntity combineSale = sOrderDao.findById(combine_sale_id);
        //还需要修改拼单主还需拼单人数
        // 拼单创建人与下单人是否一致，如果是通一个人就是发起拼单，否则就是参与拼单
        List<CombineDetailsResp> list = sOrderDao.queryByFlashSaleId(combine_sale_id, user_id);
        int still_need = combineSale.getCombine_num() - list.size() +1;
        if(initiator_id == user_id ){
        	//发起拼单的人申请了退款，将拼单状态改为失败，然后另选一个人变为拼单主
        	sOrderDao.update(combine_sale_id, user_id, out_trade_no, still_need, initiator_id,1);
        	//将拼单状态改为失败
        	sOrderDao.updateStatus(combine_sale_id, user_id, out_trade_no, still_need, initiator_id, 2);
        	
        	for(CombineDetailsResp combineDetails :list){
        		String out_trade = combineDetails.getOut_trade_no();
        		
        		if(out_trade_no.equals(out_trade)){
        			continue;
        		}else {
                	//将该拼单改为拼单主,并结束当前循环
                	sOrderDao.updateInitiator(out_trade_no, still_need, 1);
                	break;
        		}
        	}
        	
        } else{
        	
        	//将拼单状态改为失败
        	sOrderDao.updateStatus(combine_sale_id, user_id, out_trade_no, still_need, initiator_id, 2);
        	//参与拼单，修改拼单主的还需人数，变更所有拼单成员的还需拼单人数
        	sOrderDao.update(combine_sale_id, user_id, out_trade_no, still_need, initiator_id,1);
        	sOrderDao.updateComplete(combine_sale_id, user_id, out_trade_no, still_need, initiator_id, 1);
        }
	}
	
	// 退款处理
	@Transactional
	@Override
	public DataTablesResult dealApproval(ApplyRefundReq applyRefundReq,String token,String url) throws Exception {
		DataTablesResult result = new DataTablesResult();
		
    	String user = JedisConnectUtil.hget(Global.getConfig("login.userDb"), token);
		JSONObject object = JSON.parseObject(user);
        String userName = (String)object.get("username");
    	System.out.println(user);
    	int  id = applyRefundReq.getId();
    	if(!StringUtil.isEmpty(user)){
        	System.out.println(token);
        	System.out.println(url);
        	Boolean result2 = userService.judgeRole(token, url);
        	if(!result2){
        		result.setSuccess(false);
	        		result.setError("权限校验失败，你没有该操作权限！");
	        		return result;
        	}
        	// 正式调用退款逻辑，由于钱包的存在，需要先对订单的支付方式进行判断然后再分别调用退款
    		int  order_id = applyRefundReq.getOrder_id();
    		int  shops_id = applyRefundReq.getShops_id();
    		
    		// 修改退款订单记录的状态
    		result = updateRefundApproval(id,userName);
 		    SOrderExtraEntity orderExtra = queryPaymethod(shops_id,order_id);
 		    int paymethod = orderExtra.getPaymethod();
    		int user_id = orderExtra.getUser_id();
    		int price = (int)orderExtra.getPrice();
    		int combineStatus = orderExtra.getCombine_status();
    		String tracking_num = orderExtra.getTracking_num();
    		String express = orderExtra.getExpress();
 		    JSONObject obj = new JSONObject();
 		    obj.put("token", "42854a4417a060e3cdc808c7730366d6");
// 		    obj.put("outTradeNo", applyRefundReq.getOut_trade_no());
 		    obj.put("order_id", order_id);
 		    obj.put("shops_id", shops_id);
     		   if(paymethod == 1){//微信支付退款
			    String httpResult =  HttpReqUtil.doPost("https://wmall.mfish.cn/store/api/refund", obj.toString());
 			    String[] arr = httpResult.split(",");
 			    String code = arr[0];
 			    String[] arr2 = code.split(":");
 			    String codeValue = arr2[1];
 			    System.out.println("请求前端的退款接口返回状态为："+codeValue);
 			    //在测试环境时，此处可能会造成BUG需要注意
 			    if(!"0".equals(codeValue)){
 			    	result.setSuccess(false);
 	        		result.setError("前端处理退款失败！");
 	        		return result;
 			    }else{
  	     		   // 退款处理成功，此处对拼单订单进行处理，1：将对应的拼单订单改为失败，2：将拼单主的剩余人数增加
 			    	if(combineStatus != 0){
 			    		String out_trade_no = orderExtra.getOut_trade_no();
 			    		 dealCombine(out_trade_no);
 			    	}
 			    	updateOrderExtraById(order_id, -2,shops_id,tracking_num,express);
 			    	return result;
 			    }
     		   }else if(paymethod == 3) {//钱包支付退款
     			    int result3 = userService.pursePay(user_id,price);
	    			log.info("钱包退款成功，退款参数为： shops_id"+shops_id+"order_id"+order_id);
		     		   // 退款处理成功，此处对拼单订单进行处理，1：将对应的拼单订单改为失败，2：将拼单主的剩余人数增加
 			    	if(combineStatus != 0){
 			    		String out_trade_no = orderExtra.getOut_trade_no();
			    		dealCombine(out_trade_no);
			    	}
 			    	updateOrderExtraById(order_id, -2,shops_id,tracking_num,express);
	    			result.setSuccess(true);
	    			return result;
	    		}else{
	    			result.setSuccess(false);
	    			result.setError("订单支付方式错误，订单退款审核通过失败！");
	    			return result;
	    		}
    	}else{
    		result.setSuccess(false);
    		result.setError("用户token有误！");
    		return result;
    	}
	}
}
