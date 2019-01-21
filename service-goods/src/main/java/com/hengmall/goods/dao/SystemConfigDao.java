package com.hengmall.goods.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SystemConfigDao {

    //根据user_id 查询钱包明细
    @Select("select value from sys_config where code = 'rate' limit 1")
    int queryRateBycode();


}
