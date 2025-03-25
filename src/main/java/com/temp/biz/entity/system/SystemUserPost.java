package com.temp.biz.entity.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.temp.biz.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户岗位表
 *
 * @author Hollis
 * @since 2024-04-16 16:34
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "system_user_post")
public class SystemUserPost extends BaseEntity {

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 岗位ID
     */
    @TableField(value = "post_id")
    private Long postId;
}