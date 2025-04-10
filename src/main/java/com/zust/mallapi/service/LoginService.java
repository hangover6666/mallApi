package com.zust.mallapi.service;

import com.zust.mallapi.qo.LoginQo;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 国铿
 * @Date: 2025/04/10/20:38
 * @Description:
 */
public interface LoginService {

    //获取验证码
    public Map<String,String> getCaptcha();

    //登录
//    public Map<String,String> login(LoginQo qo);

}
