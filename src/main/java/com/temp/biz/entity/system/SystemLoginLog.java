package com.temp.biz.entity.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.temp.biz.entity.BaseEntity;
import com.temp.common.enums.LoginLogTypeEnum;
import com.temp.common.enums.LoginResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统访问记录
 *
 * @author Hollis
 * @since 2024-06-11 18:15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "system_login_log")
public class SystemLoginLog extends BaseEntity {

    /**
     * 访问ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 日志类型, 账号登录:100, 主动登出:200, 强制退出:202
     */
    @TableField(value = "log_type")
    private LoginLogTypeEnum logType;

    /**
     * 用户Id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 用户账号
     */
    @TableField(value = "username")
    private String username;

    /**
     * 登陆结果, 成功:0, 账号或密码不正确:10, 用户被禁用:20
     */
    @TableField(value = "`result`")
    private LoginResultEnum result;

    /**
     * 用户 IP
     */
    @TableField(value = "user_ip")
    private String userIp;

    /**
     * 浏览器 UA
     */
    @TableField(value = "user_agent")
    private String userAgent;
}