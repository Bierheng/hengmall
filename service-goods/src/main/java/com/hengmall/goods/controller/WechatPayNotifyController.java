package com.hengmall.goods.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hengmall.goods.dao.CombineOrderDao;
import com.hengmall.goods.dao.CombineSaleDao;
import com.hengmall.goods.dao.SOrderDao;
import com.hengmall.goods.dao.SOrderExtraDao;
import com.hengmall.goods.model.CombineSaleEntity;
import com.hengmall.goods.model.SOrderEntity;
import com.hengmall.goods.model.api.CombineDetailsResp;
import com.hengmall.goods.model.api.ResultState;
import com.hengmall.goods.service.UserHandleService;
import com.hengmall.goods.util.PublicUtil;
import com.hengmall.goods.util.XmlUtil;


/**
 * 微信支付结果通知(统一下单参数的notify_url)
 * 微信充值成功回调 做业务逻辑的处理
 */
@Controller
public class WechatPayNotifyController {

    Logger logger = LoggerFactory.getLogger(WechatPayNotifyController.class);

    @Autowired
    SOrderDao sOrderDao;
    @Autowired
    SOrderExtraDao sOrderExtraDao;
    @Autowired
    CombineSaleDao combineSaleDao;
    @Autowired
    CombineOrderDao combineOrderDao;
    @Autowired
    UserHandleService userHandleService;

