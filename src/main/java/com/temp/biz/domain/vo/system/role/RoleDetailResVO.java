package com.temp.biz.domain.vo.system.role;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 * 管理后台 - 角色信息详情 Response VO
 *
 * @author Hollis
 * @since 2024/06/13 下午2:36
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleDetailResVO extends RoleResVO {

    /**
     * 角色菜单ID
     */
    Set<Long> menuIds;
}
