package com.hengmall.user.model;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class FlashSaleTimeLiness {
	
	public final static int KILL_TYPE = 1;
	public final static int COLLAGE_TYPE = 2;
	
    private Integer id;
	@ApiModelProperty(value = "描述")
    private String desc;
	@ApiModelProperty(value = "抢购开始时间")
	private Date start_time;
	@ApiModelProperty(value = "抢购结束时间")
    private Date end_time;
	@ApiModelProperty(value = "创建时间")
    private Date created_time;
	@ApiModelProperty(value = "修改时间")
    private Date  updated_time;																															

	@Override
	public String toString() {	
		StringBuffer buffer = new StringBuffer();
		buffer.append("FlashSaleTimeLiness{");
		buffer.append("id='").append(id).append("'");
		buffer.append("desc='").append(desc).append("'");
		buffer.append("start_time='").append(start_time).append("'");
		buffer.append("end_time='").append(end_time).append("'");
		buffer.append("updated_time='").append(updated_time).append("'");
		buffer.append("created_time='").append(created_time).append("'");
		buffer.append("}");
		return buffer.toString();
	}
	
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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

	public Date getCreated_time() {
		return created_time;
	}

	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}

	public Date getUpdated_time() {
		return updated_time;
	}

	public void setUpdated_time(Date updated_time) {
		this.updated_time = updated_time;
	}

}