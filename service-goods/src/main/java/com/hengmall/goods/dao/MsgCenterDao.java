package com.hengmall.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.hengmall.goods.model.MsgCenterEntity;

@Repository
@Mapper
public interface MsgCenterDao {

    //根据状态码查询
    @Select("select * from msg_center a,msg_center_record b where a.id = b.msg_center_id " +
            "and b.user_id=#{user_id}")
    List<MsgCenterEntity> queryByUserId(@Param("user_id") int user_id);


    //根据主键ID删除数据
    @Delete("delete from msg_center_record where user_id=#{user_id} and msg_center_id in(${id})")
    void delByIds(@Param("user_id") int user_id, @Param("id") String id);

}
