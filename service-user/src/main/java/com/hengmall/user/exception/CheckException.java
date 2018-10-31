package com.hengmall.user.exception;

/**
 * 余额不足异常
 */
public class CheckException extends RuntimeException{

    public CheckException(){
        super();
    }

    public CheckException(String mes){
        super( mes );
    }


    public CheckException(String mes, Throwable cause){
        super( mes , cause);
    }


    public CheckException(Throwable cause){
        super(  cause);
    }

    public CheckException(String mes, Throwable cause, boolean str, boolean str1){
        super(mes , cause , str ,str1) ;
    }
}
