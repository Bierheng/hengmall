package com.server.entity;


import java.util.Date;

/**
 * 拼单 实体类
 */
public class CombineSaleEntity {

    private int id;          //拼单ID
    private int product_id;  // 店铺商品id
    private int price;       //拼单购买价格
    private int status;      //有效状态；默认1：未开始，2：已开始，0：无效
    private int stock;       //秒杀库存
    private String title;    //拼单标题
    private String icon;     //拼单图标
    private String start_time; //限时开始时间
    private String end_time;   //限时结束时间
    private String created_time;//创建时间
    private String nickname;    // 昵称
    private String head_img;    //头像
    private int initiator;      //拼主，0：拼单者，1：发起者
    private String out_trade_no;//订单号
    private Date startTime;     //开始时间
    private Date endTime;       //结束时间
    private int user_id;        //用户ID
    private int combine_num;    //拼单人数
    private int still_need;     //拼单还需人数
    private int shops_id;       //店铺ID
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getCreated_time() {
		return created_time;
	}
	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHead_img() {
		return head_img;
	}
	public void setHead_img(String head_img) {
		this.head_img = head_img;
	}
	public int getInitiator() {
		return initiator;
	}
	public void setInitiator(int initiator) {
		this.initiator = initiator;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getCombine_num() {
		return combine_num;
	}
	public void setCombine_num(int combine_num) {
		this.combine_num = combine_num;
	}
	public int getStill_need() {
		return still_need;
	}
	public void setStill_need(int still_need) {
		this.still_need = still_need;
	}
	public int getShops_id() {
		return shops_id;
	}
	public void setShops_id(int shops_id) {
		this.shops_id = shops_id;
	}
    
    
}
