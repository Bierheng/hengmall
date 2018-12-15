package com.hengmall.user.util;


import com.hengmall.user.model.Result;

/**
 * @author wuhengbin
 * @date 2018/06/20
 */
public class ResultUtil<T> {

    private Result<T> result;

    public ResultUtil(){
        result=new Result<>();
        result.setSuccess(true);
        result.setErrMsg("success");
        result.setCode(200);
    }

    public Result<T> setData(T t){
        this.result.setResult(t);
        this.result.setCode(200);
        return this.result;
    }

    public Result<T> setData(T t, String msg){
        this.result.setResult(t);
        this.result.setCode(200);
        this.result.setErrMsg(msg);
        return this.result;
    }

    public Result<T> setErrorMsg(String msg){
        this.result.setSuccess(false);
        this.result.setErrMsg(msg);
        this.result.setCode(500);
        return this.result;
    }

    public Result<T> setErrorMsg(Integer code, String msg){
        this.result.setSuccess(false);
        this.result.setErrMsg(msg);
        this.result.setCode(code);
        return this.result;
    }
   
}
