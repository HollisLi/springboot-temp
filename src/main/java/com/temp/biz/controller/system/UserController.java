package com.temp.biz.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.temp.biz.domain.vo.system.user.*;
import com.temp.biz.service.system.SystemUsersService;
import com.temp.framework.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * 管理后台 - 用户
 *
 * @author Hollis
 * @since 2024-04-11 15:43
 */
@Validated
@RestController
@RequestMapping("/system/user")
@RequiredArgsConstructor
public class UserController {

    private final SystemUsersService systemUsersService;

    /**
     * 新增用户信息
     */
    @PostMapping("/create")
    public Long create(@RequestBody @Valid UserSaveReqVO req) {
        if (StringUtils.isBlank(req.getPassword())) {
            throw new BusinessException("用户初始密码不能为空");
        }
        return systemUsersService.insert(req);
    }

    /**
     * 修改用户信息
     */
    @PutMapping("/update")
    public void update(@RequestBody @Valid UserSaveReqVO req) {
        if (req.getId() == null) {
            throw new BusinessException("用户编号不能为空");
        }
        systemUsersService.update(req);
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/delete")
    public void delete(@RequestParam("id") Long id) {
        systemUsersService.delete(id);
    }

    /**
     * 修改密码
     */
    @PutMapping("/update-password")
    public void updatePassword(@RequestBody @Valid UserUpdatePasswordReqVO request) {
        systemUsersService.updatePassword(request);
    }

    /**
     * 分页查询用户信息
     *
     * @param pageReqVO 分页和筛选条件
     * @return {@link Page }<{@link UserResVO }>
     */
    @PostMapping("/page")
    public Page<UserResVO> page(@RequestBody UserPageReqVO pageReqVO) {
        return systemUsersService.page(pageReqVO);
    }

    /**
     * 只包含被开启的用户，主要用于前端的下拉选项
     *
     * @return {@link List }<{@link UserSimpleRespVO }>
     */
    @GetMapping({"/list-all-simple", "/simple-list"})
    public List<UserSimpleRespVO> getSimpleUserList() {
        return systemUsersService.getSimpleUserList();
    }

    /**
     * 获取用户详情
     *
     * @param id 用户ID
     * @return {@link UserResVO } 用户信息
     */
    @GetMapping("/get")
    public UserResVO getDetailById(@RequestParam("id") Long id) {
        return systemUsersService.getDetailById(id);
    }

    /**
     * 获得用户拥有的角色编号列表
     *
     * @param userId 用户ID
     * @return {@link Set }<{@link Long }> 角色id集合
     */
    @GetMapping("/list-user-roles")
    public Set<Long> listUserRoles(@RequestParam("userId") Long userId) {
        return systemUsersService.listUserRoles(userId);
    }

    /**
     * 赋予用户角色
     */
    @PostMapping("/assign-user-role")
    public void assignUserRole(@RequestBody @Valid UserAssignRoleReqVO req) {
        systemUsersService.assignUserRole(req);
    }
}

