package com.hengmall.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hengmall.user.model.ShopsLocation;
import com.hengmall.user.model.TbSupplier;

@Mapper
public interface OrderLocationDao {

	/**
	 * @Title: 查询店铺的定位信息列表
	 * @Description: TODO
	 */
	@Select({ "<script>", "select * from shops_location ",
			"order by created_time desc ", "</script>" })
	List<ShopsLocation> orderLocationList();
	
	/**
	 * @Title: 查询供应商的定位信息列表
	 * @Description: TODO
	 */
	@Select({ "<script>", "select * from tb_supplier",
			"order by created_time desc ", "</script>" })
	List<TbSupplier> tbSupplierList();
	
	/**
	 *  修改供应商的地理位置信息
	 * @param lat  纬度
	 * @param lng  经度
	 * @param userId
	 */
	@Update("update tb_supplier set lat = #{lat},lng = #{lng} where user_id = #{userId}")
	int updateSupplier(@Param("lat") String lat ,@Param("lng") String lng,@Param("userId") int userId);
	
	/**
	 *  新增店铺
	 * @param shops  店铺数据
	 */
	@Insert("insert into shops_location(user_id,address,lat,lng,created_time,shops_name)"
			+ "values(#{shops.user_id},#{shops.address},#{shops.lat},#{shops.lng},now(),#{shops.shops_name})")
	int insertShops(@Param("shops") ShopsLocation shops);
}
