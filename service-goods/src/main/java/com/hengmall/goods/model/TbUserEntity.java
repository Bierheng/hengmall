package com.hengmall.goods.model;


import lombok.Data;


@Data
public class TbUserEntity {

    //  role_id=3  //供应商

    private int id;
    private String username;
    private String password;
    private int role_id;
    private String file;
    private String created;

}
