package com.server.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

import com.server.entity.UsersEntity;

/**
 * Created by Administrator on 2018/5/24.
 */
@Repository
public interface UsersDao {

    //根据token查询信息
    @Select("select * from users where token=#{token} limit 1")
    UsersEntity queryByToken(@Param("token") String token);

    //根据ID查询信息
    @Select("select * from users where id=#{id} limit 1")
    UsersEntity queryById(@Param("id") int id);


    //设置默认收货地址
    @Update("update users set address_id = #{address_id} where id = #{id}")
    void updateAddressId(@Param("id") int id, @Param("address_id") int address_id);

    @Select("select IFNULL(name, '')name from users where id = #{id} limit 1")
    String queryNameById(@Param("id") int user_id);


    //查询openid是否存在
    @Select("select exists (select id from users where openid = #{openid} limit 1)")
    int queryByOpenId(@Param("openid") String openid);


    //首次登录时将用户信息插入数据库
//    @Insert("insert into users(nickname,openid,avatar_url,gender,city,province,country,token,created_time)" +
//            "values(#{userInfo.nickName},#{openId},#{userInfo.avatarUrl},#{userInfo.gender},#{userInfo.city}," +
//            "#{userInfo.province},#{userInfo.country},#{token},now())")
//    @Options(useGeneratedKeys = true, keyProperty = "userInfo.nickName")
//    void insertToUsers(@Param("userInfo") UserInfo userInfo, @Param("openId") String openId, @Param("token") String token);
/*
@Insert("insert into users(nickname,openid,avatar_url,gender,city,province,country,token,created_time)" +
            "values(#{userInfo.nickName},#{openId},#{userInfo.avatarUrl},#{userInfo.gender},#{userInfo.city}," +
            "#{userInfo.province},#{userInfo.country},#{token},now())")
    @Options(useGeneratedKeys = true, keyProperty = "userInfo.nickName")
    void insertToUsers(@Param("userInfo") UserInfo userInfo, @Param("openId") String openId, @Param("token") String token);
*/


    //登录时修改token
    @Update("update users set token=#{token},updated_time=now() where openid=#{openid}")
    void updateToken(@Param("token") String token, @Param("openid") String openid);


    //增加钱包总金额值
    @Update("update users set money = money + #{money} where id = #{id}")
    void addMoney(@Param("money") int money, @Param("id") int id);


    //扣除钱包总金额值
    @Update("update users set money = money - #{money} where id = #{id}")
    void reduceMoney(@Param("money") int money, @Param("id") int id);


    @Select("call store.seckill(#{user_id,jdbcType=INTEGER,mode=IN}, " +
            "#{flash_sale_id,jdbcType=INTEGER,mode=IN}, #{phone,jdbcType=INTEGER,mode=IN}, " +
            "#{dateTime,jdbcType=VARCHAR,mode=IN}, #{result,jdbcType=INTEGER,mode=OUT})")
    @Options(statementType = StatementType.CALLABLE)
    Integer call(Map map);

}
