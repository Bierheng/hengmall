package com.hengmall.goods.model;


import lombok.Data;

import java.io.Serializable;

/**
 * 用户实体类
 */
@Data
public class UsersEntity implements Serializable {

    private int id;
    private String nickname;
    private String openid;    //资源地址
    private String avatar_url;       //类型，1：图片，2：视频
    private int gender;     //
    private String city;
    private String province;
    private String country;
    private int address_id;
    private String token;
    private int money;
    private String created_time;
    private String updated_time;
    private String phone;
}
