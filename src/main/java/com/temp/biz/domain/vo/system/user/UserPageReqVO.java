package com.temp.biz.domain.vo.system.user;

import com.temp.biz.domain.vo.BasePageQuery;
import com.temp.common.enums.CommonStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 管理后台 - 用户分页 Request VO
 *
 * @author Hollis
 * @since 2024-06-14 11:25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPageReqVO extends BasePageQuery {

    /**
     * 用户账号，模糊匹配
     */
    private String username;

    /**
     * 用户名称，模糊匹配
     */
    private String nickName;

    /**
     * 手机号码，模糊匹配
     */
    private String mobile;

    /**
     * 用户状态
     */
    private CommonStatusEnum status;

    /**
     * 企业ID
     */
    private Long enterpriseId;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 部门下的子部门id
     */
    private List<Long> deptChildIds;
}
