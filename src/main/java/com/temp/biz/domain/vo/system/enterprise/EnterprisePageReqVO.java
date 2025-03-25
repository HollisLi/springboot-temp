package com.temp.biz.domain.vo.system.enterprise;

import com.temp.biz.domain.vo.BasePageQuery;
import com.temp.common.enums.CommonStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 企业信息-分页列表查询 Request VO
 *
 * @author Hollis
 * @since 2024/06/03 下午3:50
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class EnterprisePageReqVO extends BasePageQuery {

    /**
     * 企业名称, 模糊搜索
     */
    private String enterpriseName;

    /**
     * 企业社会统一信用代码 精准查询
     */
    private String unifiedSocialCreditCode;

    /**
     * 企业状态（0正常 1停用）
     */
    private CommonStatusEnum status;
}
