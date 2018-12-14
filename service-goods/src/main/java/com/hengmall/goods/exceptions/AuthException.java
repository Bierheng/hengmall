package com.hengmall.goods.exceptions;

import java.io.Serializable;

/**
 * Created by guange on 03/05/2017.
 */
public class AuthException extends RuntimeException implements Serializable {

    public static final int PasswordError = 1;
    public static final int UnActive = 2;

    private int errorCode;
    public AuthException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }


}
