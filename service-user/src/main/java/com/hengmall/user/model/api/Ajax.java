package com.hengmall.user.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

@ApiModel
public class Ajax<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("状态码，成功为0， 其他错误")
	private int code;

	@ApiModelProperty("错误消息，仅在code不为0时出现")
	private String errMsg;

	@ApiModelProperty(value = "成功内容体，仅在code为0时出现")
	private T data;

	// 客户端回调标识
	private String digest;

	public Ajax() {
		this.code = 0;
		this.data = null;
	}

	public Ajax(T data) {
		this.code = 0;
		this.data = data;
	}

	public Ajax(int code, String errMsg) {
		this.code = code;
		this.errMsg = errMsg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public Object getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}
	
	
	/**
	 * @Title: 过滤器写入错误信息 
	 * @Description: TODO
	 */
	public String pushError(HttpServletRequest request, int code, String msg)
	{
		this.code = code;
		this.errMsg = msg;

		String jsonpCallback = request.getParameter("jsonpCallback");
		if (jsonpCallback != null)
		{
			return jsonpCallback + "(" + JSONObject.toJSONString(this) + ")";
		}
		return JSONObject.toJSONString(this);
	}
}