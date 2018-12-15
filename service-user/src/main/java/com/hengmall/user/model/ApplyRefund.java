package com.hengmall.user.model;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class ApplyRefund {
	
	// 2:审核中,1:通过,0:拒绝
	public static final int APPROVAL_STATUS = 2;
	public static final int PASS_STATUS = 1;
	public static final int REFUSE_STATUS = 0;
	
    private Integer id;
	@ApiModelProperty(value = "用户ID")
    private Integer user_id;
	@ApiModelProperty(value = "需要退款的订单号")
    private String out_trade_no;
	@ApiModelProperty(value = "联系人")
    private String name;
	@ApiModelProperty(value = "联系方式")
    private String phone;
	@ApiModelProperty(value = "退款原因")
    private String refund_reasons;
	@ApiModelProperty(value = "状态码，默认2：审核中，1：通过，0：拒绝")
    private Integer status;
	@ApiModelProperty(value = "拒绝原因")
    private String refuse_reason;
	@ApiModelProperty(value = "创建时间")
    private Date created_time;
	@ApiModelProperty(value = "修改时间")
    private Date updated_time;
	@ApiModelProperty(value = "审批人")
    private String approval_user;
	@ApiModelProperty(value = "审批时间")
    private Date approval_time;
	@ApiModelProperty(value = "用户ID")
    private Integer shops_id;
	@ApiModelProperty(value = "用户ID")
    private Integer order_id;
	
	@Override
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("ApplyRefund{");
		buffer.append("id='").append(id).append("'");
		buffer.append("user_id='").append(user_id).append("'");
		buffer.append("out_trade_no='").append(out_trade_no).append("'");
		buffer.append("name='").append(name).append("'");
		buffer.append("phone='").append(phone).append("'");
		buffer.append("refund_reasons='").append(refund_reasons).append("'");
		buffer.append("status='").append(status).append("'");
		buffer.append("refuse_reason='").append(refuse_reason).append("'");
		buffer.append("created_time='").append(created_time).append("'");
		buffer.append("updated_time='").append(updated_time).append("'");
		buffer.append("approval_user='").append(approval_user).append("'");
		buffer.append("approval_time='").append(approval_time).append("'");
		buffer.append("}");
		return buffer.toString();
	}
	
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


	public Integer getShops_id() {
		return shops_id;
	}

	public void setShops_id(Integer shops_id) {
		this.shops_id = shops_id;
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public Integer getUser_id() {
		return user_id;
	}


	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}


	public String getOut_trade_no() {
		return out_trade_no;
	}


	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getRefund_reasons() {
		return refund_reasons;
	}


	public void setRefund_reasons(String refund_reasons) {
		this.refund_reasons = refund_reasons;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getRefuse_reason() {
		return refuse_reason;
	}


	public void setRefuse_reason(String refuse_reason) {
		this.refuse_reason = refuse_reason;
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


	public String getApproval_user() {
		return approval_user;
	}


	public void setApproval_user(String approval_user) {
		this.approval_user = approval_user;
	}


	public Date getApproval_time() {
		return approval_time;
	}


	public void setApproval_time(Date approval_time) {
		this.approval_time = approval_time;
	}

}