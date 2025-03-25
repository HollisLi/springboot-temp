package com.temp.biz.domain.vo.system.role;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * 管理后台 - 赋予角色菜单 Request VO
 *
 * @author Hollis
 * @since 2024-06-20 18:13
 */
@Data
public class AssignRoleMenuReqVO {

    /**
     * 角色编号
     */
    @NotNull(message = "角色编号不能为空")
    private Long roleId;

    /**
     * 菜单编号列表
     */
    private Set<Long> menuIds;

}
