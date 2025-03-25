package com.temp.common.enums;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 通用状态枚举
 *
 * @author Hollis
 * @since 2024-04-11 13:46
 */
@Getter
@AllArgsConstructor
public enum CommonStatusEnum {
    ENABLE(0, "开启"),
    DISABLE(1, "关闭"),
    ;

    @EnumValue
    private final Integer value;
    private final String desc;

    public static boolean isEnable(Integer status) {
        return ObjUtil.equal(ENABLE.value, status);
    }

    public static boolean isDisable(Integer status) {
        return ObjUtil.equal(DISABLE.value, status);
    }
}
