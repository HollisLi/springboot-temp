package com.temp.biz.domain.vo.system.role;

import com.temp.biz.domain.vo.BasePageQuery;
import com.temp.common.enums.CommonStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 管理后台 - 角色分页 Request VO
 *
 * @author Hollis
 * @since 2024-06-13 14:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RolePageReqVO extends BasePageQuery {

    /**
     * 角色名称，模糊匹配
     */
    private String name;

    /**
     * 角色标识，模糊匹配
     */
    private String code;

    /**
     * 展示状态
     */
    private CommonStatusEnum status;
}
