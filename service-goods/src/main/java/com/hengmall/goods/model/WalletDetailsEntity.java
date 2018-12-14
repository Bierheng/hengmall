package com.hengmall.goods.model;


import lombok.Data;

/**
 * 商品活动模块实体类
 */
@Data
public class WalletDetailsEntity {

    private int id;
    private int user_id;
    private int price;    //支付金额（单位：分）
    private int money;       // 支付后获得的虚拟货币（单位：分）
    private int status;     //收支状态；默认1：收入，2：支出
    private int new_val;     //
    private int old_val;     //
    private String created_time;
}
