package com.hengmall.goods.service.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengmall.goods.dao.ApplyRefundDao;
import com.hengmall.goods.dao.CombineOrderDao;
import com.hengmall.goods.dao.SOrderDao;
import com.hengmall.goods.dao.SOrderExtraDao;
import com.hengmall.goods.dao.UsersDao;
import com.hengmall.goods.dao.WalletDetailsDao;
import com.hengmall.goods.model.CombineOrderEntity;
import com.hengmall.goods.model.SOrderExtraEntity;
import com.hengmall.goods.model.UsersEntity;
import com.hengmall.goods.model.constitute.WechatRefundApiResult;
import com.hengmall.goods.service.OrderService;
import com.hengmall.goods.util.WechatUtil;

@Service	
public class OrderServiceImpl implements OrderService {

	 final static Logger log= LoggerFactory.getLogger(OrderServiceImpl.class);
	
	 @Autowired
	 private CombineOrderDao combineOrderDao;
	 @Autowired
	 private WalletDetailsDao walletDetailsDao;
	 @Autowired
	 private UsersDao usersDao;
	 @Autowired
	 private SOrderDao sOrderDao;
	 @Autowired
	 private SOrderExtraDao sOrderExtraDao;
	 @Autowired
	 private ApplyRefundDao applyRefundDao;
	 
	    @Override
	    @Transactional
	    public int combineOrderAutoRefund()throws Exception{
	    	List<CombineOrderEntity> combineOrderList = combineOrderDao.autoOrderList();
	    	int i=0;
	    	for(CombineOrderEntity combineOrder : combineOrderList){
	    		// 自动退款的逻辑为，检测是哪种支付方式，分为微信支付以及钱包支付，首先根据订单号去查询子订单以获取相关订单的信息，用来判断是什么支付方式，
	    		//然后根据不同的支付方式发起不同的退款流程，如果是微信支付将直接调用退款接口，根据订单号进行退款，如果是钱包支付，将查询付款金额，将钱退回钱包，增加退款记录
	    		String out_trade_no = combineOrder.getOut_trade_no();
	    		SOrderExtraEntity orderExtra = combineOrderDao.queryPaymethodByNo(out_trade_no);
	    		int paymethod = orderExtra.getPaymethod();
	    		int order_id = orderExtra.getOrderid();
	    		int shops_id = orderExtra.getShops_id();
	    		int user_id = orderExtra.getUser_id();
	    		int price = (int)orderExtra.getPrice();
	    		UsersEntity user = usersDao.queryById(user_id);
	    		// 开始退款操作
	    		if(paymethod == 1){//微信支付
	    	        //进入退款申请审批流程
	    	        applyRefundDao.insert(user_id, order_id, user.getNickname(),
	    	        		user.getPhone(), "拼团失败，失效自动退款！本次退款方式为微信支付退款",shops_id,1);
	    	        // 退款API接口，退款单号，订单金额，退款金额
	    	        WechatRefundApiResult result = WechatUtil.wxRefund(order_id+"*"+shops_id,0.01, 0.01);
	    	        log.info("退款返回信息：{}", result);
	    	        String msg = "";
	    	        if (result.getResult_code().equals("SUCCESS")) {
	    	            msg = "退款成功";
	    	        } else {
	    	            throw new Exception(result.getErr_code_des());
	    	        }
	    	        log.info("拼单订单自动退款成功，退款订单号为："+out_trade_no);
	    		}else if(paymethod == 3) {//钱包支付
	    	        //进入退款申请审批流程
	    	        applyRefundDao.insert(user_id, order_id, user.getNickname(),
	    	        		user.getPhone(), "拼团失败，失效自动退款！本次退款方式为退款到钱包",shops_id,1);
	    			int money = user.getMoney();
	    			int newMoney = money + price;
	    			usersDao.addMoney(price, user_id);
	    			walletDetailsDao.insertToWalletDetails(user_id, price, money, newMoney,1 );
	    			log.info("拼单订单自动退款成功，退款订单号为："+out_trade_no);
	    		}
	    		// 改变订单的状态
                //修改退款成功状态，将收货状态改为已退款
                sOrderDao.changeRefundStatus(-2, out_trade_no);
                
                //此处会将订单子表的支付方式改为钱包支付，用于标识订单使用的付款方式
                sOrderExtraDao.updateStatus(-2, order_id,3,2);

                // 此处修改拼单订单状态为拼单失败，根据订单号进行修改
                combineOrderDao.updateStatus( out_trade_no, 2);
	    		
	    		i++;
	    	}
	    	log.info("拼单订单自动退款服务执行完毕，修改的订单数为："+i);
	        return 1;
	    }
}
