package com.zust.mallapi.exception;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 国铿
 * @Date: 2025/04/12/14:36
 * @Description:自定义业务异常
 */
public class TokenException extends RuntimeException{
    public TokenException(String message){
        super(message);
    }

}
