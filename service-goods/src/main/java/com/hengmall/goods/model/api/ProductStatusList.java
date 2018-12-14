package com.hengmall.goods.model.api;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2018/5/25.
 */
@ApiModel
public class ProductStatusList {

    //0：待付款，1：待发货，2：待收货，3：已收货，4：已取消订单
    public static final int PENDING_STATUS = 0;
    public static final int DELIVERY_STATUS = 1;
    public static final int UNCOLLECTED_GOODS_STATUS = 2;
    public static final int RECEIVED_STATUS = 3;
    public static final int CANCEL_ORDER_STATUS = 4;

    //0：不可删除，1：可删除
    public static final int DEL_YES = 1;
    public static final int DEL_NO = 0;


    @ApiModelProperty(value = "商品主键id")
    int id;
    @ApiModelProperty(value = "订单主键ID")
    int order_id;
    @ApiModelProperty(value = "商品名称")
    String name;
    @ApiModelProperty(value = "商品数量")
    int num;
    @ApiModelProperty(value = "商品图片")
    String headimg;
    @ApiModelProperty(value = "价格")
    double price;
    @ApiModelProperty(value = "购买商品的SKU")
    JSONObject attr;
    @ApiModelProperty(value = "状态码；0：待付款，1：待发货，2：待收货，3：已收货，4：已取消订单")
    int status;
    @ApiModelProperty(value = "评价状态码；0：订单未完成，无需评价，1：未评价，2：已评价，3：追加评价")
    int appraiseStatus;
    @ApiModelProperty(value = "下单时间")
    String date;
    @ApiModelProperty(value = "订单号")
    String outTradeNo;
    
    @ApiModelProperty(value = "是否可以删除订单，0：不可删除，1：可删除")
    int isDeletion;
    @ApiModelProperty(value = "取消订单按钮状态码；0：不可取消，1：可取消")
    int isCancelOrder;
    @ApiModelProperty(value = "立即付款按钮状态码；0：无立即付款，1：立即付款")
    int isPayment;
    @ApiModelProperty(value = "提醒发货按钮状态码；0：无提醒发货，1：提醒发货")
    int isReminding;
    @ApiModelProperty(value = "查看物流按钮状态码；0：无查看物流，1：查看物流")
    int isLogistics;
    @ApiModelProperty(value = "确认收货按钮状态码；0：无确认收货，1：确认收货")
    int isCollectGoods;
    @ApiModelProperty(value = "退款按钮状态码；0：无退款，1：可退款，2：已申请退款，3：已退款，4：拒绝退款")
    int isRefund;


    @Override
    public String toString() {
        return "ProductStatusList{" +
                "id=" + id +
                ", order_id=" + order_id +
                ", name='" + name + '\'' +
                ", headimg='" + headimg + '\'' +
                ", price=" + price +
                ", attr=" + attr +
                ", status=" + status +
                ", appraiseStatus=" + appraiseStatus +
                ", date='" + date + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", isDeletion=" + isDeletion +
                ", isCancelOrder=" + isCancelOrder +
                ", isPayment=" + isPayment +
                ", isReminding=" + isReminding +
                ", isLogistics=" + isLogistics +
                ", isCollectGoods=" + isCollectGoods +
                ", isRefund=" + isRefund +
                '}';
    }


    public int getIsRefund() {
        return isRefund;
    }

    public void setIsRefund(int isRefund) {
        this.isRefund = isRefund;
    }

    public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


	public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public JSONObject getAttr() {
        return attr;
    }

    public void setAttr(JSONObject attr) {
        this.attr = attr;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAppraiseStatus() {
        return appraiseStatus;
    }

    public void setAppraiseStatus(int appraiseStatus) {
        this.appraiseStatus = appraiseStatus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIsDeletion() {
        return isDeletion;
    }

    public void setIsDeletion(int isDeletion) {
        this.isDeletion = isDeletion;
    }

    public int getIsCancelOrder() {
        return isCancelOrder;
    }

    public void setIsCancelOrder(int isCancelOrder) {
        this.isCancelOrder = isCancelOrder;
    }

    public int getIsPayment() {
        return isPayment;
    }

    public void setIsPayment(int isPayment) {
        this.isPayment = isPayment;
    }

    public int getIsReminding() {
        return isReminding;
    }

    public void setIsReminding(int isReminding) {
        this.isReminding = isReminding;
    }

    public int getIsLogistics() {
        return isLogistics;
    }

    public void setIsLogistics(int isLogistics) {
        this.isLogistics = isLogistics;
    }

    public int getIsCollectGoods() {
        return isCollectGoods;
    }

    public void setIsCollectGoods(int isCollectGoods) {
        this.isCollectGoods = isCollectGoods;
    }
}

