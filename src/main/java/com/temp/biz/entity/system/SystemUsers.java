package com.temp.biz.entity.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.temp.biz.entity.BaseEntity;
import com.temp.common.enums.CommonStatusEnum;
import com.temp.common.enums.SexEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;

/**
 * 用户信息表
 *
 * @author Hollis
 * @since 2024-04-11 17:12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "system_users")
public class SystemUsers extends BaseEntity {

    /**
     * 用户ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 用户账号
     */
    @TableField(value = "username")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "`password`")
    private String password;

    /**
     * 用户昵称
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 企业ID
     */
    @TableField(value = "enterprise_id")
    private Long enterpriseId;

    /**
     * 部门ID
     */
    @TableField(value = "dept_id")
    private Long deptId;

    /**
     * 用户邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 手机号码
     */
    @TableField(value = "mobile")
    private String mobile;

    /**
     * 用户性别
     */
    @TableField(value = "sex")
    private SexEnum sex;

    /**
     * 头像地址
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 帐号状态（0正常 1停用）
     */
    @TableField(value = "`status`")
    private CommonStatusEnum status;

    /**
     * 最后登录IP
     */
    @TableField(value = "login_ip")
    private String loginIp;

    /**
     * 最后登录时间
     */
    @TableField(value = "login_date")
    private ZonedDateTime loginDate;
}