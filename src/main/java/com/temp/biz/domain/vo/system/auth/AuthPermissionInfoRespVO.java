package com.temp.biz.domain.vo.system.auth;

import lombok.Data;

import java.util.Set;

/**
 * 管理后台 - 登录用户的权限信息 Response VO，额外包括用户信息和角色列表
 *
 * @author Hollis
 * @since 2024-06-18 15:37
 */
@Data
public class AuthPermissionInfoRespVO {

    /**
     * 用户信息
     */
    //private UserVO user;

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

    /**
     * 菜单树
     */
    //private List<MenuVO> menus;

}
