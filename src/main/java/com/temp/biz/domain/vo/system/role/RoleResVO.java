package com.temp.biz.domain.vo.system.role;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.temp.common.enums.CommonStatusEnum;
import lombok.Data;

import java.time.ZonedDateTime;

/**
 * 管理后台 - 角色信息 Response VO
 *
 * @author Hollis
 * @since 2024/06/13 下午2:20
 */
@Data
public class RoleResVO {

    /**
     * 角色ID
     */
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色权限字符串
     */
    private String code;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 角色状态（0正常 1停用）
     */
    private CommonStatusEnum status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private ZonedDateTime createTime;
}
