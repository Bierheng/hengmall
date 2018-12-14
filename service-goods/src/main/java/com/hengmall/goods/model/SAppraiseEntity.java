package com.hengmall.goods.model;

import lombok.Data;

/**
 * 商品评价 实体类
 */
@Data
public class SAppraiseEntity {

    private int id;
    private int productid;   //评论的商品
    private int userid;     //评价的用户
    private String context;    //评价内容
    private int like;       //该条评价的点赞数
    private String resources;    //上传的评价图片或视频资源地址
    private String ctime;    //评价时间

    private String nickname;    //用户名称
    private String url;    //用户头像
    private String attr;    //商品SKU
}
