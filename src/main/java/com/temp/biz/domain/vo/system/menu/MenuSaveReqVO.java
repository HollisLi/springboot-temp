package com.temp.biz.domain.vo.system.menu;

import com.temp.common.enums.CommonStatusEnum;
import com.temp.common.enums.MenuTypeEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 管理后台 - 菜单创建/修改 Request VO
 *
 * @author Hollis
 * @since 2024-06-13 15:01
 */
@Data
public class MenuSaveReqVO {

    /**
     * 菜单ID
     */
    private Long id;

    /**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称不能为空")
    @Size(max = 50, message = "菜单名称长度不能超过50个字符")
    private String name;

    /**
     * 权限标识,仅菜单类型为按钮时，才需要传递
     * e.g: sys:menu:add
     */
    @Size(max = 100)
    private String permission;

    /**
     * 菜单类型
     */
    @NotNull(message = "菜单类型不能为空")
    private MenuTypeEnum type;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 父菜单 ID
     */
    @NotNull(message = "父菜单 ID 不能为空")
    private Long parentId;

    /**
     * 路由地址,仅菜单类型为菜单或者目录时，才需要传
     */
    @Size(max = 200, message = "路由地址不能超过200个字符")
    private String path;

    /**
     * 菜单图标,仅菜单类型为菜单或者目录时，才需要传
     */
    @Size(max = 100, message = "菜单图标不能超过100个字符")
    private String icon;

    /**
     * 组件路径,仅菜单类型为菜单时，才需要传
     */
    @Size(max = 255, message = "组件路径不能超过255个字符")
    private String component;

    /**
     * 组件名称
     */
    @Size(max = 255, message = "组件名称不能超过255个字符")
    private String componentName;

    /**
     * 菜单状态
     */
    @NotNull(message = "状态不能为空")
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

}
