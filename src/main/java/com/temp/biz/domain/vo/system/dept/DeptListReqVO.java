package com.temp.biz.domain.vo.system.dept;

import com.temp.common.enums.CommonStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 部门信息列表 Request VO
 *
 * @author Hollis
 * @since 2024-06-03 18:26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeptListReqVO {

    /**
     * 部门名称模糊查询
     */
    private String name;

    /**
     * 部门状态精准查询, 0 - 开启，1 - 关闭
     */
    private CommonStatusEnum status;

    /**
     * 企业ID
     */
    private Long enterpriseId;
}
