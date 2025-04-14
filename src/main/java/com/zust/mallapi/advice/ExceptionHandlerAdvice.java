package com.zust.mallapi.advice;

import com.zust.mallapi.common.Result;
import com.zust.mallapi.exception.BusinessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 国铿
 * @Date: 2025/04/10/21:30
 * @Description:全局异常处理器
 */
@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(Exception.class)
    public Result handler(Exception e){
        e.printStackTrace();
        return Result.fail();
    }



    //处理业务自己的异常
    @ExceptionHandler(BusinessException.class)
    public Result handler(BusinessException e){
        e.printStackTrace();
        return Result.fail(501,e.getMessage());
    }
}
