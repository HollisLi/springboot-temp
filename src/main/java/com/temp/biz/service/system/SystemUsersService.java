package com.temp.biz.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.temp.biz.domain.vo.system.dept.DeptResVO;
import com.temp.biz.domain.vo.system.online.OnlinePageReqVO;
import com.temp.biz.domain.vo.system.online.OnlineResVO;
import com.temp.biz.domain.vo.system.post.PostResVO;
import com.temp.biz.domain.vo.system.user.*;
import com.temp.biz.entity.system.*;
import com.temp.biz.mapper.system.SystemUsersMapper;
import com.temp.biz.mapstruct.system.*;
import com.temp.common.constants.ErrorMsgConstant;
import com.temp.framework.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 用户 Service
 *
 * @author Hollis
 * @since 2024/04/11 下午4:24
 */
@Service
@RequiredArgsConstructor
public class SystemUsersService extends ServiceImpl<SystemUsersMapper, SystemUsers> {

    private final SystemDeptService systemDeptService;
    private final SystemUserRoleService systemUserRoleService;
    private final SystemUserPostService systemUserPostService;
    private final SystemEnterpriseService systemEnterpriseService;


    /**
     * 按用户名查询用户
     *
     * @param username 用户名
     * @return {@link SystemUsers}
     */
    public SystemUsers getByUsername(String username) {
        return baseMapper.getByUsername(username);
    }

    /**
     * 统计企业下的用户数量
     *
     * @param enterpriseId 企业ID
     * @return {@link Integer } 用户数量
     */
    public Integer countByEnterpriseId(Long enterpriseId) {
        return baseMapper.countByEnterpriseId(enterpriseId);
    }

    /**
     * 统计部门下的用户数量
     *
     * @param deptId 部门ID
     * @return {@link Integer } 用户数量
     */
    public Integer countByDeptId(Long deptId) {
        return baseMapper.countByDeptId(deptId);
    }

    /**
     * 修改用户登录信息
     *
     * @param userId   用户ID
     * @param clientIP 客户端ip
     */
    public void updateUserLogin(Long userId, String clientIP) {
        SystemUsers users = new SystemUsers();
        users.setId(userId);
        users.setLoginIp(clientIP);
        users.setLoginDate(ZonedDateTime.now());
        baseMapper.updateById(users);
    }

    /**
     * 新增用户
     *
     * @param request 用户信息
     * @return {@link Long } 用户ID
     */
    @Transactional(rollbackFor = Exception.class)
    public Long insert(UserSaveReqVO request) {
        // 验证用户登录账号唯一
        verifyUserNameUnique(null, request.getUsername(), request.getEnterpriseId());

        // 新增用户
        SystemUsers user = SystemUserConvert.INSTANCE.toEntity(request);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        baseMapper.insert(user);

        // 新增用户岗位
        if (CollectionUtils.isNotEmpty(request.getPostIds())) {
            insertUserPosts(user.getId(), request.getPostIds());
        }
        return user.getId();
    }

    /**
     * 修改用户
     *
     * @param request 用户信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void update(UserSaveReqVO request) {
        // 验证用户是否存在
        validateUserExists(request.getId());
        // 验证用户登录账号唯一
        verifyUserNameUnique(request.getId(), request.getUsername(), request.getEnterpriseId());

        // 禁止修改密码
        request.setPassword(null);

        // 更新用户
        SystemUsers user = SystemUserConvert.INSTANCE.toEntity(request);
        baseMapper.updateById(user);

        // 更新用户岗位
        systemUserPostService.deleteByUserId(user.getId());
        if (CollectionUtils.isNotEmpty(request.getPostIds())) {
            insertUserPosts(user.getId(), request.getPostIds());
        }
    }

    /**
     * 验证用户是否存在, 不存在抛出异常
     *
     * @param id 岗位ID
     */
    private void validateUserExists(Long id) {
        if (id == null) {
            return;
        }
        if (baseMapper.selectById(id) == null) {
            throw new BusinessException(ErrorMsgConstant.USER_NOT_FOUND);
        }
    }

    /**
     * 验证用户名称唯一
     *
     * @param userId       用户ID
     * @param username     用户名
     * @param enterpriseId 企业ID
     */
    private void verifyUserNameUnique(Long userId, String username, Long enterpriseId) {
        SystemUsers user = baseMapper.getByUsernameAndEnterpriseId(username, enterpriseId);
        if (user == null) {
            return;
        }
        if (userId == null || ObjectUtils.notEqual(userId, user.getId())) {
            throw new BusinessException(ErrorMsgConstant.USER_NAME_DUPLICATE);
        }
    }

    /**
     * 新增用户岗位
     *
     * @param userId  用户ID
     * @param postIds 岗位ids
     */
    private void insertUserPosts(Long userId, List<Long> postIds) {
        Function<Long, SystemUserPost> buildUserPost = postId -> {
            SystemUserPost userPost = new SystemUserPost();
            userPost.setUserId(userId);
            userPost.setPostId(postId);
            return userPost;
        };
        List<SystemUserPost> userPosts = postIds.stream()
                .map(buildUserPost)
                .collect(Collectors.toList());
        systemUserPostService.saveBatch(userPosts);
    }

