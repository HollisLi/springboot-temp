package com.temp.biz.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.temp.biz.domain.vo.system.role.RoleDetailResVO;
import com.temp.biz.domain.vo.system.role.RolePageReqVO;
import com.temp.biz.domain.vo.system.role.RoleResVO;
import com.temp.biz.domain.vo.system.role.RoleSimpleResVO;
import com.temp.biz.entity.system.SystemRole;
import com.temp.biz.entity.system.SystemUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统角色 Mapper
 *
 * @author Hollis
 * @since 2024-04-16 16:27
 */
public interface SystemRoleMapper extends BaseMapper<SystemRole> {

    /**
     * 根据角色名称查询
     *
     * @param roleName 角色名称
     * @return {@link SystemUserRole } 角色信息
     */
    SystemRole getByName(@Param("roleName") String roleName);

    /**
     * 根据角色代码查询
     *
     * @param roleCode 角色代码
     * @return {@link SystemUserRole } 角色信息
     */
    SystemRole getByCode(@Param("roleCode") String roleCode);

    /**
     * 获得角色分页
     *
     * @param request 分页筛选条件
     * @return {@link Page }<{@link RoleResVO }> 角色分页
     */
    Page<RoleResVO> page(Page<RoleResVO> page, @Param("request") RolePageReqVO request);

    /**
     * 根据角色ID查询
     *
     * @param roleId 角色ID
     * @return {@link RoleResVO } 角色信息
     */
    RoleResVO getById(@Param("roleId") Long roleId);

    /**
     * 获取角色精简信息列表
     * <p>
     * 只包含被开启的角色，主要用于前端的下拉选项
     *
     * @return {@link RoleDetailResVO} 角色详情
     */
    List<RoleSimpleResVO> listSimple();

}