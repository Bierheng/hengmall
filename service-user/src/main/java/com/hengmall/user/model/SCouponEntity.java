package com.server.entity;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 卡券 实体类
 */
public class SCouponEntity {

	private int id; // 商品类别id
	@ApiModelProperty(value = "优惠券名称")
	private String title; // 优惠券名称
	@ApiModelProperty(value = "满多少可用（单位：元）")
	private int prerequisite; // 满多少可用（单位：元）
	@ApiModelProperty(value = "优惠券面值（单位：元）")
	private int price; // 优惠券面值（单位：元）
	@ApiModelProperty(value = "开始时间")
	private Date start_time; // 开始时间
	@ApiModelProperty(value = "结束时间")
	private Date end_time; // 结束时间
	@ApiModelProperty(value = "创建时间")	
	private Date created_time; // 创建时间
	@ApiModelProperty(value = "资源地址，对象存储地址")		
	private String img;// 资源地址，对象存储地址
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("SCouponEntity{");
		buffer.append("id='").append(id).append("'");
		buffer.append("title='").append(title).append("'");
		buffer.append("prerequisite='").append(prerequisite).append("'");
		buffer.append("price='").append(price).append("'");
		buffer.append("start_time='").append(start_time).append("'");
		buffer.append("end_time='").append(end_time).append("'");
		buffer.append("created_time='").append(created_time).append("'");
		buffer.append("img='").append(img).append("'");
		buffer.append("}");
		return buffer.toString();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrerequisite() {
		return prerequisite;
	}
	public void setPrerequisite(int prerequisite) {
		this.prerequisite = prerequisite;
	}

	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public Date getCreated_time() {
		return created_time;
	}
	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}

	
}
