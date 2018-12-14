package com.hengmall.goods.model;


import lombok.Data;

import java.util.Date;

/**
 * 拼单详情
 */
@Data
public class CombineOrderEntity {

    private int id;
    private int combine_sale_id;//combine_sale主鍵id，正在拼哪个单
    private String user_id;    //用户id
    private String out_trade_no;  //订单号
    private int still_need;    //还需要的拼单人数
    private int combine_num;    //拼单总人数
    private String initiator;  //发起拼单的用户默认0：拼单者，1：发起者
    private int initiator_id;  //发起者 用户id

    private String start_time;  //拼单开始时间
    private String end_time;    //拼单结束时间
    private String created_time; //拼单创建时间
    private Date startTime;
    private Date endTime;
    private String head_img; // 拼单者头像
    private int status;  //拼单订单状态：1：等待拼单完成；2：拼单失败；3拼单成功
}
