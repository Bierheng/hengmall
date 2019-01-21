package com.hengmall.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.hengmall.goods.model.RelCouponEntity;
import com.hengmall.goods.model.constitute.Coupon;

/**
 * Created by Administrator on 2018/5/24.
 */
@Repository
@Mapper
public interface RelCouponDao {

    //根据主键ID删除数据
    @Delete("delete from rel_coupon where id in(${id})")
    void delByIds(@Param("id") String id);


    //优惠券领取
    @Insert("insert into rel_coupon(s_coupon_id,user_id,shops_id)values(#{id},#{user_id},#{shops_id})")
    void insert(@Param("id") int id, @Param("user_id") int user_id ,@Param("shops_id") int shops_id);


    //查询优惠券是否已经领取过
    @Select("select id from rel_coupon where s_coupon_id=#{id} and user_id=#{user_id}")
    Integer isExist(@Param("id") int id, @Param("user_id") int user_id);


    //根据user_id
    @Select("select * from rel_coupon where user_id = #{user_id}")
    List<RelCouponEntity> queryCoupon(@Param("user_id") int user_id);
    
    //根据user_id,订单金额判断是否
    @Select("SELECT b.id as id,b.img as img ,b.start_time as start_time,b.end_time as end_time, a.`status` as `status`, b.prerequisite*100 as prerequisite,b.title as title,b.price as price,b.shops_id as shops_id  FROM "
    		+ " rel_coupon a LEFT JOIN s_coupon b on a.s_coupon_id = b.id"
    		+ " WHERE a.user_id = #{user_id} and b.shops_id = #{shops_id} and a.status = 0 and b.prerequisite < #{price}/100")
    List<Coupon> queryCouponByMoney(@Param("user_id") int user_id,@Param("shops_id") int shops_id,@Param("price") int price);
    
    //查询优惠券是否已经领取过
    @Select("select sum(price*100) from s_coupon where id in ( ${id} )")
    Integer countCoupon(@Param("id") String id);
}
