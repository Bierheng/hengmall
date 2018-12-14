package com.hengmall.goods.model;

import lombok.Data;

import java.util.Date;

/**
 * 图片轮播 实体类
 */
@Data
public class PAddressEntity{

    private int id;
    private String uname;       //收获人姓名
    private String user_id;     //用户关联ID
    private String phone;           //收货人手机号码
    private int zip_code;        //邮政编码
    private String province;     //省
    private String city;        //市
    private String area;        //区
    private String street;      //街道
    private String adress;      //详细地址
    private Date ctime;       //添加时间

}
