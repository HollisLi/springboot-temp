package com.temp.biz.service.system;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import com.temp.biz.domain.dto.system.auth.UserInfoDTO;
import com.temp.biz.domain.vo.system.auth.AuthPermissionInfoRespVO;
import com.temp.biz.domain.vo.system.auth.ChangePasswordReqVO;
import com.temp.biz.domain.vo.system.auth.LoginReqVO;
import com.temp.biz.domain.vo.system.menu.MenuVO;
import com.temp.biz.entity.system.SystemEnterprise;
import com.temp.biz.entity.system.SystemLoginLog;
import com.temp.biz.entity.system.SystemMenu;
import com.temp.biz.entity.system.SystemUsers;
import com.temp.biz.mapstruct.system.AuthConvert;
import com.temp.biz.mapstruct.system.SystemUserConvert;
import com.temp.common.constants.SessionKeyConstant;
import com.temp.common.enums.CommonStatusEnum;
import com.temp.common.enums.LoginLogTypeEnum;
import com.temp.common.enums.LoginResultEnum;
import com.temp.common.enums.MenuTypeEnum;
import com.temp.common.utils.ServletUtils;
import com.temp.framework.exception.BusinessException;
import com.temp.framework.security.TokenHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.temp.biz.entity.system.SystemMenu.ID_ROOT;