    /**
     * 支付成功后的回调函数
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("notify")
    public ResultState notify(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());//设置日期格式

        ResultState resultState = new ResultState();

        String resXml = "";

        logger.info("开始处理支付返回的请求：\n");


        BufferedReader reader = req.getReader();

        String line = "";
        String xmlString = null;
        StringBuffer inputString = new StringBuffer();

        while ((line = reader.readLine()) != null) {
            inputString.append(line);
        }
        xmlString = inputString.toString();
        req.getReader().close();

        logger.debug("----接收到的数据如下：---" + xmlString);


        Map<String, String> map = new HashMap<String, String>();
        String result_code = "";
        String return_code = "";
        String out_trade_no = "";
        String transaction_id = "";

        //将接受到的xml数据转换为map形式
        map = XmlUtil.xmlToMap(xmlString);
        logger.debug("数据转为map形式：{}", map);


        result_code = map.get("result_code");
        out_trade_no = map.get("out_trade_no");     //获取微信支付返回的订单号
        return_code = map.get("return_code");
        transaction_id = map.get("transaction_id");


        int num = 0;
        int user_id = 0;
        int money = 0;
        int products_id = 0;

        if ("SUCCESS".equals(result_code)) {

            logger.info("用户：{} 成功充值：{}", user_id, money);

            //根据 out_trade_no 订单号查询数据
            if(out_trade_no.contains("+")){
            //在订单列表重新进行再次支付起调拆分订单，将只改变部分订单的支付状态以及。目前这种情况基本出现在单独购买上
                String[] arr = out_trade_no.split("+");
                String outTradeNo = arr[0];
                String payNo = arr[1];
                if(PublicUtil.isEmpty(out_trade_no) || PublicUtil.isEmpty(payNo)){
                	 logger.info("订单号错误："+out_trade_no);
                }
                SOrderEntity orderInfo = sOrderDao.findByOutTradeNo(outTradeNo);
                int order_id = orderInfo.getId();
                //修改支付成功状态，将收货状态改为待发货
                sOrderDao.changePayStatus(1, out_trade_no);
                sOrderExtraDao.updateStatusByOutTradeNo(1, order_id,payNo);
            }else{
            // 正常的一次性下单购买成功流程
            SOrderEntity orderInfo = sOrderDao.findByOutTradeNo(out_trade_no);

            logger.info("orderInfo: {}", orderInfo.toString());

            int buy_status = orderInfo.getBuy_status();
            user_id = orderInfo.getUser_id();

            money = orderInfo.getPrice();
            int order_id = orderInfo.getId();
            
            if (buy_status == 3) {  //钱包充值

                //增加/减少 钱包总可用值及记录新增
                userHandleService.changeUserMoney(user_id, money, 1);
                //修改支付成功状态，将收货状态改为已收货
                sOrderDao.changePayStatus(1, out_trade_no);
                sOrderExtraDao.updateStatus(1, order_id,1,0);
                logger.info("user_id（{}）钱包充值（{}）充值成功", user_id, money);

            } else if (buy_status == 1) {   //单独购买

                //修改支付成功状态，将收货状态改为待发货
                sOrderDao.changePayStatus(1, out_trade_no);
                sOrderExtraDao.updateStatus(1, order_id,1,0);
            } else if (buy_status == 4) {   //秒杀
            	//抢购商品
                //修改支付成功状态，将收货状态改为待发货
                sOrderDao.changePayStatus(1, out_trade_no);
                sOrderExtraDao.updateStatus(1, order_id,1,0);
                logger.info("buyStatus: {}", buy_status);
            } else if (buy_status == 2) {
                logger.info("拼单购买 ... ");
                //TODO 此处拼单生成的订单记录还有待构建业务方案
                SOrderEntity sOrderEntity =  sOrderDao.findByOutTradeNo(out_trade_no);
                int initiator_id = sOrderEntity.getInitiator_id();
                int combine_sale_id = sOrderEntity.getCombine_sale_id();
                
                CombineSaleEntity combineSale = combineSaleDao.findById(combine_sale_id);
                // 拼单创建人与下单人是否一致，如果是通一个人就是发起拼单，否则就是参与拼单
                if(initiator_id == user_id ){
                	//发起拼单
                	int still_need = combineSale.getCombine_num() -1 ;
                	combineOrderDao.insert(combine_sale_id, user_id, out_trade_no, still_need, initiator_id, 1,1);
                }else{
                	//参与拼单
                	List<CombineDetailsResp> list = combineOrderDao.queryByFlashSaleId(combine_sale_id, user_id);
                	int still_need = combineSale.getCombine_num() - list.size() -1;
                	logger.info("拼单购买剩余还需人数 ... "+still_need);
                	if(still_need  < 0 ) throw new Exception("该拼团名额已满，请换一组拼团");
                	if(still_need  == 0 ) {//拼单完成，启动拼单成功条件，更改拼单订单状态
                    	combineOrderDao.insert(combine_sale_id, user_id, out_trade_no, still_need, initiator_id, 0,3);
                    	//还需要修改拼单主还需拼单人数
                    	combineOrderDao.update(combine_sale_id, user_id, out_trade_no, still_need, initiator_id,3);
                    	combineOrderDao.updateComplete(combine_sale_id, user_id, out_trade_no, still_need, initiator_id, 3);
                    	//  还需要修改订单状态
                    	for(CombineDetailsResp combineDetails : list){
                    		String outTradeNo = combineDetails.getOut_trade_no();
                    		sOrderExtraDao.updateCompleteStatus(3, outTradeNo);
                    	}
                    	
                	}else{
                	combineOrderDao.insert(combine_sale_id, user_id, out_trade_no, still_need, initiator_id, 0,1);
                	//还需要修改拼单主还需拼单人数
                	combineOrderDao.update(combine_sale_id, user_id, out_trade_no, still_need, initiator_id,1);
                	}
                }
            }
            }
            logger.info("充值回调成功！");

            resultState.setErrcode(0);// 表示成功,可以不写，int默认是0
            resultState.setErrmsg("success");

            // 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了
            resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
        } else {
            resultState.setErrcode(-1);// 支付失败
            resultState.setErrmsg(map.get("err_code_des"));
            logger.info("支付失败,错误信息：" + map.get("err_code_des"));
            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[" + map.get("err_code_des") + "]]></return_msg>" + "</xml> ";
        }


        BufferedOutputStream out = new BufferedOutputStream(resp.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();

        return resultState;
    }
}