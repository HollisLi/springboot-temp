package com.temp.biz.domain.vo.system.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.temp.biz.domain.vo.system.dept.DeptSimpleResVO;
import com.temp.biz.domain.vo.system.enterprise.EnterpriseSimpleResVO;
import com.temp.biz.domain.vo.system.post.PostSimpleResVO;
import com.temp.biz.domain.vo.system.role.RoleSimpleResVO;
import com.temp.common.enums.SexEnum;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * 管理后台 - 用户个人中心信息 Response VO
 *
 * @author Hollis
 * @since 2024-06-18 17:13
 */
@Data
public class UserProfileRespVO {

    /**
     * 用户编号
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
     * 最后登录 IP
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
     * 所属公司
     */
    private EnterpriseSimpleResVO enterprise;

    /**
     * 所在部门
     */
    private DeptSimpleResVO dept;

    /**
     * 所属岗位数组
     */
    private List<PostSimpleResVO> posts;

    /**
     * 所属角色
     */
    private List<RoleSimpleResVO> roles;
}
