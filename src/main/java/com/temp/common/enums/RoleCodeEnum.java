package com.temp.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * 角色标识 Enum
 *
 * @author Hollis
 * @since 2024/06/13 上午11:19
 */
@Getter
@AllArgsConstructor
public enum RoleCodeEnum {
    SUPER_ADMIN("SUPER_ADMIN", "超级管理员"),
    ;

    /**
     * 角色编码
     */
    private final String code;

    /**
     * 名字
     */
    private final String desc;

    public static boolean isSuperAdmin(String code) {
        return StringUtils.equalsIgnoreCase(code, SUPER_ADMIN.getCode());
    }
}
