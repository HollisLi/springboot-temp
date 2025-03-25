package com.temp.biz.entity.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.temp.biz.entity.BaseEntity;
import com.temp.common.enums.CommonStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 企业信息表
 *
 * @author Hollis
 * @since 2024-04-16 14:17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "system_enterprise")
public class SystemEnterprise extends BaseEntity {

    /**
     * 企业ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 企业名称
     */
    @TableField(value = "enterprise_name")
    private String enterpriseName;

    /**
     * 统一社会信用代码
     */
    @TableField(value = "unified_social_credit_code")
    private String unifiedSocialCreditCode;

    /**
     * 管理员用户ID
     */
    @TableField(value = "admin_user")
    private Long adminUser;

    /**
     * 部门状态（0正常 1停用）
     */
    @TableField(value = "`status`")
    private CommonStatusEnum status;
}