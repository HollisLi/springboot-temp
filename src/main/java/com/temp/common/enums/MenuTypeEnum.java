package com.temp.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 菜单类型枚举类
 *
 * @author Hollis
 * @since 2024-04-16 16:41
 */
@Getter
@AllArgsConstructor
public enum MenuTypeEnum {
    DIR(1, "目录"),
    MENU(2, "菜单"),
    BUTTON(3, "按钮"),
    ;

    @EnumValue
    private final Integer value;
    private final String desc;
}
