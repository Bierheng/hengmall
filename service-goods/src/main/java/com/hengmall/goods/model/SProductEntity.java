package com.hengmall.goods.model;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 商品信息 实体类
 */
@Data
public class SProductEntity {

    private int id;    //商品id
    private int shops_product_id;    //商品id
    private String name;    //商品名称
    private String headimg;   //商品展示图片，用于展示在商品列表上的图片
    private int price;   //价格
    private String attribute;   //尺寸大小、颜色
    /*private String sizes;   //大小，尺码(多个以“,”分隔)
    private String colors;  //颜色，多种颜色以"，"分割*/
    private int stock;      //库存
    private int allowrefund;  //是否允许退款，1：允许，0：不允许；默认不允许
    private int status; // 状态：1上架，0下架
    private double freight; //快递运费
    private double oldprice; //原价

    private String created_time;       //发布时间

    private JSONObject attr;
    private String attrs;           //用户购买的SKU值
    private int receiving_status;       //收货状态
    private int payment_status;     //支付状态
    private int delivery_status;     //发货状态
    private int appraisestatus;     //评价状态
    private int order_id;     //订单主键ID

    private int combine_num;     //拼单人数（拼单需满多少人）
    private String out_trade_no;     //订单号
    private String rights;     //商品特色服务

    /**
     * 商品说明；如：[{"title":"金币","content":"购买可得34积分"},
     * {"title":"邮","content":"全场包邮"},{"title":"退","content":"7天无理由退换"}]
     */
    private String description;
    private Integer shops_id;   // 店铺ID
    private String state_icon;  //国家国旗
    private int state_id;		//国家ID
    private int salesvolume;    //商品销量
    private String tagids;      //标签
    private String endimg;	    //尾部图片
    private int price_type;     //价格类型
}
