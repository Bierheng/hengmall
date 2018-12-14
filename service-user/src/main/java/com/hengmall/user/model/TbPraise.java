package com.server.entity;

import io.swagger.annotations.ApiModelProperty;

public class TbPraise {
    private Integer id;
	@ApiModelProperty(value = "用户id")
    private Integer userId;
	@ApiModelProperty(value = "话题id")
    private Integer topicId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }
}