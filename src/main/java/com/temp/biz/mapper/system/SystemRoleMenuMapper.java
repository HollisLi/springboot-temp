package com.temp.biz.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.temp.biz.entity.system.SystemMenu;
import com.temp.biz.entity.system.SystemRoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 系统角色菜单 Mapper
 *
 * @author Hollis
 * @since 2024-04-16 18:13
 */
public interface SystemRoleMenuMapper extends BaseMapper<SystemRoleMenu> {

    /**
     * 获取用户ID关联的所有菜单权限
     *
     * @param userId 用户ID
     * @return {@link Set}<{@link String}> 菜单权限
     */
    Set<String> listPermissionByUserId(@Param("userId") Long userId);

    /**
     * 获取用户ID关联的所有菜单
     *
     * @param userId 用户ID
     * @return {@link Set}<{@link SystemMenu}>
     */
    Set<SystemMenu> listMenuByUserId(@Param("userId") Long userId);

    /**
     * 通过角色ID删除相关的角色菜单关联
     *
     * @param roleId 角色ID
     */
    void deleteByRoleId(@Param("roleId") Long roleId);

    /**
     * 通过角色Id获取菜单ID集合
     *
     * @param roleId 角色ID
     * @return {@link List }<{@link Long }> 菜单ID集合(无重复数据)
     */
    Set<Long> listMenuIdsByRoleId(@Param("roleId") Long roleId);

    /**
     * 通过菜单id删除相关的角色菜单关联
     *
     * @param menuId 菜单ID
     */
    void deleteByMenuId(@Param("menuId") Long menuId);

    Set<SystemMenu> listAll();

}