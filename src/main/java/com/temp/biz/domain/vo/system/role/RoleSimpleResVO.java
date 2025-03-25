package com.temp.biz.domain.vo.system.role;

import lombok.Data;

/**
 * 管理后台 - 精简角色信息 Response VO
 *
 * @author Hollis
 * @since 2024/06/13 下午2:52
 */
@Data
public class RoleSimpleResVO {

    /**
     * 角色ID
     */
    private Long id;

    /**
     * 角色Code
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;
}
