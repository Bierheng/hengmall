package com.hengmall.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.hengmall.goods.model.SAppraiseEntity;

/**
 * Created by Administrator on 2018/5/24.
 */
@Repository
@Mapper
public interface SAppraiseDao {

    //根据商品id查询评价信息
    @Select("select a.productid,a.url,a.attr,b.nickname,a.context,a.`like`,a.resources,a.ctime from s_appraise a,users b " +
            "where a.productid = #{id} and a.userid = b.id")
    List<SAppraiseEntity> queryByproductId(@Param("id") int id);

    //根据商品id查询评价信息
    @Select("select a.productid,a.url,a.attr,b.nickname,a.context,a.`like`,a.resources,a.ctime from s_appraise a,users b " +
            "where a.userid = #{id} and a.userid = b.id")
    List<SAppraiseEntity> queryByUserId(@Param("id") int id);

    //商品评价新增
    @Insert("insert into s_appraise(productid,userid,context,matching,deliveryspeed,score,servicequality," +
            "serviceattitude,resources,ctime,url,attr)" +
            "values(#{productid},#{userid},#{context},#{matching},#{deliveryspeed},#{score},#{servicequality}," +
            "#{serviceattitude},#{resources},now(),#{url},#{attr})")
    void insert(@Param("productid") int id, @Param("userid") int user_id,
                @Param("context") String context, @Param("matching") int matching,
                @Param("deliveryspeed") int deliveryspeed, @Param("score") int score,
                @Param("servicequality")int servicequality,@Param("serviceattitude")int serviceattitude,
                @Param("resources")String resources,
                @Param("url") String url,
                @Param("attr")String attr);

    //商品评价新增
    @Insert("insert into s_appraise(productid,userid,context,matching,deliveryspeed,score,servicequality," +
            "serviceattitude,ctime,url,attr)" +
            "values(#{productid},#{userid},#{context},#{matching},#{deliveryspeed},#{score}," +
            "#{servicequality},#{serviceattitude},now(),#{url},#{attr})")
    void insertExceptResource(@Param("productid") int id,@Param("userid") int user_id,
                @Param("context") String context,@Param("matching") int matching,
                @Param("deliveryspeed") int deliveryspeed,@Param("score") int score,
                @Param("servicequality")int servicequality,@Param("serviceattitude")int serviceattitude,
                @Param("url") String url,
                @Param("attr")String attr);
}
