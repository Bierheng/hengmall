package com.hengmall.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.hengmall.goods.model.PAddressEntity;
import com.hengmall.goods.model.constitute.AddressList;
import com.hengmall.goods.model.constitute.AddressOperation;

/**
 * Created by Administrator on 2018/5/24.
 */
@Repository
public interface PAddressDao {

    //根据id查询
    @Select("select * from p_address where id = #{id}")
    PAddressEntity queryById(@Param("id") int id);


    //新增
    @Insert("insert into p_address(user_id,uname,phone,zip_code,province,city,area,street,address,ctime)" +
            "values(#{user_id},#{uname},#{phone},#{zip_code},#{province},#{city},#{area},#{street},#{address},now())")
    void insert(@Param("user_id") int user_id,@Param("uname") String uname, @Param("phone") String phone,
                @Param("zip_code") int zip_code, @Param("province") String province, @Param("city") String city,
                @Param("area") String area, @Param("street") String street, @Param("address") String address);


    //根据用户id查询收货地址
    @Select("select * from p_address where id = (select address_id from users where id = #{id}) " +
            "UNION all " +
            "select * from p_address where user_id = #{id} and id !=(select address_id from users where id =#{id})" +
            " limit #{offset},#{limit}")
    List<AddressList> getAddressList(@Param("id") int id,@Param("offset") int offset,
                                     @Param("limit") int limit);


    //根据主键id删除数据
    @Delete("delete from p_address where id = #{id}")
    void delById(@Param("id") int id);


    //根据主键id修改数据
    @Update("update p_address set uname=#{edit.uname},phone = #{edit.phone}," +
            "zip_code =#{edit.zip_code} ,province=#{edit.province}," +
            "city = #{edit.city},area=#{edit.area},address=#{edit.address} " +
            "where id = #{edit.id}")
    void updateById(@Param("edit") AddressOperation editAddress);

}