    /**
     * 删除用户
     *
     * @param userId 用户ID
     */
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long userId) {
        // 验证用户是否存在
        validateUserExists(userId);
        // 删除用户岗位
        systemUserPostService.deleteByUserId(userId);
        // 删除用户角色
        systemUserRoleService.deleteByUserId(userId);
        // 删除用户
        baseMapper.deleteById(userId);
    }

    /**
     * 更新用户密码
     *
     * @param request 要求
     */
    public void updatePassword(UserUpdatePasswordReqVO request) {
        SystemUsers user = new SystemUsers();
        user.setId(request.getId());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        baseMapper.updateById(user);
    }

    /**
     * 分页查询用户信息
     *
     * @param pageReqVO 分页和筛选条件
     * @return {@link Page }<{@link UserResVO }> 用户信息
     */
    public Page<UserResVO> page(UserPageReqVO pageReqVO) {
        if (null != pageReqVO.getDeptId()) {
            List<Long> deptChildIds = systemDeptService.listChildIdByDeptId(pageReqVO.getDeptId());
            deptChildIds.add(pageReqVO.getDeptId());
            pageReqVO.setDeptChildIds(deptChildIds);
        }
        Page<UserResVO> page = new Page<>(pageReqVO.getPageNum(), pageReqVO.getPageSize());
        return baseMapper.page(page, pageReqVO);
    }

    /**
     * 获取用户详情
     *
     * @param userId 用户ID
     * @return {@link UserResVO } 用户信息
     */
    public UserResVO getDetailById(Long userId) {
        UserResVO user = baseMapper.getDetailById(userId);
        if (user == null) {
            return null;
        }

        // 查询用户岗位
        List<SystemPost> userPost = systemUserPostService.getPostByUserId(userId);
        if (CollectionUtils.isNotEmpty(userPost)) {
            user.setPostIds(userPost.stream().map(SystemPost::getId).collect(Collectors.toList()));
        }

        // 查询用户角色
        user.setRoleIds(systemUserRoleService.listRoleIdByUserId(userId));
        return user;
    }

    /**
     * 获得登录用户信息
     *
     * @param userId 用户ID
     * @return {@link UserProfileRespVO }
     */
    public UserProfileRespVO getUserProfile(Long userId) {
        // 获得用户基本信息
        SystemUsers userInfo = Optional
                .ofNullable(baseMapper.selectById(userId))
                .orElseThrow(() -> new BusinessException("未查询到用户信息"));
        UserProfileRespVO res = SystemUserConvert.INSTANCE.toUserProfileRespVO(userInfo);

        // 获得企业信息
        SystemEnterprise enterprise = systemEnterpriseService.getById(userInfo.getEnterpriseId());
        res.setEnterprise(SystemEnterpriseConvert.INSTANCE.toEnterpriseSimpleResVO(enterprise));

        // 获得部门信息
        DeptResVO dept = systemDeptService.getDetailById(userInfo.getDeptId());
        res.setDept(SystemDeptConvert.INSTANCE.toDeptSimpleResVO(dept));

        // 获得岗位信息
        List<SystemPost> posts = systemUserPostService.getPostByUserId(userId);
        res.setPosts(SystemPostConvert.INSTANCE.toSystemPostList(posts));

        // 获得角色信息
        Set<SystemRole> systemRoles = systemUserRoleService.listRoleByUserId(userId);
        res.setRoles(SystemRoleConvert.INSTANCE.toRoleSimpleResVOList(systemRoles));
        return res;
    }

    /**
     * 只包含被开启的用户，主要用于前端的下拉选项
     *
     * @return {@link List }<{@link UserSimpleRespVO }>
     */
    public List<UserSimpleRespVO> getSimpleUserList() {
        return baseMapper.getSimpleUserList();
    }

    /**
     * 获得在线用户分页列表
     *
     * @param request 筛选条件
     * @return {@link Page }<{@link PostResVO }> 在线用户列表
     */
    public Page<OnlineResVO> pageOnline(OnlinePageReqVO request) {
        Page<OnlineResVO> page = new Page<>(request.getPageNum(), request.getPageSize());
        return baseMapper.pageOnline(page, request);
    }

    /**
     * 获得用户拥有的角色编号列表
     *
     * @param userId 用户ID
     * @return {@link Set }<{@link Long }> 角色id集合
     */
    public Set<Long> listUserRoles(Long userId) {
        return systemUserRoleService.listRoleIdByUserId(userId);
    }

    /**
     * 赋予用户角色
     *
     * @param req 请求
     */
    public void assignUserRole(UserAssignRoleReqVO req) {
        systemUserRoleService.deleteByUserId(req.getUserId());
        if (CollectionUtils.isEmpty(req.getRoleIds())) {
            return;
        }

        Function<Long, SystemUserRole> buildUserRole = roleId -> {
            SystemUserRole userRole = new SystemUserRole();
            userRole.setUserId(req.getUserId());
            userRole.setRoleId(roleId);
            return userRole;
        };
        List<SystemUserRole> userRoles = req.getRoleIds().stream()
                .map(buildUserRole)
                .collect(Collectors.toList());
        systemUserRoleService.saveBatch(userRoles);
    }
}
