package com.temp.biz.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.temp.biz.entity.BaseEntity;
import com.temp.common.enums.SexEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户信息表
 *
 * @author Hollis
 * @since 2024-12-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "system_user")
public class SystemUser extends BaseEntity {

    /**
     * 用户ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 登录账号
     */
    @TableField(value = "username")
    private String username;

    /**
     * 登录密码
     */
    @TableField(value = "`password`")
    private String password;

    /**
     * 用户昵称
     */
    @TableField(value = "nickname")
    private String nickname;

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
     * 头像
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 手机号码
     */
    @TableField(value = "mobile")
    private String mobile;

    /**
     * 用户邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 用户性别, 0:女 1:男
     */
    @TableField(value = "sex")
    private SexEnum sex;

    /**
     * 帐号状态（0正常 1停用）
     */
    @TableField(value = "`status`")
    private Boolean status;
}