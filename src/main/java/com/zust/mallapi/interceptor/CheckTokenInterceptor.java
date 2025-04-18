package com.zust.mallapi.interceptor;

import com.zust.mallapi.common.Constants;
import com.zust.mallapi.exception.BusinessException;
import com.zust.mallapi.exception.TokenException;
import com.zust.mallapi.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 国铿
 * @Date: 2025/04/15/11:45
 * @Description:检查token拦截器
 */
//交给spring容器管理，但是他不属于三层架构
@Component
public class CheckTokenInterceptor implements HandlerInterceptor {


    @Autowired
    public RedisUtils redisUtils;




    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //检查用户是否携带token，表示没有登录
        String token = request.getHeader("Authorization");
        if (token == null){
            throw new TokenException("检测到未登录，请登录");
        }

        //检测当前登录是否失效：redis中是否有token的key
        if (!redisUtils.exists(Constants.LOGIN_ADMIN_INFO_TOKEN + token)) {
            throw new BusinessException("登陆失效，请重新登录");
        }
        //放行请求
        return true;
    }
}
