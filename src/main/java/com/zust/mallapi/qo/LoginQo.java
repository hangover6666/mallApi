package com.zust.mallapi.qo;

import lombok.Getter;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 国铿
 * @Date: 2025/04/10/21:17
 * @Description:封装登录条件类
 */
@Getter
@Setter
public class LoginQo {
    private String username;
    private String password;
    private String code;
    private String uuid;
}
