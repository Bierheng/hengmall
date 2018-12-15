package com.hengmall.user.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2018/5/24.
 */
@Repository
public interface PUserDao {

	// 设置默认收货地址
	@Update("update ${platform}.p_user set addressid = #{address_id} where id = #{id}")
	void updateAddressId(@Param("id") int id, @Param("address_id") int address_id);

	@Select("select IFNULL(name, '')name from ${platform}.p_user where id = #{id} limit 1")
	String queryNameById(@Param("id") int user_id);
}
