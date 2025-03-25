package com.temp.biz.entity.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.temp.biz.entity.BaseEntity;
import com.temp.common.enums.ColorTypeEnumEnum;
import com.temp.common.enums.CommonStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典数据表
 *
 * @author Hollis
 * @since 2024-06-13 10:46
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "system_dict_data")
public class SystemDictData extends BaseEntity {

    /**
     * 字典键值主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 字典排序
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 字典标签
     */
    @TableField(value = "`label`")
    private String label;

    /**
     * 字典键值
     */
    @TableField(value = "`value`")
    private String value;

    /**
     * 字典类型
     */
    @TableField(value = "dict_type")
    private String dictType;

    /**
     * 状态（0正常 1停用）
     */
    @TableField(value = "`status`")
    private CommonStatusEnum status;

    /**
     * 颜色类型
     */
    @TableField(value = "color_type")
    private ColorTypeEnumEnum colorType;

    /**
     * css样式
     */
    @TableField(value = "css_class")
    private String cssClass;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;
}