/**
 * 身份验证 Service
 *
 * @author Hollis
 * @since 2024-07-12 15:57
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class AuthService {

    private final SystemUsersService systemUsersService;
    private final SystemUserRoleService systemUserRoleService;
    private final SystemLoginLogService systemLoginLogService;
    private final SystemRoleMenuService systemRoleMenuService;
    private final SystemEnterpriseService systemEnterpriseService;


    /**
     * 用户登录
     *
     * @param req 请求
     * @return {@link String}
     */
    public SaTokenInfo login(LoginReqVO req) {
        // 登录验证, 1.账号密码是否正确, 账号是否被禁用. 2. 账号所属企业是否存在, 是否被禁用
        SystemUsers systemUser = authenticate(req);
        StpUtil.login(systemUser.getId());

        AuthPermissionInfoRespVO permissionInfo = getPermissionInfo(systemUser.getId());
        UserInfoDTO userinfo = new UserInfoDTO();
        userinfo.setUser(systemUser);
        userinfo.setRoles(permissionInfo.getRoles());
        userinfo.setPosts(permissionInfo.getPosts());
        userinfo.setPermissions(permissionInfo.getPermissions());

        StpUtil.getSession().set(SessionKeyConstant.USER_INFO, userinfo);
        return StpUtil.getTokenInfo();
    }

    /**
     * 登录验证账号密码
     * <p>
     * 这里校验是为了维护登录日志
     *
     * @param loginReq 登录信息
     */
    public SystemUsers authenticate(LoginReqVO loginReq) {
        String username = loginReq.getUsername();

        SystemUsers user = systemUsersService.getByUsername(loginReq.getUsername());
        if (user == null) {
            createLoginLog(null, username, LoginResultEnum.BAD_CREDENTIALS);
            throw new BusinessException("登录失败，用户名或密码错误");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(loginReq.getPassword(), user.getPassword())) {
            createLoginLog(user.getId(), username, LoginResultEnum.BAD_CREDENTIALS);
            throw new BusinessException("登录失败，用户名或密码错误");
        }
        if (CommonStatusEnum.DISABLE.equals(user.getStatus())) {
            createLoginLog(user.getId(), username, LoginResultEnum.USER_DISABLED);
            throw new BusinessException("登录失败，账号已被禁用");
        }

        SystemEnterprise enterprise = systemEnterpriseService.getById(user.getEnterpriseId());
        if (enterprise == null || enterprise.getDeleteFlag()) {
            createLoginLog(user.getId(), username, LoginResultEnum.BAD_ENTERPRISE);
            throw new BusinessException("登录失败，账号所属企业不存在");
        }
        if (CommonStatusEnum.DISABLE.equals(enterprise.getStatus())) {
            createLoginLog(user.getId(), username, LoginResultEnum.USER_ENTERPRISE_DISABLED);
            throw new BusinessException("登录失败，账号所属企业已禁用");
        }

        createLoginLog(user.getId(), username, LoginResultEnum.SUCCESS);
        return user;
    }

    /**
     * 创建登录日志
     *
     * @param userId      用户ID
     * @param username    用户名
     * @param loginResult 登录结果
     */
    private void createLoginLog(Long userId, String username, LoginResultEnum loginResult) {
        SystemLoginLog reqDTO = new SystemLoginLog();
        reqDTO.setLogType(LoginLogTypeEnum.LOGIN_USERNAME);
        reqDTO.setUserId(userId);
        reqDTO.setUsername(username);
        reqDTO.setResult(loginResult);
        reqDTO.setUserIp(ServletUtils.getClientIP());
        reqDTO.setUserAgent(ServletUtils.getUserAgent());
        systemLoginLogService.save(reqDTO);

        if (userId != null && LoginResultEnum.SUCCESS.equals(loginResult)) {
            // 更新最后登录时间 和 登录IP
            systemUsersService.updateUserLogin(userId, ServletUtils.getClientIP());
        }
    }

    /**
     * 注销
     */
    public void logout() {
        createLogoutLog(TokenHolder.getUserId(), TokenHolder.getUsername(), LoginLogTypeEnum.LOGOUT_SELF, LoginResultEnum.SUCCESS);
        StpUtil.logout();
    }

    /**
     * 强制注销
     *
     * @param userId 用户ID
     */
    public void forcedLogout(Long userId) {
        SystemUsers user = Optional.ofNullable(systemUsersService.getById(userId))
                .orElseThrow(() -> new BusinessException("用户不存在"));
        createLogoutLog(user.getId(), user.getUsername(), LoginLogTypeEnum.LOGOUT_FORCED, LoginResultEnum.SUCCESS);
        StpUtil.kickout(userId);
    }

    /**
     * 创建登出日志
     *
     * @param userId      用户ID
     * @param username    用户名
     * @param logType     登录类型
     * @param loginResult 登录结果
     */
    private void createLogoutLog(Long userId, String username, LoginLogTypeEnum logType, LoginResultEnum loginResult) {
        // 插入登录日志
        SystemLoginLog reqDTO = new SystemLoginLog();
        reqDTO.setLogType(logType);
        reqDTO.setUserId(userId);
        reqDTO.setUsername(username);
        reqDTO.setResult(loginResult);
        reqDTO.setUserIp(ServletUtils.getClientIP());
        reqDTO.setUserAgent(ServletUtils.getUserAgent());
        systemLoginLogService.save(reqDTO);
    }

    /**
     * 更改密码
     *
     * @param req 请求
     */
    public void changePassword(ChangePasswordReqVO req) {
        // 获取用户信息
        SystemUsers users = Optional.ofNullable(systemUsersService.getById(req.getUserId()))
                .orElseThrow(() -> new BusinessException("用户不存在"));

        // 修改密码
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        users.setPassword(passwordEncoder.encode(req.getNewPassword()));
        systemUsersService.updateById(users);

        // 注销登录
        logout();
    }

    public AuthPermissionInfoRespVO getPermissionInfo() {
        return getPermissionInfo(TokenHolder.getUserId());
    }

    /**
     * 获取登录用户的权限信息
     *
     * @return {@link AuthPermissionInfoRespVO }
     */
    public AuthPermissionInfoRespVO getPermissionInfo(Long userId) {
        // 获取用户信息
        SystemUsers users = Optional.ofNullable(systemUsersService.getById(userId))
                .orElseThrow(() -> new BusinessException("用户不存在"));

        AuthPermissionInfoRespVO res = new AuthPermissionInfoRespVO();
        res.setUser(SystemUserConvert.INSTANCE.toUserVO(users));

        // 角色
        Set<String> roleCodes = systemUserRoleService.listRoleCodeByUserId(userId);
        if (CollectionUtils.isEmpty(roleCodes)) {
            res.setRoles(Collections.emptySet());
            res.setPermissions(Collections.emptySet());
            res.setMenus(Collections.emptyList());
            return res;
        }

        // 菜单
        Set<SystemMenu> systemMenus = systemRoleMenuService.listMenuByUserId(userId);
        if (CollectionUtils.isEmpty(systemMenus)) {
            res.setRoles(roleCodes);
            res.setPermissions(Collections.emptySet());
            res.setMenus(Collections.emptyList());
            return res;
        }

        res.setRoles(roleCodes);
        res.setPermissions(systemMenus.stream().map(SystemMenu::getPermission).collect(Collectors.toSet()));
        res.setMenus(buildMenuTree(new ArrayList<>(systemMenus)));
        return res;
    }


    /**
     * 将菜单列表，构建成菜单树
     *
     * @param menuList 菜单列表
     * @return 菜单树
     */
    List<MenuVO> buildMenuTree(List<SystemMenu> menuList) {
        if (CollUtil.isEmpty(menuList)) {
            return Collections.emptyList();
        }
        // 移除按钮
        menuList.removeIf(menu -> MenuTypeEnum.BUTTON.equals(menu.getType()));
        menuList.sort(Comparator.comparing(SystemMenu::getSort));

        // 构建菜单树
        // 使用 LinkedHashMap 的原因，是为了排序 。实际也可以用 Stream API ，就是太丑了。
        Map<Long, MenuVO> treeNodeMap = new LinkedHashMap<>();
        menuList.forEach(menu -> treeNodeMap.put(menu.getId(), AuthConvert.INSTANCE.toMenuVO(menu)));
        // 处理父子关系
        treeNodeMap.values().stream().filter(node -> !node.getParentId().equals(ID_ROOT)).forEach(childNode -> {
            // 获得父节点
            MenuVO parentNode = treeNodeMap.get(childNode.getParentId());
            if (parentNode == null) {
                log.error("[buildRouterTree][resource({}) 找不到父资源({})]", childNode.getId(), childNode.getParentId());
                return;
            }
            // 将自己添加到父节点中
            if (parentNode.getChildren() == null) {
                parentNode.setChildren(new ArrayList<>());
            }
            parentNode.getChildren().add(childNode);
        });
        // 获得到所有的根节点
        List<MenuVO> menus = filterList(treeNodeMap.values(), node -> ID_ROOT.equals(node.getParentId()));
        // 排序，保证菜单的有序性
        menus.sort(Comparator.comparing(MenuVO::getSort));
        return menus;
    }

    public static List<MenuVO> filterList(Collection<MenuVO> from, Predicate<MenuVO> predicate) {
        if (CollUtil.isEmpty(from)) {
            return new ArrayList<>();
        }
        return from.stream().filter(predicate).collect(Collectors.toList());
    }
}
