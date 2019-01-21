package com.hengmall.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.hengmall.goods.model.CombineOrderEntity;
import com.hengmall.goods.model.SOrderExtraEntity;
import com.hengmall.goods.model.api.CombineDetailsResp;

@Repository
@Mapper
public interface CombineOrderDao {

    //根据订单号查询拼单人数列表
    @Select("select b.avatar_url headImg,a.start_time startTime,a.end_time endTime ,a.initiator_id as initiatorId,a.out_trade_no as out_trade_no  from combine_order a,users b where " +
            "a.user_id=b.id and a.combine_sale_id=#{combine_sale_id} and a.initiator_id=#{user_id}")
    List<CombineDetailsResp> queryByFlashSaleId(@Param("combine_sale_id") int combine_sale_id,
                                                @Param("user_id") int user_id);
    
    //根据订单号查询拼单人数列表
    @Select("select count(*) from combine_order a,users b where " +
            "a.user_id=b.id and a.combine_sale_id=#{combine_sale_id} and a.initiator_id=#{user_id} and user_id = #{user_id} and status = 1")
    int countByFlashSaleId(@Param("combine_sale_id") int combine_sale_id,@Param("user_id") int user_id);

    //新增
    @Insert("insert into combine_order(combine_sale_id,user_id,out_trade_no,still_need,initiator_id,initiator,start_time,end_time,created_time,`status`) " +
            " values(#{combine_sale_id},#{user_id},#{out_trade_no},#{still_need},#{initiator_id},#{initiator},now(),NOW() + INTERVAL 1 DAY ,now(),#{status})")
    void insert(@Param("combine_sale_id") int combine_sale_id,@Param("user_id") int user_id,@Param("out_trade_no") String out_trade_no,
    		    @Param("still_need") int still_need,@Param("initiator_id") int initiator_id, @Param("initiator") int initiator,@Param("status") int status );
    
    //修改
    @Update("update combine_order set still_need = #{still_need} " +
            " where combine_sale_id = #{combine_sale_id} and user_id = #{initiator_id}  and  initiator_id = #{initiator_id} and initiator = 1 and status = #{status}")
    void update(@Param("combine_sale_id") int combine_sale_id,@Param("user_id") int user_id,@Param("out_trade_no") String out_trade_no,
    		    @Param("still_need") int still_need,@Param("initiator_id") int initiator_id,@Param("status") int status);
    
    //修改
    @Update("update combine_order set still_need = #{still_need} " +
            " where combine_sale_id = #{combine_sale_id} and user_id = #{initiator_id}  and  initiator_id = #{initiator_id} and initiator = 0 and status = #{status}")
    void updateComplete(@Param("combine_sale_id") int combine_sale_id,@Param("user_id") int user_id,@Param("out_trade_no") String out_trade_no,
    		    @Param("still_need") int still_need,@Param("initiator_id") int initiator_id,@Param("status") int status);
    
    //修改
    @Update("update combine_order set status = #{status} " +
            " where out_trade_no = #{out_trade_no}")
    void updateStatus(@Param("out_trade_no") String out_trade_no,@Param("status") int status);
    
    //根据主键查询数据
    @Select("select * from combine_order where end_time < now() and `status` = 1")
    List<CombineOrderEntity> autoOrderList();
    
    //根据主键查询数据
    @Select("select b.paymethod as paymethod,b.order_id AS orderid,b.shops_id as shops_id,a.user_id as user_id,b.price as price from s_order a left join s_order_extra b on a.id = b.order_id where a.out_trade_no = #{out_trade_no}")
    SOrderExtraEntity queryPaymethodByNo(@Param("out_trade_no") String out_trade_no);
    
    //根据主键查询数据
    @Select("select a.*,b.combine_num from combine_order a left join combine_sale b on a.combine_sale_id = b.id where a.out_trade_no = #{out_trade_no}")
    CombineOrderEntity queryCombineOrderByNo(@Param("out_trade_no") String out_trade_no);
    
    //根据主键查询数据
    @Select("select a.*,b.combine_num from combine_order a left join combine_sale b on a.combine_sale_id = b.id where a.combine_sale_id = #{combine_sale_id} and a.user_id = #{user_id} and a.initiator_id = #{user_id} and a.initiator = 1 and a.status = 1")
    CombineOrderEntity queryCombineOrderById(@Param("combine_sale_id") int  combine_sale_id ,@Param("user_id") int  user_id);
    
}
