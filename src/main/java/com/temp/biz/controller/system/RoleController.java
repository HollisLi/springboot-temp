package com.temp.biz.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.temp.biz.domain.vo.system.role.*;
import com.temp.biz.service.system.SystemRoleService;
import com.temp.framework.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * 管理后台 - 角色
 *
 * @author Hollis
 * @since 2024/06/12 下午5:53
 */
@Validated
@RestController
@RequestMapping("/system/role")
@RequiredArgsConstructor
public class RoleController {

    private final SystemRoleService systemRoleService;

    /**
     * 创建角色
     *
     * @param request 角色信息
     * @return {@link Long } 角色id
     */
    @PostMapping("/create")
    public Long create(@RequestBody @Valid RoleSaveReqVO request) {
        return systemRoleService.insert(request);
    }

    /**
     * 修改角色
     *
     * @param request 角色信息
     */
    @PutMapping("/update")
    public void update(@RequestBody @Valid RoleSaveReqVO request) {
        if (request.getId() == null) {
            throw new BusinessException("角色Id不能为空");
        }
        systemRoleService.update(request);
    }

    /**
     * 删除角色
     *
     * @param id 角色Id
     */
    @DeleteMapping("/delete")
    public void delete(@RequestParam("id") Long id) {
        systemRoleService.delete(id);
    }

    /**
     * 获得角色分页
     *
     * @param request 分页筛选条件
     * @return {@link Page }<{@link RoleResVO }> 角色分页
     */
    @PostMapping("/page")
    public Page<RoleResVO> page(@RequestBody @Valid RolePageReqVO request) {
        return systemRoleService.page(request);
    }

    /**
     * 获得角色详情
     *
     * @param id 角色Id
     * @return {@link RoleDetailResVO} 角色详情
     */
    @GetMapping("/get")
    public RoleDetailResVO get(@RequestParam("id") Long id) {
        return systemRoleService.getDetailById(id);
    }

    /**
     * 获取角色精简信息列表
     * <p>
     * 只包含被开启的角色，主要用于前端的下拉选项
     *
     * @return {@link RoleDetailResVO} 角色详情
     */
    @GetMapping("/simple-list")
    public List<RoleSimpleResVO> simpleList() {
        return systemRoleService.simpleList();
    }

    /**
     * 获取角色菜单权限id集合
     *
     * @param roleId 角色ID
     * @return {@link RoleDetailResVO} 角色详情
     */
    @GetMapping("/list-role-menus")
    public Set<Long> listRoleMenus(@RequestParam("roleId") Long roleId) {
        return systemRoleService.listRoleMenus(roleId);
    }

    /**
     * 赋予角色菜单
     *
     * @param req 角色id 和 菜单id集合
     */
    @PostMapping("/assign-role-menu")
    public void assignRoleMenu(@RequestBody AssignRoleMenuReqVO req) {
        systemRoleService.assignRoleMenu(req);
    }

}
