package com.hengmall.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.hengmall.user.model.constitute.RelRecommend;

/**
 * Created by Administrator on 2018/5/24.
 */
@Repository
public interface RelRecommendDao {

	// 查询
	@Select("select a.productid,b.headimg,b.name,b.price from rel_recommend a,s_product b where a.productid=b.id limit 20")
	List<RelRecommend> select();

}
