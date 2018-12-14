package com.hengmall.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.hengmall.goods.model.SOrderEntity;
import com.hengmall.goods.model.constitute.OrderDetails;


@Repository
public interface SOrderDao {

    //订单新增
    @Insert("insert into s_order(out_trade_no,user_id,s_coupon_id,price,paymethod,buy_status," +
            "address_id,created_time,updated_time)" +
            "values(#{out_trade_no},#{user_id},#{coupon_id},#{price},#{paymethod}," +
            "#{buy_status},#{address_id},now(),now())")
    @Options(useGeneratedKeys = true, keyProperty = "order.id")
    void insertToOrder(@Param("out_trade_no") String out_trade_no, @Param("user_id") int user_id,
                       @Param("coupon_id") int coupon_id, @Param("price") int price,
                       @Param("paymethod") int paymethod, @Param("buy_status") int buy_status,
                       @Param("address_id") int address_id, @Param("order") SOrderEntity orderEntity);
    
    //订单新增
    @Insert("insert into s_order(out_trade_no,user_id,s_coupon_id,price,paymethod,buy_status," +
            "address_id,initiator_id,combine_sale_id,created_time,updated_time)" +
            "values(#{out_trade_no},#{user_id},#{coupon_id},#{price},#{paymethod}," +
            "#{buy_status},#{address_id},#{initiatorId},#{combine_sale_id},now(),now())")
    @Options(useGeneratedKeys = true, keyProperty = "order.id")
    void insertToOrderCombine(@Param("out_trade_no") String out_trade_no, @Param("user_id") int user_id,
		                      @Param("coupon_id") int coupon_id, @Param("price") int price,
		                      @Param("paymethod") int paymethod, @Param("buy_status") int buy_status,
		                      @Param("address_id") int address_id,@Param("initiatorId") int initiatorId,
		                      @Param("combine_sale_id") int combine_sale_id,@Param("order") SOrderEntity orderEntity);

    //订单新增
    @Insert("insert into tb_shops_order(order_id,shops_id,created_time)" +
            "values(#{order_id},#{shops_id},now())")
    void insertToShopsOrder(@Param("order_id") int order_id,
                            @Param("shops_id") int shops_id);
    
    //根据订单ID查询 订单是否已完成
    @Select("select count(*) from s_order where id = #{id} and status = 1 limit 1")
    int queryOrderIsFinished(@Param("id") int id);


    //隐藏订单
    @Update("update s_order set hide_status = 1,updated_time=now() where id = #{id}")
    void changeHideStatus(@Param("id") int id);


    //支付成功修改订单支付状态
    @Update("update s_order set status=#{status},updated_time=now(),pay_time = now()  " +
            "where out_trade_no=#{out_trade_no}")
    void changePayStatus(@Param("status") int status,@Param("out_trade_no") String out_trade_no);
    
    //退款成功修改订单支付状态
    @Update("update s_order set status=#{status},updated_time=now(),refund_time = now()  " +
            "where out_trade_no=#{out_trade_no}")
    void changeRefundStatus(@Param("status") int status,@Param("out_trade_no") String out_trade_no);


    //根据订单ID查询数据
    @Select("select * from s_order where out_trade_no = #{out_trade_no} limit 1")
    SOrderEntity findByOutTradeNo(@Param("out_trade_no") String out_trade_no);


    //根据订单ID修改实际支付金额
    @Update("update s_order set price = #{price},updated_time=now() where id = #{id}")
    void updatePriceById(@Param("price") int price, @Param("id") int id);


    //取消订单（将status状态改为1）
    @Update("update s_order set status=#{status},updated_time=now() where id=#{id}")
    void changeOrderStatusById(@Param("status") int status, @Param("id") int id);


    //根据主键ID查询数据
    @Select("select * from s_order where id = #{id} and status=1 limit 1")
    SOrderEntity queryById(@Param("id") int id);
    
    //根据主键ID查询数据
    @Select("select distinct shops_id from s_order_extra where order_id = #{order_id} and status=1 ")
    List<Integer> queryShopsById(@Param("order_id") int order_id);


    //根据主键ID修改status、delivery_status的值
    @Update("update s_order_extra set status=#{status},updated_time=now() " +
            "where order_id=#{id} and shops_id = #{shops_id}")
    void changeStatusById(@Param("status") int status,@Param("shops_id") int shops_id,@Param("id") int id);


    //用户下单后未支付，订单默认10分钟有效（10分钟后将自动取消订单）60*10：10分钟
    @Update("update s_order set `status`=-1,updated_time=now() where `status`=0 and buy_status!=4 and " +
            "UNIX_TIMESTAMP(now())>=UNIX_TIMESTAMP(created_time)+60*10")
    void autoCancelOrder();


    //根据支付状态码查询
    @Select("select * from s_order where `status`=#{status} and buy_status=#{buy_status}")
    List<SOrderEntity> queryByStatus(@Param("status") int status, @Param("buy_status") int buy_status);


    //根据订单号查询订单详情
    @Select("select a.product_name,a.product_id,a.attrs,a.price,a.num,a.created_time purchase_time,b.`status` buy_status, b.pay_time  pay_time,a.pay_no pay_no, " +
            "c.uname,c.phone,c.province,c.city,c.area,c.address,a.shops_id shops_id,d.headimg as headimg from s_order_extra a,s_order b,p_address c ,s_product d, tb_shops_product e  " +
            "where b.out_trade_no=#{out_trade_no} and a.shops_id = #{shops_id} and a.order_id = b.id and  b.address_id = c.id and a.product_id = e.id and d.id = e.product_id")
    List<OrderDetails> queryOrderDetail(@Param("out_trade_no") String out_trade_no,@Param("shops_id") int shops_id);
    
    //根据订单号查询订单详情
    @Select("select a.product_name,a.product_id,a.attrs,a.price,a.num,a.created_time purchase_time,b.`status` buy_status, b.pay_time  pay_time, a.pay_no pay_no, " +
            "c.uname,c.phone,c.province,c.city,c.area,c.address,a.shops_id shops_id,d.headimg from s_order_extra a,s_order b,p_address c ,s_product d ,tb_shops_product e  " +
            "where b.out_trade_no=#{out_trade_no} and a.order_id = b.id and b.address_id = c.id and a.product_id = e.id and d.id = e.product_id and a.shops_id = #{shops_id}")
    List<OrderDetails> queryOrderDetailbyshops(@Param("out_trade_no") String out_trade_no,@Param("shops_id") int shops_id);
    
    //根据订单号查询订单详情
    /*@Select("select * from s_order_extra where id=(select id from s_order where out_trade_no=#{out_trade_no} limit 1)")
    List<SOrderEntity> queryOrderDetail(@Param("out_trade_no") String out_trade_no);*/


    //根据ID修改状态码
    /*@Update("update s_order set `status`=-1 where id=#{}")
    void dfd();*/

    //店铺会员的新增
    @Insert("insert into tb_shops_member(user_id,shops_id,created_time,status)" +
            "values(#{user_id},#{shops_id},now(),0)")
    void insertToShopsMember(@Param("user_id") int user_id,
                            @Param("shops_id") int shops_id);
    
    //判断店铺会员是否已经存在了
    @Select("select count(*) from tb_shops_member where user_id =#{user_id} and shops_id =#{shops_id}")
    int countShopsMember(@Param("user_id") int user_id,
                         @Param("shops_id") int shops_id);
    
    //判断店铺会员是否已经存在了
    @Select("select sum(price*num) from s_order_extra where pay_no =#{payNo}")
    int countOrderPrice(@Param("payNo") String payNo);
    
}
