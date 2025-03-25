package com.temp.biz.domain.vo.system.menu;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.temp.common.enums.CommonStatusEnum;
import com.temp.common.enums.MenuTypeEnum;
import lombok.Data;

import java.time.ZonedDateTime;

/**
 * 管理后台 - 菜单信息 Response VO
 *
 * @author Hollis
 * @since 2024-06-13 16:28
 */
@Data
public class MenuResVO {

    /**
     * 菜单ID
     */
    private Long id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 权限标识,仅菜单类型为按钮时，才需要传递
     */
    private String permission;

    /**
     * 菜单类型
     */
    private MenuTypeEnum type;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 父菜单 ID
     */
    private Long parentId;

    /**
     * 路由地址,仅菜单类型为菜单或者目录时，才需要传
     */
    private String path;

    /**
     * 菜单图标,仅菜单类型为菜单或者目录时，才需要传
     */
    private String icon;

    /**
     * 组件路径,仅菜单类型为菜单时，才需要传
     */
    private String component;

    /**
     * 组件名
     */
    private String componentName;

    /**
     * 菜单状态
     */
    private CommonStatusEnum status;

    /**
     * 是否可见
     */
    private Boolean visible;

    /**
     * 是否缓存
     */
    private Boolean keepAlive;

    /**
     * 是否总是显示
     */
    private Boolean alwaysShow;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private ZonedDateTime createTime;
}
