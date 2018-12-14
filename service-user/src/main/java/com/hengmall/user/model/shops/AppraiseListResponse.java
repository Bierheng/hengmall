package com.server.entity.shops;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 商品评论列表 entity
 * @author Administrator
 *
 */
public class AppraiseListResponse{
	
	@ApiModelProperty(value = "评论id(进入子页面用到)")
	private String id;    //id
	
	@ApiModelProperty(value = "店铺id(预留)")
	private String shopsId;   
	
	@ApiModelProperty(value = "店铺名(一)")
    private String shopsName;
	
	@ApiModelProperty(value = "商品id(预留)")
	private String productId;
	
	@ApiModelProperty(value = "评论的商品名称(二)")
    private String productName;
	
	@ApiModelProperty(value = "评论的商品图(三)")
    private String productImg;
	
	@ApiModelProperty(value = "评价的用户id(预留)")
    private String userid;
	
	@ApiModelProperty(value = "评价的用户(四)")
    private String userName;
	
	@ApiModelProperty(value = "评价内容(五)")
    private String context;
	
	@ApiModelProperty(value = "评价时间(六)")
    private Date ctime;
	
	@ApiModelProperty(value = "该条评价的点赞数(子页面)")
    private Integer like;
	
	@ApiModelProperty(value = "上传的评价图片或视频资源地址(子页面)")
    private String resources;
	
	@ApiModelProperty(value = "描述相符，星级评价(子页面)")
    private Integer matching;
	
	@ApiModelProperty(value = "发货速度星级评价(子页面)")
    private Integer deliveryspeed;
	
	@ApiModelProperty(value = "商品星级评分(子页面)")
    private Integer score;
	
	@ApiModelProperty(value = "服务质量星级评分(子页面)")
    private Integer servicequality;
	
	@ApiModelProperty(value = "配送员服务态度星级评分(子页面) ")
    private Integer serviceattitude;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShopsId() {
		return shopsId;
	}

	public void setShopsId(String shopsId) {
		this.shopsId = shopsId;
	}

	public String getShopsName() {
		return shopsName;
	}

	public void setShopsName(String shopsName) {
		this.shopsName = shopsName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Integer getLike() {
		return like;
	}

	public void setLike(Integer like) {
		this.like = like;
	}

	public String getResources() {
		return resources;
	}

	public void setResources(String resources) {
		this.resources = resources;
	}

	public Integer getMatching() {
		return matching;
	}

	public void setMatching(Integer matching) {
		this.matching = matching;
	}

	public Integer getDeliveryspeed() {
		return deliveryspeed;
	}

	public void setDeliveryspeed(Integer deliveryspeed) {
		this.deliveryspeed = deliveryspeed;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getServicequality() {
		return servicequality;
	}

	public void setServicequality(Integer servicequality) {
		this.servicequality = servicequality;
	}

	public Integer getServiceattitude() {
		return serviceattitude;
	}

	public void setServiceattitude(Integer serviceattitude) {
		this.serviceattitude = serviceattitude;
	}

	
	
}