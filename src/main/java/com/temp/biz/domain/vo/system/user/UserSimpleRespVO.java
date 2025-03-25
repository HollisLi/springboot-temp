package com.temp.biz.domain.vo.system.user;

import lombok.Data;

/**
 * 管理后台 - 用户精简信息 Response VO
 *
 * @author Hollis
 * @since 2024-06-18 17:43
 */
@Data
public class UserSimpleRespVO {

    /**
     * 用户编号
     */
    private Long id;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 部门名称
     */
    private String deptName;

}
