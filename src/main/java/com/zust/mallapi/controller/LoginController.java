package com.zust.mallapi.controller;

import com.zust.mallapi.common.Result;
import com.zust.mallapi.qo.LoginQo;
import com.zust.mallapi.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 国铿
 * @Date: 2025/04/10/20:12
 * @Description:
 */
@RestController  //只需要相应数据
public class LoginController {

    @Autowired
    private LoginService loginService;
    //获取验证码
    @GetMapping("/captcha")
    public Result getCaptcha(){
        //生成验证码
        Map<String, String> captcha = loginService.getCaptcha();
        return Result.success("验证码获取成功",captcha);
    }

    //登录请求
    @PostMapping("/login")
    public Result login(@RequestBody LoginQo loginQo){

        return Result.success("验证码获取成功",null);
    }
}
