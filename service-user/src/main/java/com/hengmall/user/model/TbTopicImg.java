package com.server.entity;

import io.swagger.annotations.ApiModelProperty;

public class TbTopicImg {
	
    private Integer id;
	@ApiModelProperty(value = "话题ID")
    private Integer topic_id;
	@ApiModelProperty(value = "资源ID")
    private Integer resources_id;
	@ApiModelProperty(value = "资源类型，1图片，2视频")
    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public Integer getTopic_id() {
		return topic_id;
	}

	public void setTopic_id(Integer topic_id) {
		this.topic_id = topic_id;
	}

	public Integer getResources_id() {
		return resources_id;
	}

	public void setResources_id(Integer resources_id) {
		this.resources_id = resources_id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}