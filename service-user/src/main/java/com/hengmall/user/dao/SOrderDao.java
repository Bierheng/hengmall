package com.hengmall.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hengmall.user.model.CombineDetailsResp;
import com.hengmall.user.model.CombineSaleEntity;
import com.hengmall.user.model.DataTablesResultShops;
import com.hengmall.user.model.SOrderEntity;
import com.hengmall.user.model.SOrderExtraEntity;

@Mapper
public interface SOrderDao {

	/**
	 * @Title: 通过用户查询他所有的订单
	 * @Description: TODO
	 */
	  @Select(
	  "select * from s_order where userid = #{user_id} and `status` = #{status} "
			  + "limit #{offset},#{limit}") 
	  List<SOrderEntity> queryByUserId(@Param("user_id") int user_id, @Param("status") int status,
	  @Param("offset") int offset, @Param("limit") int limit);
	 
	/**
	 * @Title: 查询订单列表
	 * @Description: TODO
	 */
	@Select({ "<script>", "select * from s_order "
			+ "<where>"
				+ "<if test='id != null and id != \"\"'>"
					+ "id = #{orderId}"
				+ "</if>"
				+ "and status >= 0 "	
			+ "</where>",
			"order by created_time desc limit #{page} , #{pagesize}", "</script>" })
	List<SOrderEntity> orderList(Map<String, Object> params);
	
	
	@Select("SELECT "
			+ "s.id AS 'id',"
			+ "s.shops_name AS 'shopsName'"
			+ " FROM "
				+ "s_order_extra a"
			+ " LEFT JOIN shops_location s ON a.shops_id = s.id"
			+ " WHERE "
				+ "a.order_id = #{orderid}"
				+ " GROUP BY a.shops_id")
	List<DataTablesResultShops> shopsList(@Param("orderid") int orderid);
	
	
	/**
	 * @Title: 根据订单ID查询订单子表列表
	 * @Description: TODO
	 */
	@Select({ "<script>", " select id,order_id,product_id,product_name,attrs,price ,num,`status`,appraise_status,"
			+ "shops_id AS 'shopsId',"
			+ "tracking_num AS 'tracking_num',"
			+ "express AS 'express',"
			+ "pay_no AS 'payNo' , "
			+ "combine_status AS 'combine_status' "
			+ " from s_order_extra where order_id = #{orderid} "
			+ "order by id desc", "</script>" })
	List<SOrderExtraEntity> orderExtraList(@Param("orderid") int orderid);
	
	/**
	 *  修改订单子表的状态
	 * @param id  订单子表的ID	
	 * @param status 订单子表的状态
	 */
	@Update("update s_order_extra set `status` = #{status} where id = #{id}")
	void updateOrderExtra(@Param("status") int status ,@Param("id") int id);
	
	/**
	 *  修改同一个主订单下订单子表的状态
	 * @param id  订单子表的ID	
	 * @param status 订单子表的状态
	 */
	@Update("update s_order_extra set `status` = #{status},tracking_num = #{tracking_num},express = #{express} where order_id = #{id} and shops_id = #{shops_id}")
	void updateOrderExtraAll(@Param("status") int status ,@Param("id") int id,@Param("shops_id") int shops_id,
							 @Param("tracking_num") String tracking_num,@Param("express") String express);
	
	/**
	 *  修改订单的状态
	 * @param id  订单的ID	
	 * @param status 订单的状态
	 */
	@Update("update s_order set delivery_status = #{status} where id = #{id}")
	void updateOrder(@Param("status") int status ,@Param("id") int id);
	
	/**
	 * 通过子订单ID拿到住订单ID
	 * @return
	 */
	@Select("select id,order_id,product_id,product_name,attrs,price ,num,`status`,appraise_status,"
			+ "shops_id AS 'shopsId',"
			+ "tracking_num AS 'trackingNum',"
			+ "express AS 'express',"
			+ "pay_no AS 'payNo'"
			+ "from s_order_extra where id = #{id}")
	SOrderExtraEntity queryOrder(@Param("id") int id);
	
