package com.temp.biz.domain.vo.system.online;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.temp.biz.domain.vo.BasePageQuery;
import lombok.*;

import java.time.ZonedDateTime;

/**
 * 在线用户分页查询
 *
 * @author Hollis
 * @since 2024/06/12 下午5:24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OnlinePageReqVO extends BasePageQuery {

    /**
     * 登录账号，模糊匹配
     */
    private String username;

    /**
     * 用户名称, 模糊匹配
     */
    private String nickname;

    /**
     * 企业, 精准匹配
     */
    private Long enterpriseId;

    /**
     * 部门, 精准匹配
     */
    private Long deptId;

    /**
     * 登录时间, 范围查询-开始(yyyy-MM-dd HH:mm:ss)
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private ZonedDateTime loginDateStart;

    /**
     * 登录时间, 范围查询-结束 (yyyy-MM-dd HH:mm:ss)
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private ZonedDateTime loginDateEnd;
}
