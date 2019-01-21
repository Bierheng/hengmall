package com.hengmall.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.hengmall.goods.model.FlashSaleEntity;
import com.hengmall.goods.model.constitute.FlashSale;

@Repository
@Mapper
public interface FlashSaleDao {

    //根据状态码查询
    @Select("select * from flash_sale")
    List<FlashSaleEntity> select();


    //根据主键查询数据
    @Select("select * from flash_sale where id = #{id} limit 1")
    FlashSaleEntity findById(@Param("id") int id);


    //根据状态码查询限时抢购商品 // DATE_FORMAT(end_time,'%Y-%c-%d %h:%i')endTime
    @Select("select product_id productId,title,price,icon,status,unix_timestamp(end_time)endTime," +
            "unix_timestamp(start_time)startTime from flash_sale")
    List<FlashSale> query();


    //修改有效状态
    @Update("update flash_sale set status = 0,updated_time = now() where status =1 " +
            "and unix_timestamp(now())>unix_timestamp(end_time)")
    void changeStatus();


    //根据限时表（flash_sale_timeliness）主键ID查询关联数据
    @Select("select a.product_id productId,a.id flashSaleId,a.title,a.price/100 price,a.icon," +
            "a.`status`,a.stock,b.price/100 originalPrice from flash_sale a,s_product b,tb_shops_product c " +
            "where a.product_id=c.id and b.id = c.product_id and timeliness_id=#{id}")
    List<FlashSale> findByTimelinessId(@Param("id") int id);


    //查询用户所有已经秒杀过的商品
    @Select("select * from flash_sale where id not in" +
            "(select flash_sale_id from success_killed where user_id = #{user_id})")
    List<FlashSale> findNotKillProduct(@Param("user_id") int user_id);


}
