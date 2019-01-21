package com.hengmall.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.hengmall.goods.model.TbUserEntity;

/**
 * platform库中的管理后台用户登录表
 */
@Repository
@Mapper
public interface TbUserDao {

    @Select("select * from ${platform}.tb_user where id=#{id} limit 1")
    List<TbUserEntity> findById(@Param("id") int id);


    //数据新增
    @Insert("insert into ${platform}.tb_user(username,`password`,phone,state,description,role_id,created,updated)" +
            "values(#{username},#{password},#{phone},#{state},#{desc},#{role_id},now(),now())")
    void insert(@Param("username") String username, @Param("password") String password,
                @Param("phone") String phone, @Param("state") int state, @Param("desc") String desc,
                @Param("role_id") int role_id);

}
