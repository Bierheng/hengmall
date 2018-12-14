package com.hengmall.goods.enums;


/**
 * 状态码
 * @author zzy
 * @date 2018年9月27日
 */
public enum StatusEnum {
	NORMAL(0),  // 正常

	REMOVE(1);  // 删除

	// 对应树屋等级
    private Integer statusCode;


    StatusEnum(Integer statusCode) {
        this.statusCode = statusCode;
    }


	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
}
