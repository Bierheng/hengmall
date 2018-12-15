package com.hengmall.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.hengmall.user.model.constitute.RelIndexProduct;

/**
 * Created by Administrator on 2018/5/24.
 */
@Repository
public interface RelIndexProductDao {

	// 查询
	@Select("select a.productid,b.headimg,b.name from rel_index_product a,s_product b where a.productid=b.id limit 20")
	List<RelIndexProduct> select();

}
