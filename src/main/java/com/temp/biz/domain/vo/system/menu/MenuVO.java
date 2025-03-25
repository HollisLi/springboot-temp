package com.temp.biz.domain.vo.system.menu;


import lombok.Data;

import java.util.List;

/**
 * 管理后台 - 登录用户的菜单信息 Response VO
 *
 * @author Hollis
 * @since 2024-06-18 15:39
 */
@Data
public class MenuVO {

    /**
     * 菜单名称
     */
    private Long id;

    /**
     * 父菜单 ID
     */
    private Long parentId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 路由地址,仅菜单类型为菜单或者目录时，才需要传
     */
    private String path;

    /**
     * 组件路径,仅菜单类型为菜单时，才需要传
     */
    private String component;

    /**
     * 组件名
     */
    private String componentName;

    /**
     * 菜单图标,仅菜单类型为菜单或者目录时，才需要传
     */
    private String icon;

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
     * 菜单排序
     */
    private Integer sort;

    /**
     * 权限标识
     */
    private String permission;

    /**
     * 子路由
     */
    private List<MenuVO> children;

}