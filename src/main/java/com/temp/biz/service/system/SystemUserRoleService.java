package com.temp.biz.service.system;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.temp.biz.entity.system.SystemRole;
import com.temp.biz.entity.system.SystemUserRole;
import com.temp.biz.mapper.system.SystemUserRoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 系统用户角色 Service
 *
 * @author Hollis
 * @since 2024-04-16 16:30
 */
@Service
public class SystemUserRoleService extends ServiceImpl<SystemUserRoleMapper, SystemUserRole> {

    /**
     * 查询用户角色Id
     *
     * @param userId 用户ID
     * @return {@link List }<{@link Long }>
     */
    public Set<Long> listRoleIdByUserId(Long userId) {
        return baseMapper.listRoleIdByUserId(userId);
    }

    /**
     * 获取用户所有角色
     *
     * @param userId 用户ID
     * @return {@link Set}<{@link String}> 角色Code
     */
    public Set<String> listRoleCodeByUserId(Long userId) {
        return baseMapper.listRoleCodeByUserId(userId);
    }

    /**
     * 获取用户所有角色
     *
     * @param userId 用户ID
     * @return {@link Set}<{@link SystemRole}> 角色Code
     */
    public Set<SystemRole> listRoleByUserId(Long userId) {
        return baseMapper.listRoleByUserId(userId);
    }

    /**
     * 删除用户所有角色
     *
     * @param userId 用户ID
     */
    public void deleteByUserId(Long userId) {
        baseMapper.deleteByUserId(userId);
    }
}
