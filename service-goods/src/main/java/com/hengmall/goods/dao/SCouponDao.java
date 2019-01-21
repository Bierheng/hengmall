package com.hengmall.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.hengmall.goods.model.constitute.Coupon;

/**
 * Created by Administrator on 2018/5/24.
 */
@Repository
@Mapper
public interface SCouponDao {

    //根据user_id查询
    @Select("select b.id,a.title,a.prerequisite,a.price,a.img,a.shops_id," +
            "DATE_FORMAT(a.start_time,'%Y-%c-%d %h:%i')start_time," +
            "DATE_FORMAT(a.end_time,'%Y-%c-%d %h:%i')end_time from s_coupon a,rel_coupon b where " +
            "a.id = b.s_coupon_id and b.user_id=#{user_id} and b.status = #{status}")
    List<Coupon> queryByType(@Param("user_id") int user_id, @Param("status") int status);


    //根据主键ID删除数据
    @Delete("delete from s_coupon where id in(${id})")
    void delByIds(@Param("id") String id);

    @Select("select * from s_coupon where id not in" +
            "(select s_coupon_id from rel_coupon where user_id=#{user_id})")
    List<Coupon> queryByUnclaimed(@Param("user_id")int user_id);

}
