package com.hengmall.welfare.enums;


/**
 * 道具掉落地址状态码
 * @author zzy
 * @date 2018年9月28日
 */
public enum AddressCodeEnum {

	welfare(1);  //钓鱼


    private Integer code;


    AddressCodeEnum(Integer code) {
        this.code = code;
    }

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
}
