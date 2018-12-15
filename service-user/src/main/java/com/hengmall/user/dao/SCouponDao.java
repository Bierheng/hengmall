package com.hengmall.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.hengmall.user.model.SCouponBean;
import com.hengmall.user.model.SCouponEntity;

/**
 * wuhengbin on 2018/7/2.
 */
@Repository
public interface SCouponDao {
	
	/**
	 * @Title: 查询优惠券列表
	 * @Description: 
	 */
	@Select({ "<script>", "select id,title,prerequisite,price,start_time,end_time,created_time,img from s_coupon",
			"order by created_time desc limit #{page},#{pagesize}", "</script>" })
	List<SCouponEntity> couponList(Map<String, Object> params);
	
	/**
	 *  新增优惠券
	 * @param SCouponEntity  优惠券实体类	
	 * @param 
	 */
	@Insert("insert into s_coupon(title,prerequisite,price,start_time,end_time,created_time,img)"
			+ "values(#{sCoupon.title},#{sCoupon.prerequisite},#{sCoupon.price},#{sCoupon.start_time},#{sCoupon.end_time},now(),#{sCoupon.img})")
	int insert(@Param("sCoupon") SCouponBean sCoupon);
	
	// 根据主键id删除数据
	@Delete("delete from s_coupon where id = #{id}")
	int delById(@Param("id") int id);
       
	// 根据主键id修改数据
	@Update("update s_coupon set title=#{edit.title},prerequisite = #{edit.prerequisite},price =#{edit.price},start_time =#{edit.start_time},"
			+ "end_time =#{edit.end_time} ,img =#{edit.img}"+"where id = #{edit.id}")
	void updateById(@Param("edit") SCouponBean sCoupon);

	/**
	 * 获取优惠券记录总数
	 * @return
	 */
	@Select("select count(*) from s_coupon")
	int countCoupon();

}
