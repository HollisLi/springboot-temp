package com.temp.biz.entity.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.temp.biz.entity.BaseEntity;
import com.temp.common.enums.CommonStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部门表
 *
 * @author Hollis
 * @since 2024-04-16 16:04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "system_dept")
public class SystemDept extends BaseEntity {

    public static final Long PARENT_ID_ROOT = 0L;

    /**
     * 部门id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 部门名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 企业id
     */
    @TableField(value = "enterprise_id")
    private Long enterpriseId;

    /**
     * 父部门id
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 显示顺序
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 负责人
     */
    @TableField(value = "leader_user_id")
    private Long leaderUserId;

    /**
     * 联系电话
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 部门状态（0正常 1停用）
     */
    @TableField(value = "`status`")
    private CommonStatusEnum status;
}