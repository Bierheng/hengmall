package com.server.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2018/5/25.
 */

@ApiModel
public class CombineDetailsResp {

    @ApiModelProperty(value = "头像")
    String headImg;
    @ApiModelProperty(value = "拼单创建人ID")
    int initiatorId;
    @ApiModelProperty(value = "订单号")
    String out_trade_no;
    @ApiModelProperty(value = "开始时间")
    long startTime;
    @ApiModelProperty(value = "结束时间")
    long endTime;
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public int getInitiatorId() {
		return initiatorId;
	}
	public void setInitiatorId(int initiatorId) {
		this.initiatorId = initiatorId;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
    
}
