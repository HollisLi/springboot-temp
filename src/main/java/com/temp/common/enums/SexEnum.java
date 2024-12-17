package com.temp.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 性别 Enum
 *
 * @author Hollis
 * @since 2024/12/17 10:50
 */
@Getter
@AllArgsConstructor
public enum SexEnum {
    MAN(1, "男"),
    WOMAN(0, "女"),
    ;

    @EnumValue
    private final Integer code;
    private final String desc;
}
