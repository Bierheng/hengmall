package com.hengmall.user.model;

/**
 * 返回参数对应表 
 * @author Administrator
 *
 */
public enum ResMessage {

	Success(0, "Success"),
	NO_LOGIN(50001,"没有登录或已在其它设备登录"),
	BE_OVERDUE(50002,"登录凭证过期"),
	NO_POWER(50003,"权限不足"),
	OTHER(50004,"已在其它设备登录"),
	;
	
	
	public int code;
	public String message;

	private ResMessage(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public static String getMessage(int code)
	{
		for (ResMessage rm : values())
		{
			if (rm.code == code)
			{
				return rm.message;
			}
		}
		return "";
	}
}
