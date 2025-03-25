package com.temp.biz.service.system;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.temp.biz.domain.vo.system.role.*;
import com.temp.biz.entity.system.SystemRole;
import com.temp.biz.entity.system.SystemRoleMenu;
import com.temp.biz.mapper.system.SystemRoleMapper;
import com.temp.biz.mapstruct.system.SystemRoleConvert;
import com.temp.common.constants.ErrorMsgConstant;
import com.temp.common.enums.RoleCodeEnum;
import com.temp.framework.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 系统角色 Service
 *
 * @author Hollis
 * @since 2024-04-16 16:27
 */
@Service
@RequiredArgsConstructor
public class SystemRoleService extends ServiceImpl<SystemRoleMapper, SystemRole> {

    private final SystemRoleMenuService systemRoleMenuService;

    /**
     * 创建角色
     *
     * @param request 角色信息
     * @return {@link Long } 角色id
     */
    @Transactional(rollbackFor = Exception.class)
    public Long insert(RoleSaveReqVO request) {
        // 校验角色的唯一性
        verifyRoleUnique(null, request.getName(), request.getCode());
        // 新增角色
        SystemRole role = SystemRoleConvert.INSTANCE.toEntity(request);
        baseMapper.insert(role);
        return role.getId();
    }

    /**
     * 修改角色信息
     *
     * @param request 角色信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void update(RoleSaveReqVO request) {
        // 校验角色的唯一性
        verifyRoleUnique(request.getId(), request.getName(), request.getCode());
        // 修改角色
        SystemRole role = SystemRoleConvert.INSTANCE.toEntity(request);
        baseMapper.updateById(role);
    }

    /**
     * 验证角色是否唯一
     *
     * @param roleId   角色ID
     * @param roleName 角色名称
     * @param roleCode 角色Code
     */
    private void verifyRoleUnique(Long roleId, String roleName, String roleCode) {
        // 禁止创建 超级管理员角色
        if (RoleCodeEnum.isSuperAdmin(roleCode)) {
            throw new BusinessException(ErrorMsgConstant.ROLE_ADMIN_CODE_ERROR, RoleCodeEnum.SUPER_ADMIN.getCode());
        }

        // 角色名称不能重复
        SystemRole roleByName = baseMapper.getByName(roleName);
        if (roleByName != null) {
            if (roleId == null || ObjectUtil.notEqual(roleByName.getId(), roleId)) {
                throw new BusinessException(ErrorMsgConstant.ROLE_NAME_DUPLICATE, roleName);
            }
        }

        // 角色Code不能重复
        SystemRole roleByCode = baseMapper.getByCode(roleCode);
        if (roleByCode == null) {
            return;
        }
        if (roleId == null || ObjectUtil.notEqual(roleByCode.getId(), roleId)) {
            throw new BusinessException(ErrorMsgConstant.ROLE_CODE_DUPLICATE, roleCode);
        }
    }

    /**
     * 删除角色
     *
     * @param roleId 角色ID
     */
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long roleId) {
        // 验证角色是否存在
        validateRoleExists(roleId);
        // 删除角色权限关联
        systemRoleMenuService.deleteByRoleId(roleId);
        // 删除角色
        baseMapper.deleteById(roleId);
    }

    /**
     * 验证角色是否存在
     *
     * @param roleId 角色ID
     */
    private void validateRoleExists(Long roleId) {
        SystemRole systemRole = baseMapper.selectById(roleId);
        if (systemRole == null) {
            throw new BusinessException(ErrorMsgConstant.ROLE_NOT_EXISTS);
        }
        if (RoleCodeEnum.isSuperAdmin(systemRole.getCode())) {
            throw new BusinessException(ErrorMsgConstant.ROLE_SUPER_ADMIN_CANT_DELETE, RoleCodeEnum.SUPER_ADMIN.getCode());
        }
    }

    /**
     * 获得角色分页
     *
     * @param request 分页筛选条件
     * @return {@link Page }<{@link RoleResVO }> 角色分页
     */
    public Page<RoleResVO> page(RolePageReqVO request) {
        Page<RoleResVO> page = new Page<>(request.getPageNum(), request.getPageSize());
        return baseMapper.page(page, request);
    }

    /**
     * 获得角色详情
     *
     * @param roleId 角色ID
     * @return {@link RoleDetailResVO } 角色详情
     */
    public RoleDetailResVO getDetailById(Long roleId) {
        RoleResVO roleRes = baseMapper.getById(roleId);
        if (roleRes == null) {
            return null;
        }

        // 获得角色菜单
        RoleDetailResVO roleDetailRes = SystemRoleConvert.INSTANCE.toDetailRes(roleRes);
        roleDetailRes.setMenuIds(systemRoleMenuService.listMenuIdsByRoleId(roleId));
        return roleDetailRes;
    }

    /**
     * 获取角色精简信息列表
     * <p>
     * 只包含被开启的角色，主要用于前端的下拉选项
     *
     * @return {@link RoleDetailResVO} 角色详情
     */
    public List<RoleSimpleResVO> simpleList() {
        return baseMapper.listSimple();
    }

    /**
     * 获取角色菜单权限id
     *
     * @param roleId 角色ID
     * @return {@link RoleDetailResVO} 角色详情
     */
    public Set<Long> listRoleMenus(Long roleId) {
        return systemRoleMenuService.listMenuIdsByRoleId(roleId);
    }

    /**
     * 赋予角色菜单
     *
     * @param req 角色id 和 菜单id集合
     */
    public void assignRoleMenu(AssignRoleMenuReqVO req) {
        // 修改角色权限关联
        systemRoleMenuService.deleteByRoleId(req.getRoleId());
        if (CollectionUtils.isEmpty(req.getMenuIds())) {
            return;
        }

        // 新增角色权限关联
        Function<Long, SystemRoleMenu> buildSystemRoleMenu = menuId -> {
            SystemRoleMenu roleMenu = new SystemRoleMenu();
            roleMenu.setRoleId(req.getRoleId());
            roleMenu.setMenuId(menuId);
            return roleMenu;
        };
        List<SystemRoleMenu> systemRoleMenus = req.getMenuIds().stream()
                .map(buildSystemRoleMenu)
                .collect(Collectors.toList());
        systemRoleMenuService.saveBatch(systemRoleMenus);
    }
}
