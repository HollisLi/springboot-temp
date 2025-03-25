package com.temp.biz.domain.vo.system.menu;

import com.temp.common.enums.MenuTypeEnum;
import lombok.Data;

/**
 * 管理后台 - 菜单精简信息 Response VO
 *
 * @author Hollis
 * @since 2024-06-13 16:49
 */
@Data
public class MenuSimpleRespVO {

    /**
     * 菜单Id
     */
    private Long id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 父菜单 ID
     */
    private Long parentId;

    /**
     * 菜单类型
     */
    private MenuTypeEnum type;
}
