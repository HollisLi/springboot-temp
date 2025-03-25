package com.temp.biz.entity.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.temp.biz.entity.BaseEntity;
import com.temp.common.enums.CommonStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色信息表
 *
 * @author Hollis
 * @since 2024-04-16 16:17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "system_role")
public class SystemRole extends BaseEntity {

    /**
     * 角色ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 角色名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 角色权限字符串
     */
    @TableField(value = "code")
    private String code;

    /**
     * 显示顺序
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 角色状态（0正常 1停用）
     */
    @TableField(value = "`status`")
    private CommonStatusEnum status;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;
}