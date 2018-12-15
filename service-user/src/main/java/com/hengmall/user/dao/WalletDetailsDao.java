package com.hengmall.user.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletDetailsDao {

//    //根据user_id 查询钱包明细
//    @Select("select * from wallet_details where user_id = #{user_id}")
//    List<WalletDetailsEntity> queryByUserId(@Param("user_id") int user_id);


    //数新增
    @Insert("insert into wallet_details(user_id,money,old_val,new_val,`status`,created_time)" +
            "values(#{user_id},#{money},#{old_val},#{new_val},#{status},now())")
    void insertToWalletDetails(@Param("user_id") int user_id, @Param("money") int money,
                               @Param("old_val") int old_val, @Param("new_val") int new_val,
                               @Param("status") int status);
    
    //数新增
    @Insert("update wallet_details set money = #{money},old_val =#{old_val},new_val = #{new_val},`status` = #{status},updated_time = now() " +
            "where user_id = #{user_id}")
    void updateWalletDetails(@Param("user_id") int user_id, @Param("money") int money,
                               @Param("old_val") int old_val, @Param("new_val") int new_val,
                               @Param("status") int status);


}
