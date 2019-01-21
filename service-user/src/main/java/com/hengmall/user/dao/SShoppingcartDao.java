package com.hengmall.user.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hengmall.user.model.api.AddCartReq;

@Mapper
public interface SShoppingcartDao {

	// 新增
	@Insert("insert into s_shoppingcart(productid,size,color,sum,userid,ctime)"
			+ "values(#{cart.productid},#{cart.size},#{cart.color},#{cart.sum},#{id},now())")
	void insert(@Param("id") int id, @Param("cart") AddCartReq addCart);

}
