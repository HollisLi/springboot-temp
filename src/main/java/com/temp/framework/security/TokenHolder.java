package com.temp.framework.security;

import cn.dev33.satoken.stp.StpUtil;
import com.temp.biz.domain.dto.system.auth.UserInfoDTO;
import com.temp.biz.entity.system.SystemUsers;
import com.temp.common.constants.SessionKeyConstant;

import java.util.Set;

/**
 * 获取 Token 中的用户信息
 *
 * @author Hollis
 * @since 2023/09/07 20:30
 */
public class TokenHolder {

    public static final String REQUEST_ID = "RequestId";

    /**
     * 获取 用户ID
     *
     * @return {@link String}
     */
    public static Long getUserId() {
        return getLoginUser().getUser().getId();
    }

    /**
     * 获取 登录账号
     *
     * @return {@link String}
     */
    public static String getUsername() {
        return getLoginUser().getUser().getUsername();
    }

    /**
     * 获取 用户名称
     *
     * @return {@link String}
     */
    public static String getNickName() {
        return getLoginUser().getUser().getNickname();
    }

    /**
     * 获取 用户 邮箱
     *
     * @return {@link String}
     */
    public static String getEmail() {
        return getLoginUser().getUser().getEmail();
    }

    /**
     * 获取 用户 手机号
     * e.g: 13000000001
     *
     * @return {@link String}
     */
    public static String getMobile() {
        return getLoginUser().getUser().getMobile();
    }

    /**
     * 获取 用户企业ID
     *
     * @return {@link String}
     */
    public static Long getEnterpriseId() {
        return getLoginUser().getUser().getEnterpriseId();
    }

    /**
     * 获取 用户部门ID
     *
     * @return {@link String}
     */
    public static Long getDeptId() {
        return getLoginUser().getUser().getDeptId();
    }

    /**
     * 获取 用户角色列表
     *
     * @return {@link String}
     */
    public static Set<String> getRoles() {
        return getLoginUser().getRoles();
    }

    /**
     * 获取 用户岗位列表
     *
     * @return {@link String}
     */
    public static Set<String> getPosts() {
        return getLoginUser().getPosts();
    }

    /**
     * 获取 用户操作权限列表
     *
     * @return {@link String}
     */
    public static Set<String> getPermissions() {
        return getLoginUser().getPermissions();
    }

    /**
     * 获取用户详细信息
     */
    public static UserInfoDTO getLoginUser() {
        UserInfoDTO userInfo = (UserInfoDTO) StpUtil.getSession().get(SessionKeyConstant.USER_INFO);
        return userInfo == null ? buildUserInfo() : userInfo;
    }

    private static UserInfoDTO buildUserInfo() {
        SystemUsers user = new SystemUsers();
        user.setId(0L);
        user.setUsername("System");
        //user.setPassword();
        user.setNickname("System");
        //user.setRemark();
        user.setEnterpriseId(0L);
        user.setDeptId(0L);
        //user.setEmail();
        //user.setMobile();
        //user.setSex();
        //user.setAvatar();
        //user.setStatus();
        //user.setLoginIp();
        //user.setLoginDate();
        //user.setCreateUser();
        //user.setCreateUserName();
        //user.setCreateTime();
        //user.setUpdateUser();
        //user.setUpdateTime();
        //user.setDeleteFlag();

        UserInfoDTO userInfo = new UserInfoDTO();
        userInfo.setUser(user);
        userInfo.setRoles(null);
        userInfo.setPosts(null);
        userInfo.setPermissions(null);
        return userInfo;
    }
}
