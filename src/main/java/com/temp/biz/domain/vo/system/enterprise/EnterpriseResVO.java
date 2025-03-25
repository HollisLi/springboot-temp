package com.temp.biz.domain.vo.system.enterprise;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.temp.common.enums.CommonStatusEnum;
import lombok.Data;

import java.time.ZonedDateTime;

/**
 * 企业信息 Response VO
 *
 * @author Hollis
 * @since 2024/06/03 下午3:54
 */
@Data
public class EnterpriseResVO {

    /**
     * 企业ID
     */
    private Long id;

    /**
     * 企业名称
     */
    private String enterpriseName;

    /**
     * 统一社会信用代码
     */
    private String unifiedSocialCreditCode;

    /**
     * 管理员用户ID
     */
    private Long adminUserId;

    /**
     * 管理员用户名称
     */
    private String adminUserName;

    /**
     * 部门状态（0正常 1停用）
     */
    private CommonStatusEnum status;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private ZonedDateTime createTime;
}
