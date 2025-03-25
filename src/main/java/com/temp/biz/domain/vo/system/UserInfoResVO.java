package com.temp.biz.domain.vo.system;

import com.temp.biz.entity.system.SystemMenu;
import com.temp.common.enums.SexEnum;
import lombok.Data;

import java.util.Set;

/**
 * 用户信息响应 VO
 *
 * @author Hollis
 * @since 2024/04/16 下午3:37
 */
@Data
public class UserInfoResVO {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户账号
     */
    private String username;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 企业ID
     */
    private Long enterpriseId;

    /**
     * 企业名称
     */
    private String enterpriseName;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 岗位Id
     */
    private Long postId;

    /**
     * 岗位名称
     */
    private String postName;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 用户性别
     */
    private SexEnum sex;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 未读业务通知数量
     */
    private Long businessNotifyCount;

    /**
     * 菜单权限
     */
    private Set<SystemMenu> menus;
}
