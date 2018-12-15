package com.hengmall.user.exception;

/**
 * @author wuhengbin
 * @date 2018.6.20
 */
public class XmallException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;

    public XmallException(String msg){
        super(msg);
        this.msg=msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
