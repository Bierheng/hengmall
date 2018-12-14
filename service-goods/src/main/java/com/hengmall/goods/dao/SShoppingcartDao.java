package com.hengmall.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.hengmall.goods.model.SShoppingcartEntity;


@Repository
public interface SShoppingcartDao {

    //新增
    @Insert("insert into s_shoppingcart(product_id,attr,sum,user_id,created_time,shops_id,sku_index,price)" +
            "values(#{product_id},#{attr},#{sum},#{user_id},now(),#{shops_id},#{sku_index},#{price})")
    void insert(@Param("product_id") int product_id, @Param("attr") String attr,
                @Param("sum") int sum, @Param("user_id") int user_id,@Param("shops_id") int shops_id,@Param("sku_index") int sku_index,@Param("price") int price);


    //根据userId 查询购物车列表
    @Select("select a.id,a.product_id product_id,a.attr attr,a.sum,b.name,b.headimg,a.price,a.shops_id from " +
            "s_shoppingcart a LEFT JOIN tb_shops_product c ON a.product_id = c.id LEFT JOIN s_product b ON b.id = c.product_id where a.user_id = #{user_id} and a.shops_id = #{shops_id}")
    List<SShoppingcartEntity> selectByUserId(@Param("user_id") int user_id,@Param("shops_id") int shops_id);
    
    //根据userId,shopsId 查询购物车店铺列表
    @Select("select distinct a.user_id,a.shops_id from " +
            "s_shoppingcart a where a.user_id = #{user_id}")
    List<SShoppingcartEntity> selectShopsByUserId(@Param("user_id") int user_id);
    
    //根据userId,shopsId,product_id 查询购物车中是否已经存在该商品
    @Select("select * from " +
            "s_shoppingcart a where a.user_id = #{user_id} and a.shops_id = #{shops_id} and a.product_id = #{product_id} and a.sku_index = #{sku_index}")
    SShoppingcartEntity queryProductByUserId(@Param("user_id") int user_id,@Param("shops_id") int shops_id,@Param("product_id") int product_id,@Param("sku_index") int sku_index);


    @Delete("delete from s_shoppingcart where id in(${id})")
    void delById(@Param("id") String id);

	// 根据主键id修改数据
	@Update("update s_shoppingcart set sum =#{edit.sum}"
			+ "  where id = #{edit.id}")
	int updateById(@Param("edit") SShoppingcartEntity edit);

}
