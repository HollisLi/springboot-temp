package com.temp.biz.domain.vo.system.user;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * 管理后台 - 赋予用户角色 Request VO
 *
 * @author Hollis
 * @since 2024-06-21 10:39
 */
@Data
public class UserAssignRoleReqVO {

    /**
     * 用户编号
     */
    @NotNull(message = "用户编号不能为空")
    private Long userId;

    /**
     * 角色编号列表
     */
    private Set<Long> roleIds;
}
