package com.hengmall.goods.model;


import lombok.Data;

/**
 * 消息中心实体类
 */
@Data
public class MsgCenterEntity {

    private int id;
    private String title;      //内容标题
    private String content;    //消息内容
    private String icon;       //图标
    private String created_time;
}
