package com.temp.biz.service.system;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.temp.biz.entity.system.SystemMenu;
import com.temp.biz.entity.system.SystemRoleMenu;
import com.temp.biz.mapper.system.SystemRoleMenuMapper;
import com.temp.common.enums.RoleCodeEnum;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * 系统角色菜单 Service
 *
 * @author Hollis
 * @since 2024-04-16 18:13
 */
@Service
@RequiredArgsConstructor
public class SystemRoleMenuService extends ServiceImpl<SystemRoleMenuMapper, SystemRoleMenu> {

    private final SystemUserRoleService systemUserRoleService;

    /**
     * 获取用户所有菜单权限
     *
     * @param userId 用户ID
     * @return {@link Set}<{@link String}>
     */
    public Set<String> listPermissionByUserId(Long userId) {
        return baseMapper.listPermissionByUserId(userId);
    }

    /**
     * 获取用户所有菜单
     *
     * @param userId 用户ID
     * @return {@link Set}<{@link SystemMenu}>
     */
    public Set<SystemMenu> listMenuByUserId(Long userId) {
        Set<String> roles = systemUserRoleService.listRoleCodeByUserId(userId);
        if (CollectionUtils.isEmpty(roles)) {
            return Collections.emptySet();
        }

        // 如果是超级管理员，返回所有菜单
        if (roles.contains(RoleCodeEnum.SUPER_ADMIN.getCode())) {
            return baseMapper.listAll();
        }

        return baseMapper.listMenuByUserId(userId);
    }

    /**
     * 通过角色ID删除相关的角色菜单关联
     *
     * @param roleId 角色ID
     */
    public void deleteByRoleId(Long roleId) {
        baseMapper.deleteByRoleId(roleId);
    }

    /**
     * 通过角色Id获取菜单ID集合
     *
     * @param roleId 角色ID
     * @return {@link List }<{@link Long }> 菜单ID集合(无重复数据)
     */
    public Set<Long> listMenuIdsByRoleId(Long roleId) {
        return baseMapper.listMenuIdsByRoleId(roleId);
    }

    /**
     * 通过菜单id删除相关的角色菜单关联
     *
     * @param menuId 菜单ID
     */
    public void deleteByMenuId(Long menuId) {
        baseMapper.deleteByMenuId(menuId);
    }
}
