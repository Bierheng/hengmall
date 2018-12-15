package com.hengmall.user.model;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 商品评价 实体类
 */
public class SAppraiseEntity {

	private int id;
	@ApiModelProperty(value = "评论的商品id")
	private int productid; // 评论的商品
	@ApiModelProperty(value = "评价的用户id")	
	private int userid; // 评价的用户
	@ApiModelProperty(value = "评价内容")		
	private String context; // 评价内容
	@ApiModelProperty(value = "该条评价的点赞数")		
	private int like; // 该条评价的点赞数
	@ApiModelProperty(value = "上传的评价图片或视频资源地址")	
	private String resources; // 上传的评价图片或视频资源地址
	@ApiModelProperty(value = "评价时间")		
	private Date ctime; // 评价时间
	@ApiModelProperty(value = "用户名称")
	private String name; // 用户名称

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResources() {
		return resources;
	}

	public void setResources(String resources) {
		this.resources = resources;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
}
