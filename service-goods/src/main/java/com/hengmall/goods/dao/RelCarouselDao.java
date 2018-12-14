package com.hengmall.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.hengmall.goods.model.constitute.Carousel;

/**
 * Created by Administrator on 2018/5/24.
 */
@Repository
public interface RelCarouselDao {

    //根据Type查询
    @Select("select product_id productId,path from rel_carousel where s_category_id = #{category_id}")
    List<Carousel> queryByType(@Param("category_id") int category_id);
  /*  @Select("select a.product_id productId,b.path from rel_carousel a,s_resources b where a.product_id=b.id " +
            "and a.type = #{type}")
    List<Carousel> queryByType(@Param("type") int type);
*/
}
