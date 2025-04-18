package com.zust.mallapi.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 国铿
 * @Date: 2025/04/10/20:19
 * @Description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private int code; //请求状态码
    private String message; //消息
    private Object data; //响应数据


    //请求成功时候
    public static Result success(int code,String message,Object data){return new Result(code,message,data);}

    public static Result success(String message,Object data){return new Result(200,message,data);}

    public static Result success(String message){return new Result(200,message,null);}

    //请求失败的情况
    public static Result fail(int code,String message){
        return new Result(code,message,null);
    }

    public static Result fail(){
        return new Result(500,"服务器异常",null);
    }


}
