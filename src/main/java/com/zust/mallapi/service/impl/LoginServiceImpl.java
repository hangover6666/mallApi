package com.zust.mallapi.service.impl;

import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zust.mallapi.common.Constants;
import com.zust.mallapi.entity.Admin;
import com.zust.mallapi.exception.BusinessException;
import com.zust.mallapi.mapper.AdminMapper;
import com.zust.mallapi.qo.LoginQo;
import com.zust.mallapi.service.LoginService;
import com.zust.mallapi.utils.RedisUtils;
import com.zust.mallapi.utils.VerifyCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

    @Autowired
    private AdminMapper adminMapper;

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

    @Override
    public Map<String, String> login(LoginQo qo) {
        //校验验证码是否正确
        //1.先从redis中获取验证码
        String code = redisUtils.get(Constants.LOGIN_CAPTCHA_CODE + qo.getUuid());
        //2.判断用户输入的验证码与redis中存储的验证码是否一致
        if (!VerifyCodeUtil.verification(code,qo.getCode(),true)) {
                //抛出一个异常
              throw new BusinessException("验证码错误");
        }

        //验证码一致
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Admin::getAdminName,qo.getUsername()).eq(Admin::getAdminPassword,qo.getPassword());
        Admin loginAdmin = adminMapper.selectOne(wrapper);
        //判断登录是否成功，看看有没有这个实例对象，loginAdmin不为null,说明登录成功，否则为null
        if ( loginAdmin== null){
            throw new BusinessException("账户或密码输入错误");
        }

        //登录成功，生成token
        String token  = UUID.randomUUID().toString().replaceAll("-","");

        //将登录的用户信息存储到redis当中，token作为key，登录的用户对象，作为value，放到redis当中去
        //.toString()转换成字符串，但要注意，到将来，还要还原成对象。不重写这个方法，这里面放的是内存地址。不推荐使用
        //推荐转换成json格式字符串，利用hutu工具包
        //而且设置token需要给他提供一个前缀，提高可读性。
        redisUtils.set(Constants.LOGIN_ADMIN_INFO_TOKEN + token, JSONUtil.toJsonStr(loginAdmin),Constants.LOGIN_ADMIN_INFO_TOKEN_EXPIRE_TIME);

        Map<String,String> map = new HashMap<>();
        map.put("token",token);
        return map;
    }

    @Override
    public Admin getUserInfo(HttpServletRequest request) {
        //获取用户信息逻辑   思路：获取请求头的信息->要获取一个请求对象，controller有
        //1.获取请求头当中的token
        String token = request.getHeader("Authorization");
        //2.从redis中获取用户信息
        String loginAdminJson = redisUtils.get(Constants.LOGIN_ADMIN_INFO_TOKEN + token);
        //3.将loginAdminJson转换成admin对象,用toBean方法转换成admin对象，
        Admin loginAdmin = JSONUtil.toBean(loginAdminJson, Admin.class);
        //把（敏感信息）密码清空，安全起见
        loginAdmin.setAdminPassword(null);

        return loginAdmin;
    }
}