	/**
	 * 通过子订单ID拿到住订单ID
	 * @return
	 */
	@Select("select id,order_id,product_id,product_name,attrs,price ,num,`status`,appraise_status,"
			+ "shops_id AS 'shopsId',"
			+ "tracking_num AS 'trackingNum',"
			+ "express AS 'express',"
			+ "pay_no AS 'payNo'"
			+ "from s_order_extra where order_id = #{id} limit 1")
	SOrderExtraEntity queryOrderId(@Param("id") int id);
	
	/**
	 * 获取订单记录总数
	 * @return
	 */
	@Select("select count(*) from s_order")
	int countOrder();
	
    //根据主键查询数据
    @Select("select b.paymethod from s_order a left join s_order_extra b on a.id = b.order_id where a.out_trade_no = #{out_trade_no}")
    SOrderExtraEntity queryPaymethodByNo(@Param("out_trade_no") String out_trade_no);
    
    //根据主键查询数据
    @Select("select a.out_trade_no as out_trade_no,b.express ,b.tracking_num, b.paymethod as paymethod,b.order_id AS orderid,b.shops_id as shops_id,a.user_id as user_id,b.price as price from s_order a left join s_order_extra b on a.id = b.order_id where b.shops_id = #{shops_id} and b.order_id = #{order_id}")
    SOrderExtraEntity queryPaymethodById(@Param("shops_id") int shops_id,@Param("order_id") int order_id);
    
    //修改
    @Update("update combine_order set status = #{status} " +
            " where out_trade_no = #{out_trade_no}")
    void updateCombineOrder(@Param("out_trade_no") String out_trade_no, @Param("status") int status);

	    //根据订单ID查询数据
    @Select("select * from s_order where out_trade_no = #{out_trade_no} limit 1")
    SOrderEntity findByOutTradeNo(@Param("out_trade_no") String out_trade_no);

    //根据主键查询数据
    @Select("select * from combine_sale where id = #{id} limit 1")
    CombineSaleEntity findById(@Param("id") int id);

    //根据订单号查询拼单人数列表
    @Select("select b.avatar_url headImg,a.start_time startTime,a.end_time endTime ,a.initiator_id as initiatorId,a.out_trade_no as out_trade_no  from combine_order a,users b where " +
            "a.user_id=b.id and a.combine_sale_id=#{combine_sale_id} and a.initiator_id=#{user_id}")
    List<CombineDetailsResp> queryByFlashSaleId(@Param("combine_sale_id") int combine_sale_id,
                                                @Param("user_id") int user_id);
    
    //修改
    @Update("update combine_order set still_need = #{still_need} " +
            " where combine_sale_id = #{combine_sale_id} and user_id = #{initiator_id}  and  initiator_id = #{initiator_id} and initiator = 1 and status = #{status}")
    void update(@Param("combine_sale_id") int combine_sale_id,@Param("user_id") int user_id,@Param("out_trade_no") String out_trade_no,
    		    @Param("still_need") int still_need,@Param("initiator_id") int initiator_id,@Param("status") int status);
    
    //修改
    @Update("update combine_order set status = #{status} " +
            " where combine_sale_id = #{combine_sale_id} and user_id = #{user_id}  and  initiator_id = #{initiator_id} and out_trade_no = #{out_trade_no} and status = #{status}")
    void updateStatus(@Param("combine_sale_id") int combine_sale_id,@Param("user_id") int user_id,@Param("out_trade_no") String out_trade_no,
    		    	  @Param("still_need") int still_need,@Param("initiator_id") int initiator_id,@Param("status") int status);
    
    //修改
    @Update("update combine_order set still_need = #{still_need} " +
            " where combine_sale_id = #{combine_sale_id} and user_id = #{initiator_id}  and  initiator_id = #{initiator_id} and initiator = 0 and status = #{status}")
    void updateComplete(@Param("combine_sale_id") int combine_sale_id,@Param("user_id") int user_id,@Param("out_trade_no") String out_trade_no,
    		    @Param("still_need") int still_need,@Param("initiator_id") int initiator_id,@Param("status") int status);
    
    //修改
    @Update("update combine_order set initiator_id = user_id , status = #{status} ,initiator = 1 ,still_need = #{still_need} " +
            " where out_trade_no = #{out_trade_no}")
    void updateInitiator(@Param("out_trade_no") String out_trade_no,@Param("still_need") int still_need,@Param("status") int status);
    
}
