package com.temp.biz.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.temp.biz.entity.system.SystemRole;
import com.temp.biz.entity.system.SystemUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * 系统用户角色 Mapper
 *
 * @author Hollis
 * @since 2024-04-16 16:30
 */
public interface SystemUserRoleMapper extends BaseMapper<SystemUserRole> {

    /**
     * 通过用户ID获取角色Code列表
     *
     * @param userId 用户ID
     * @return {@link Set}<{@link String}>
     */
    Set<String> listRoleCodeByUserId(@Param("userId") Long userId);

    /**
     * 删除用户所有角色
     *
     * @param userId 用户ID
     */
    void deleteByUserId(@Param("userId") Long userId);

    /**
     * 查询用户角色Id集合
     *
     * @param userId 用户ID
     * @return {@link Set }<{@link Long }>
     */
    Set<Long> listRoleIdByUserId(@Param("userId") Long userId);

    /**
     * 查询用户角色集合
     *
     * @param userId 用户ID
     * @return {@link Set }<{@link Long }>
     */
    Set<SystemRole> listRoleByUserId(@Param("userId") Long userId);

}