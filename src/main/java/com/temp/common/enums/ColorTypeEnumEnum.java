package com.temp.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 颜色类型 Enum
 *
 * @author Hollis
 * @since 2024/06/17 下午6:23
 */
@Getter
@AllArgsConstructor
public enum ColorTypeEnumEnum {
    DEFAULT("default", "默认"),
    PRIMARY("primary", "主要"),
    SUCCESS("success", "成功"),
    INFO("info", "普通"),
    WARNING("warning", "警告"),
    DANGER("danger", "危险");

    @EnumValue
    private final String value;
    private final String desc;
}
