package com.temp.biz.domain.dto.system.auth;

import com.temp.biz.entity.system.SystemUsers;
import lombok.Data;

import java.util.Set;

/**
 * 登录用户信息 DTO
 *
 * @author Hollis
 * @since 2025/03/25 16:37
 */
@Data
public class UserInfoDTO {

    /**
     * 角色标识数组
     */
    private SystemUsers user;

    /**
     * 角色标识数组
     */
    private Set<String> roles;

    /**
     * 岗位标识数组
     */
    private Set<String> posts;

    /**
     * 操作权限数组
     */
    private Set<String> permissions;
}
