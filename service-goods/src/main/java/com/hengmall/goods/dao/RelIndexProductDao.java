package com.hengmall.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.hengmall.goods.model.constitute.RelIndexProduct;

/**
 * Created by Administrator on 2018/5/24.
 */
@Repository
public interface RelIndexProductDao {

    //查询
    @Select("select a.product_id,b.headimg,b.name from rel_index_product a,s_product b where a.product_id=b.id limit 20")
    List<RelIndexProduct> select();

}
