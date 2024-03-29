package com.temp.security;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 获取 Token 中的用户信息
 *
 * @author Hollis
 * @since 2023/09/07 20:30
 */
public class TokenHolder {

    /**
     * 获取 租户 Id
     *
     * @return {@link String}
     */
    public static Long getTenantId() {
        return getUserDetail().getTenantId();
    }

    /**
     * 获取 租户 Id
     *
     * @return {@link String}
     */
    public static String getTenantName() {
        return getUserDetail().getTenantName();
    }

    /**
     * 获取 会员 企业Id
     *
     * @return {@link String}
     */
    public static Long getEnterPriseId() {
        return getUserDetail().getEnterpriseId();
    }

    /**
     * 获取 会员 企业名称
     *
     * @return {@link String}
     */
    public static String getEnterPriseName() {
        return getUserDetail().getEnterpriseName();
    }

    /**
     * 获取 会员 Id
     *
     * @return {@link String}
     */
    public static Long getMemberId() {
        return getUserDetail().getMemberId();
    }

    /**
     * 获取 会员 Code
     *
     * @return {@link String}
     */
    public static String getMemberCode() {
        return getUserDetail().getMemberCode();
    }

    /**
     * 获取 会员 名称
     *
     * @return {@link String}
     */
    public static String getMemberName() {
        return getUserDetail().getMemberName();
    }

    /**
     * 获取 会员 手机号
     * e.g: 13000000001
     *
     * @return {@link String}
     */
    public static String getMobilePhone() {
        return getUserDetail().getMemberMobilePhone();
    }

    /**
     * 获取用户详细信息
     */
    public static UserDetail getUserDetail() {
        return (UserDetail) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

    }
}
