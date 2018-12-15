package com.hengmall.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.hengmall.user.model.SAppraiseEntity;

/**
 * Created by Administrator on 2018/5/24.
 */
@Repository
public interface SAppraiseDao {

	// 根据商品id查询评价信息
	@Select("select * from s_appraise a,${platform}.p_user b where a.productid = #{id} and a.userid = b.id")
	List<SAppraiseEntity> queryByproductId(@Param("id") int id);

	/**
	 * @Title: 根据查询评论列表
	 * @Description: 
	 */
	@Select({ "<script>", " select id,productid,userid,context,ctime,`like`,resources,matching,deliveryspeed,score,servicequality,serviceattitude"
			+ " from s_appraise "
			,"order by ctime desc limit #{page} , #{pagesize}", "</script>" })
	List<SAppraiseEntity> queryAppraiseList(Map<String, Object> params);
	
	// 根据评论主键id删除数据
	@Delete("delete from s_appraise where id = #{id}")
	void delById(@Param("id") int id);
	
	/**
	 * 获取评论记录总数
	 * @return
	 */
	@Select("select count(*) from s_appraise")
	int countAppraise();
}
