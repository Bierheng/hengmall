package com.hengmall.user.model;

import io.swagger.annotations.ApiModelProperty;

public class PraiseReq {
	@ApiModelProperty(value = "是否点赞，0：未点赞，1：已点赞")
    private Integer is_praise;
	@ApiModelProperty(value = "已经点赞数")
    private Integer praiseNum;
	
	public Integer getIs_praise() {
		return is_praise;
	}

	public void setIs_praise(Integer is_praise) {
		this.is_praise = is_praise;
	}

	public Integer getPraiseNum() {
		return praiseNum;
	}

	public void setPraiseNum(Integer praiseNum) {
		this.praiseNum = praiseNum;
	}
}