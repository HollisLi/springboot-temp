package com.temp.biz.entity.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.temp.biz.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色和菜单关联表
 *
 * @author Hollis
 * @since 2024-04-16 18:12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "system_role_menu")
public class SystemRoleMenu extends BaseEntity {

    /**
     * 自增编号
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 角色ID
     */
    @TableField(value = "role_id")
    private Long roleId;

    /**
     * 菜单ID
     */
    @TableField(value = "menu_id")
    private Long menuId;
}