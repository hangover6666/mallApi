package com.zust.mallapi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 管理员表
 * </p>
 *
 * @author 国铿
 * @since 2025-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "admin_id", type = IdType.AUTO)
    private Integer adminId;

    /**
     * 账户名
     */
    private String adminName;

    /**
     * 昵称
     */
    private String adminNickname;

    /**
     * 密码
     */
    private String adminPassword;

    /**
     * 头像地址
     */
    private String adminProfilePictureSrc;


}
