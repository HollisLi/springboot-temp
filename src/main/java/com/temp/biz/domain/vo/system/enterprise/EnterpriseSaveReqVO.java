package com.temp.biz.domain.vo.system.enterprise;

import com.temp.common.enums.CommonStatusEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 企业信息 新增/更新 Request VO
 *
 * @author Hollis
 * @since 2024/06/03 下午1:54
 */
@Data
public class EnterpriseSaveReqVO {

    /**
     * 企业ID
     */
    private Long id;

    /**
     * 企业名称
     */
    @Size(max = 125, message = "企业名称长度不能超过 125 个字符")
    @NotBlank(message = "企业名称不能为空")
    private String enterpriseName;

    /**
     * 统一社会信用代码
     */
    @Size(max = 125, message = "统一社会信用代码长度不能超过 125 个字符")
    private String unifiedSocialCreditCode;

    /**
     * 管理员用户ID
     */
    private Long adminUser;

    /**
     * 部门状态（0正常 1停用）
     */
    @NotNull(message = "企业状态不能为空")
    private CommonStatusEnum status;
}
