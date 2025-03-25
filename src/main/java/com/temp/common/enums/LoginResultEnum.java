package com.temp.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 登录结果的枚举类
 *
 * @author Hollis
 * @since 2024-06-19 10:47
 */
@Getter
@AllArgsConstructor
public enum LoginResultEnum {
    SUCCESS(0, "成功"),

    BAD_CREDENTIALS(10, "账号或密码不正确"),
    BAD_ENTERPRISE(11, "账号所属企业不存在"),

    USER_DISABLED(20, "用户被禁用"),
    USER_ENTERPRISE_DISABLED(21, "所属企业被禁用"),

    UNKNOWN_EXCEPTION(100, "未知异常"),
    ;

    @EnumValue
    private final Integer value;
    private final String desc;

}
