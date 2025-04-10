package com.zust.mallapi.service.impl;

import com.zust.mallapi.common.Constants;
import com.zust.mallapi.qo.LoginQo;
import com.zust.mallapi.service.LoginService;
import com.zust.mallapi.utils.RedisUtils;
import com.zust.mallapi.utils.VerifyCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 国铿
 * @Date: 2025/04/10/20:40
 * @Description:
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private RedisUtils redisUtils;
    @Override
    public Map<String, String> getCaptcha() {
        //获取验证码
        Map<String, String> map = VerifyCodeUtil.generateVerifyCode();
        //从map中获取验证码，把验证码存到redis，并设置一个过期时间
        String code = map.get("code");
        String uuid = map.get("uuid");
        redisUtils.set(Constants.LOGIN_CAPTCHA_CODE +uuid,code,Constants.LOGIN_CAPTCHA_CODE_EXPIRE_TIME);

        //将map当中的验证码删除
        map.remove("code");
        return map;
    }

//    @Override
//    public Map<String, String> login(LoginQo qo) {
//        //校验验证码是否正确
//        //1.先从redis中获取验证码
//        String code = redisUtils.get(Constants.LOGIN_CAPTCHA_CODE + qo.getUuid());
//        //2.判断用户输入的验证码与redis中存储的验证码是否一致
//        if (!VerifyCodeUtil.verification(code,qo.getCode(),true)) {
//              throw
//        }
//
//        return null;
//    }
}
