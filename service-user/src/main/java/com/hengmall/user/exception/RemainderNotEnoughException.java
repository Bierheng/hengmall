package com.hengmall.user.exception;

/**
 * 余额不足异常
 */
public class RemainderNotEnoughException extends CheckException{

    public RemainderNotEnoughException(){
        super();
    }

    public RemainderNotEnoughException(String mes){
        super( mes );
    }


    public RemainderNotEnoughException(String mes,Throwable cause){
        super( mes , cause);
    }


    public RemainderNotEnoughException(Throwable cause){
        super(  cause);
    }

    public RemainderNotEnoughException(String mes,Throwable cause,boolean str,boolean str1){
        super(mes , cause , str ,str1) ;
    }
}
