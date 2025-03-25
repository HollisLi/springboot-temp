package com.temp.biz.entity.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.temp.biz.entity.BaseEntity;
import com.temp.common.enums.CommonStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典类型表
 *
 * @author Hollis
 * @since 2024-06-13 10:45
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "system_dict_type")
public class SystemDictType extends BaseEntity {

    /**
     * 字典主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 字典名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 字典类型
     */
    @TableField(value = "`type`")
    private String type;

    /**
     * 状态（0正常 1停用）
     */
    @TableField(value = "`status`")
    private CommonStatusEnum status;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;
}