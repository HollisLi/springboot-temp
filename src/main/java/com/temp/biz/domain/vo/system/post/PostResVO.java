package com.temp.biz.domain.vo.system.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.temp.common.enums.CommonStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

/**
 * VO
 *
 * @author Hollis
 * @since 2024/06/12 下午5:21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResVO {

    /**
     * 岗位ID
     */
    private Long id;

    /**
     * 岗位名称
     */
    private String name;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 状态（0正常 1停用）
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
