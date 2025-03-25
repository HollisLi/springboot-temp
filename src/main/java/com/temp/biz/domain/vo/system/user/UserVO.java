package com.temp.biz.domain.vo.system.user;

import lombok.Data;

/**
 * 用户信息 VO
 *
 * @author Hollis
 * @since 2024-06-18 15:38
 */
@Data
public class UserVO {

    /**
     * 用户编号
     */
    private Long id;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 企业编号
     */
    private Long enterpriseId;

    /**
     * 部门编号
     */
    private Long deptId;
}