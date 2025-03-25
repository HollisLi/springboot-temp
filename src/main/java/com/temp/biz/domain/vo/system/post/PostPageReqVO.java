package com.temp.biz.domain.vo.system.post;

import com.temp.biz.domain.vo.BasePageQuery;
import com.temp.common.enums.CommonStatusEnum;
import lombok.*;

/**
 * 岗位分页查询
 *
 * @author Hollis
 * @since 2024/06/12 下午5:24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PostPageReqVO extends BasePageQuery {

    /**
     * 岗位名称，模糊匹配
     */
    private String name;

    /**
     * 岗位状态, 精准匹配
     */
    private CommonStatusEnum status;
}
