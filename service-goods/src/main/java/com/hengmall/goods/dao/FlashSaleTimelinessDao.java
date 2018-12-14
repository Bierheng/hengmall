package com.hengmall.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.hengmall.goods.model.FlashSaleTimelinessEntity;

@Repository
public interface FlashSaleTimelinessDao {

    //根据类型查询
    @Select("select id,`desc`,unix_timestamp(start_time)*1000 startTime,unix_timestamp(end_time)*1000 endTime " +
            "from flash_sale_timeliness")
    List<FlashSaleTimelinessEntity> query();

}
