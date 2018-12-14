package com.hengmall.goods.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class Ajax<T> implements Serializable {

    @ApiModelProperty("状态码，成功为0， 其他错误")
    private int code;

    @ApiModelProperty("错误消息，仅在code不为0时出现")
    private String errMsg;

    @ApiModelProperty(value = "成功内容体，仅在code为0时出现")
    private T data;


    //客户端回调标识
    private String digest;

    public Ajax(){
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

    @Override
    public String toString() {
        return "Ajax{" +
                "code=" + code +
                ", errMsg='" + errMsg + '\'' +
                ", data=" + data +
                ", digest='" + digest + '\'' +
                '}';
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
}