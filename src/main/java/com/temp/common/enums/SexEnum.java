package com.temp.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 性别的枚举值
 *
 * @author Hollis
 * @since 2024-04-11 16:58
 */
@Getter
@AllArgsConstructor
public enum SexEnum {
    FEMALE(0, "女"),
    MALE(1, "男"),
    ;

    @EnumValue
    private final Integer value;
    private final String desc;
}
