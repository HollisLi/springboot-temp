package com.temp.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 登录日志的类型枚举
 *
 * @author Hollis
 * @since 2024-06-11 18:19
 */
@Getter
@AllArgsConstructor
public enum LoginLogTypeEnum {
    LOGIN_USERNAME(100, "账号密码登录"),

    LOGOUT_SELF(200, "主动登出"),
    LOGOUT_FORCED(202, "强制退出"),
    ;

    @EnumValue
    private final Integer value;
    private final String desc;

}
