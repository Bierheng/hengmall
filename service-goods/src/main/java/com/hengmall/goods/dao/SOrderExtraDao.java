package com.hengmall.goods.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.hengmall.goods.model.SOrderExtraEntity;


@Repository
public interface SOrderExtraDao {

    @Update("update s_order_extra set appraise_status=#{status} where order_id=#{order_id} and product_id=#{product_id} ")
    void updateAppraiseStatus(@Param("status") int status, @Param("order_id") int order_id,
                              @Param("product_id") int id);

    @Update("update s_order_extra set status=#{status},paymethod=#{paymethod},combine_status=#{combine_status}  where order_id=#{order_id}")
    void updateStatus(@Param("status") int status, @Param("order_id") int order_id,@Param("paymethod") int paymethod,@Param("combine_status") int combine_status);
    
    @Update("update s_order_extra set combine_status=#{combine_status} where order_id= ( select id from s_order where out_trade_no = #{out_trade_no} )")
    void updateCompleteStatus(@Param("combine_status") int combine_status, @Param("out_trade_no") String out_trade_no);
    
    @Update("update s_order_extra set status=#{status},pay_time = now() where order_id=#{order_id}")
    void updateRefundStatus(@Param("status") int status, @Param("order_id") int order_id);
    
    @Update("update s_order_extra set status=#{status}, appraise_status=#{appraise_status} where order_id=#{order_id} and shops_id =#{shops_id}")
    void updateStatusByShopsId(@Param("status") int status, @Param("order_id") int order_id,@Param("shops_id") int shops_id,@Param("appraise_status") int appraise_status );
    
    @Update("update s_order_extra set status=#{status} where order_id=#{order_id} and pay_no = #{outTradeNo}")
    void updateStatusByOutTradeNo(@Param("status") int status, @Param("order_id") int order_id,@Param("outTradeNo") String outTradeNo);

    //数据入库
    @Insert("insert into s_order_extra(order_id,product_id,sku_index,product_name,attrs,price,num,`status`,shops_id,created_time,pay_no)" +
            "values(#{order_id},#{product_id},#{sku_index},#{product_name},#{attrs},#{price},#{num},#{status},#{shops_id},now(),#{pay_no})")
    @Options(useGeneratedKeys = true, keyProperty = "orderExtra.id")
    void insertSOrderExtra(@Param("order_id") int order_id, @Param("product_id") int product_id,
                           @Param("sku_index") int sku_index,@Param("product_name") String product_name,
                           @Param("attrs") String attrs, @Param("price") int price,
                           @Param("num") int num, @Param("status") int status,@Param("shops_id") int shops_id,
                           @Param("pay_no") String pay_no,@Param("orderExtra") SOrderExtraEntity sOrderExtra);


    //多个商品下单，则有多个记录，但只会有一个单号，将多个商品与一个单号关联起来
    @Update("update s_order_extra set order_id=#{order_id} where id in(${ids})")
    void updateOrderId(@Param("order_id") int order_id,@Param("ids") String ids);
    
    //根据主键ID查询数据
    @Select("select * from s_order_extra where order_id = #{order_id} and product_id=#{product_id} limit 1")
    SOrderExtraEntity queryById(@Param("order_id") int order_id,@Param("product_id") int product_id);
    
    //根据主键ID查询数据
    @Select("select * from s_order_extra where order_id = #{order_id} and shops_id=#{shops_id} limit 1")
    SOrderExtraEntity queryByShopId(@Param("order_id") int order_id,@Param("shops_id") int shops_id);
    
}
