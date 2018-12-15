package com.hengmall.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.hengmall.user.model.PAddressEntity;
import com.hengmall.user.model.api.EditAddressReq;
import com.hengmall.user.model.constitute.AddressList;

/**
 * Created by Administrator on 2018/5/24.
 */
@Repository
public interface PAddressDao {

	// 根据id查询
	@Select("select * from ${platform}.p_address where id = #{id}")
	PAddressEntity queryById(@Param("id") int id);

	// 新增
	@Insert("insert into ${platform}.p_address(user_id,uname,phone,zip_code,province,city,area,street,adress,ctime)"
			+ "values(#{user_id},#{uname},#{phone},#{zip_code},#{province},#{city},#{area},#{street},#{adress},now())")
	void insert(@Param("user_id") int user_id, @Param("uname") String uname, @Param("phone") String phone,
			@Param("zip_code") int zip_code, @Param("province") String province, @Param("city") String city,
			@Param("area") String area, @Param("street") String street, @Param("adress") String adress);

	// 根据用户id查询收货地址
	@Select("select * from ${platform}.p_address where id = (select addressid from ${platform}.p_user where id = #{id}) "
			+ "UNION all " + "select * from ${platform}.p_address where user_id = #{id} and id != "
			+ "(select addressid from ${platform}.p_user where id = #{id})")
	List<AddressList> getAddressList(@Param("id") int id);

	// 根据主键id删除数据
	@Delete("delete from ${platform}.p_address where id = #{id}")
	void delById(@Param("id") int id);
       
	// 根据主键id修改数据
	@Update("update ${platform}.p_address set uname=#{edit.uname},phone = #{edit.phone},"
			+ "zip_code =#{edit.zip_code} ,province=#{edit.province},"
			+ "city = #{edit.city},area=#{edit.area},adress=#{edit.address} " + "where id = #{edit.id}")
	void updateById(@Param("edit") EditAddressReq editAddress);

}
