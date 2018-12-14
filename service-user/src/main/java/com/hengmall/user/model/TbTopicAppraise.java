package com.server.entity;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class TbTopicAppraise {
    private Integer id;
	@ApiModelProperty(value = "用户ID")
    private Integer user_id;
	@ApiModelProperty(value = "点赞数")
    private Integer look;
	@ApiModelProperty(value = "被评论人ID")
    private Integer appraise_user;
	@ApiModelProperty(value = "话题ID")
    private Integer topic_id;
	@ApiModelProperty(value = "评论内容")
    private String context;
	@ApiModelProperty(value = "创建时间")
    private Date create_time;
	@ApiModelProperty(value = "子评论")
    private List<TbTopicAppraise> childAppraise;
	@ApiModelProperty(value = "用户名")
    private String userName;
	@ApiModelProperty(value = "头像")
    private String url;
	@ApiModelProperty(value = "评论时间")
    private String date;
	@ApiModelProperty(value = "是否已点赞,0:没点赞，1：已点赞")
    private Integer is_appraise;
	
	@Override 
	public String toString(){
			StringBuffer buffer = new StringBuffer();
			buffer.append("TbTopicAppraise{");
			buffer.append("id='").append(id).append("'");
			buffer.append("user_id='").append(user_id).append("'");
			buffer.append("look='").append(look).append("'");
			buffer.append("appraise_user='").append(appraise_user).append("'");
			buffer.append("topic_id='").append(topic_id).append("'");
			buffer.append("context='").append(context).append("'");
			buffer.append("create_time='").append(create_time).append("'");
			buffer.append("childAppraise='").append(childAppraise).append("'");
			buffer.append("userName='").append(userName).append("'");
			buffer.append("url='").append(url).append("'");
			buffer.append("date='").append(date).append("'");
			buffer.append("is_appraise='").append(is_appraise).append("'");
			buffer.append("}");
			return buffer.toString();
	}
	

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getLook() {
		return look;
	}

	public void setLook(Integer look) {
		this.look = look;
	}

	public Integer getAppraise_user() {
		return appraise_user;
	}

	public void setAppraise_user(Integer appraise_user) {
		this.appraise_user = appraise_user;
	}

	public Integer getTopic_id() {
		return topic_id;
	}

	public void setTopic_id(Integer topic_id) {
		this.topic_id = topic_id;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
	
    public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public List<TbTopicAppraise> getChildAppraise() {
		return childAppraise;
	}

	public void setChildAppraise(List<TbTopicAppraise> childAppraise) {
		this.childAppraise = childAppraise;
	}

	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public Integer getIs_appraise() {
		return is_appraise;
	}

	public void setIs_appraise(Integer is_appraise) {
		this.is_appraise = is_appraise;
	}
}