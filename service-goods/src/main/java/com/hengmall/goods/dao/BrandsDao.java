package com.hengmall.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.hengmall.goods.model.BrandsEntity;
import com.hengmall.goods.model.constitute.Brands;

@Repository
public interface BrandsDao {

    //根据状态码查询
    @Select("select * from brands")
    List<BrandsEntity> select();


    //根据状态码查询
    @Select("select * from brands")
    List<Brands> selects();

}
