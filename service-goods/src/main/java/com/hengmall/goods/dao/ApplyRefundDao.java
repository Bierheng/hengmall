package com.hengmall.goods.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.hengmall.goods.model.ApplyRefundEntity;

@Repository
@Mapper
public interface ApplyRefundDao {

    //数据新增
    @Insert("insert into apply_refund(user_id,order_id,name,phone,refund_reasons,created_time,shops_id,status)" +
            "values(#{user_id},#{order_id},#{name},#{phone},#{refund_reasons},now(),#{shops_id},#{status})")
    void insert(@Param("user_id") int user_id, @Param("order_id") int order_id,
                @Param("name") String name, @Param("phone") String phone,
                @Param("refund_reasons") String refund_reasons,
                @Param("shops_id") int  shops_id,@Param("status") int  status);


    //根据订单号查询是否有对应的申请退款记录
    @Select("select * from apply_refund where order_id=#{order_id} and shops_id =#{shops_id}  limit 1")
    ApplyRefundEntity findByOutTradeNo(@Param("order_id") int  order_id,@Param("shops_id") int shops_id);

}
