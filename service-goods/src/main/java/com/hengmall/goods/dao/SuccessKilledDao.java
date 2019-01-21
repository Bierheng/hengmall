package com.hengmall.goods.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.hengmall.goods.model.SuccessKilledEntity;

@Repository
@Mapper
public interface SuccessKilledDao {

    //查询该用户是否已经秒杀过该件商品
    @Select("select id from success_killed where user_id=#{user_id} and " +
            "flash_sale_id = #{flash_sale_id} limit 1")
    SuccessKilledEntity findByFlashSaleId(@Param("user_id")int user_id , @Param("flash_sale_id") int flash_sale_id);

}
