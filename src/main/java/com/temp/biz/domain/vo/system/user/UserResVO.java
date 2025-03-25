package com.temp.biz.domain.vo.system.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.temp.common.enums.CommonStatusEnum;
import com.temp.common.enums.SexEnum;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

/**
 * 管理后台 - 用户信息 Response VO
 *
 * @author Hollis
 * @since 2024-06-14 11:29
 */
@Data
public class UserResVO {

    /**
     * 用户编号
     */
    private Long id;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 备注
     */
    private String remark;

    /**
     * 企业Id
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
     * 用户头像
     */
    private String avatar;

    /**
     * 状态
     */
    private CommonStatusEnum status;

    /**
     * 最后登录IP
     */
    private String loginIp;

    /**
     * 最后登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private ZonedDateTime loginDate;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private ZonedDateTime createTime;

    /**
     * 岗位编号数组
     */
    private List<Long> postIds;

    /**
     * 角色数组
     */
    private Set<Long> roleIds;
}
