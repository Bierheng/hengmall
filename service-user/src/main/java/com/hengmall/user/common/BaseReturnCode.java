package com.hengmall.user.common;

public class BaseReturnCode {
	/** 非法操作 **/
	public static final int ILLEGAL = -1;

	/** 参数错误 **/
	public static final int PARAMS_ERROR = 10000;
	
	/** 业务处理失败 **/
	public static final int PROCESS_ERROR = 20000;
	
	/** SessionId错误 **/
	public static final int SESSIONID_ERROR = 30001;
	/** SessionId为空 **/
	public static final int SESSIONID_EMPTY = 30002;
	/** SessionId过期 **/
	public static final int SESSIONID_EXPIRED = 30003;
	/** SessionId解析失败 **/
	public static final int SESSIONID_PARSE_FAIL = 30004;
	
	/** 当期用户没有权限进行此操作 **/
	public static final int PERMISSION_NO = 30050;
	/** 权限解析失败 **/
	public static final int PERMISSION_PARSE_FAIL = 30051;

}
