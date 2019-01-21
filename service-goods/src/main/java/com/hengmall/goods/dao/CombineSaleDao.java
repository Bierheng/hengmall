package com.hengmall.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.hengmall.goods.model.CombineSaleEntity;

@Repository
@Mapper
public interface CombineSaleDao {


    //根据主键查询数据
    @Select("select * from combine_sale where id = #{id} limit 1")
    CombineSaleEntity findById(@Param("id") int id);
    


    //查询特定的商品拼主的信息
    @Select("select b.id,a.combine_num,d.avatar_url head_img,d.nickname,c.combine_sale_id,c.user_id,c.still_need," +
            "c.out_trade_no,c.initiator,c.start_time startTime,c.end_time endTime from combine_sale a, tb_shops_product b, " +
            " combine_order c,users d where a.id = c.combine_sale_id and a.product_id = b.product_id  " +
            "and c.user_id=d.id and b.id=#{product_id}  and c.end_time > now()")
    List<CombineSaleEntity> queryByType(@Param("product_id") int product_id);


    //拼单人数减一
    @Update("update combine_sale set still_need=still_need-1 where id=#{id} and still_need>0")
    Integer stillNeedSubtract(@Param("id") int id);

    //根据状态码查询拼单商品列表
    @Select("select a.id,b.id as product_id,a.title,a.price/100 as price,a.icon,a.status,a.shops_id from combine_sale a left join tb_shops_product b on a.product_id = b.product_id and a.shops_id = b.shops_id where status = 2 ")
    List<CombineSaleEntity> query();
    
}
