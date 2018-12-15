package com.hengmall.user.model;

import io.swagger.annotations.ApiModelProperty;

public class TopicQueryReq {
	
	@ApiModelProperty(value = "分类类型")
    private Integer type;
	@ApiModelProperty(value = "当前页面数")
    private Integer draw;
	@ApiModelProperty(value = "页面数据数")
    private Integer length;
	@ApiModelProperty(value = "用户token,点赞，评论接口需要使用")
    private String token;
	@ApiModelProperty(value = "话题ID，点赞，评论,查看话题,新增话题查看数,接口需要使用")
    private Integer topicId;
	@ApiModelProperty(value = "评论ID，评论内点赞接口需要使用")
    private Integer appraiseId;
	@ApiModelProperty(value = "被评论人ID，评论接口需要使用")
    private Integer appraiseUserId;
	@ApiModelProperty(value = "内容，评论,搜索接口需要使用")
    private String context;
	
	@Override
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("TopicQueryReq{");
		buffer.append("type='").append(type).append("'");
		buffer.append("draw='").append(draw).append("'");
		buffer.append("length='").append(length).append("'");
		buffer.append("token='").append(token).append("'");
		buffer.append("topicId='").append(topicId).append("'");
		buffer.append("appraiseUserId='").append(appraiseUserId).append("'");
		buffer.append("context='").append(context).append("'");
		buffer.append("}");
		return buffer.toString();
	}
	
	public Integer getAppraiseId() {
		return appraiseId;
	}
	public void setAppraiseId(Integer appraiseId) {
		this.appraiseId = appraiseId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getDraw() {
		return draw;
	}
	public void setDraw(Integer draw) {
		this.draw = draw;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Integer getTopicId() {
		return topicId;
	}
	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}
	public Integer getAppraiseUserId() {
		return appraiseUserId;
	}
	public void setAppraiseUserId(Integer appraiseUserId) {
		this.appraiseUserId = appraiseUserId;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
}