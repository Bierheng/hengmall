package com.hengmall.goods.model;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class ShopsLocation {
	
	@ApiModelProperty(value = "店铺ID")
    private Integer id;
	@ApiModelProperty(value = "店铺主人ID")
    private Integer user_id;
	@ApiModelProperty(value = "地理位置纬度")
    private String lat;
	@ApiModelProperty(value = "地理位置经度")
    private String lng;
	@ApiModelProperty(value = "店铺地址")
    private String address;
	@ApiModelProperty(value = "创建时间")
    private Date created_time;
	@ApiModelProperty(value = "店铺名称")
    private String shops_name;
	@ApiModelProperty(value = "店铺头像")
    private String avatar_url;
	@ApiModelProperty(value = "国籍")
    private String national;
	@ApiModelProperty(value = "粉丝数")
    private Integer fans_num;
	@ApiModelProperty(value = "订单总价")
    private Double total_price;
	@ApiModelProperty(value = "订单号")
    private String out_trade_no;
	@ApiModelProperty(value = "支付流水号")
    private String pay_no;
	@ApiModelProperty(value = "订单ID")
    private int order_id;
	@ApiModelProperty(value = "订单状态")
    private int status;
	@ApiModelProperty(value = "订单列表")
    private List<?> orderList;
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
	@ApiModelProperty(value = "店铺描述")
    private String describe;
    @ApiModelProperty(value = "评价状态码；0：订单未完成，无需评价，1：未评价，2：已评价，3：追加评价")
    int appraiseStatus;
	@ApiModelProperty(value = "支付时间")
    private String pay_time;
	@ApiModelProperty(value = "购买时间")
    private String purchase_time;

	
	@Override
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("ShopsLocation{");
		buffer.append("id='").append(id).append("'");
		buffer.append("user_id='").append(user_id).append("'");
		buffer.append("address='").append(address).append("'");
		buffer.append("lat='").append(lat).append("'");
		buffer.append("lng='").append(lng).append("'");
		buffer.append("created_time='").append(created_time).append("'");
		buffer.append("shops_name='").append(shops_name).append("'");
		buffer.append("avatar_url='").append(avatar_url).append("'");
		buffer.append("national='").append(national).append("'");
		buffer.append("fans_num='").append(fans_num).append("'");
		buffer.append("describe='").append(describe).append("'");
		buffer.append("}");
		return buffer.toString();
	}
	
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
	public String getPay_time() {
		return pay_time;
	}

	public void setPay_time(String pay_time) {
		this.pay_time = pay_time;
	}

	public String getPurchase_time() {
		return purchase_time;
	}

	public void setPurchase_time(String purchase_time) {
		this.purchase_time = purchase_time;
	}

	public int getAppraiseStatus() {
		return appraiseStatus;
	}

	public void setAppraiseStatus(int appraiseStatus) {
		this.appraiseStatus = appraiseStatus;
	}

	public String getPay_no() {
		return pay_no;
	}

	public void setPay_no(String pay_no) {
		this.pay_no = pay_no;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Double total_price) {
		this.total_price = total_price;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	

	public List<?> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<?> orderList) {
		this.orderList = orderList;
	}

	public Integer getUser_id() {
		return user_id;
	}


	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreated_time() {
		return created_time;
	}

	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}

	public String getShops_name() {
		return shops_name;
	}

	public void setShops_name(String shops_name) {
		this.shops_name = shops_name;
	}

	public String getAvatar_url() {
		return avatar_url;
	}

	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
	}

	public String getNational() {
		return national;
	}

	public void setNational(String national) {
		this.national = national;
	}

	public Integer getFans_num() {
		return fans_num;
	}

	public void setFans_num(Integer fans_num) {
		this.fans_num = fans_num;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
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

	public int getIsRefund() {
		return isRefund;
	}

	public void setIsRefund(int isRefund) {
		this.isRefund = isRefund;
	}
}