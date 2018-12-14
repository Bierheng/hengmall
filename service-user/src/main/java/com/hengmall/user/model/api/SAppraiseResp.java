package com.server.entity.api;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2018/5/25.
 */
@ApiModel
public class SAppraiseResp {

	@ApiModelProperty(value = "评论的商品")
	int productId;
	@ApiModelProperty(value = "评价的用户名称")
	String name;
	@ApiModelProperty(value = "评价内容")
	String context;
	@ApiModelProperty(value = "评价的点赞数")
	int like;
	@ApiModelProperty(value = "上传的评价图片或视频资源地址")
	JSONObject resources;
	@ApiModelProperty(value = "评价时间")
	String date;

	@Override
	public String toString() {
		return "SAppraiseResp{" + "productId=" + productId + ", name='" + name + '\'' + ", context='" + context + '\''
				+ ", like=" + like + ", resources=" + resources + ", date='" + date + '\'' + '}';
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public JSONObject getResources() {
		return resources;
	}

	public void setResources(JSONObject resources) {
		this.resources = resources;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
