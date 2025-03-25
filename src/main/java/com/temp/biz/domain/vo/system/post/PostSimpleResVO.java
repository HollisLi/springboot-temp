package com.temp.biz.domain.vo.system.post;

import lombok.Data;

/**
 * 管理后台 - 岗位信息的精简 Response VO
 *
 * @author Hollis
 * @since 2024-06-18 17:16
 */
@Data
public class PostSimpleResVO {

    /**
     * 岗位序号
     */
    private Long id;

    /**
     * 岗位名称
     */
    private String name;

}
