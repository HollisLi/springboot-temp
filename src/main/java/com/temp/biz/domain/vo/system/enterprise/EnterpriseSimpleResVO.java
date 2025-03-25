package com.temp.biz.domain.vo.system.enterprise;

import lombok.Data;

import java.io.Serializable;

/**
 * 精简企业信息 Response VO
 *
 * @author Hollis
 * @since 2024/06/03 下午3:54
 */
@Data
public class EnterpriseSimpleResVO implements Serializable {

    private static final long serialVersionUID = -3470044902349190006L;

    /**
     * 企业ID
     */
    private Long id;

    /**
     * 企业名称
     */
    private String enterpriseName;
}
