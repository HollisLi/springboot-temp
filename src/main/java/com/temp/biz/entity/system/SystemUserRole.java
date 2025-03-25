package com.temp.biz.entity.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.temp.biz.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户和角色关联表
 *
 * @author Hollis
 * @since 2024-04-16 16:29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "system_user_role")
public class SystemUserRole extends BaseEntity {

    /**
     * 自增编号
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 角色ID
     */
    @TableField(value = "role_id")
    private Long roleId;
}