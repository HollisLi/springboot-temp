package com.temp.biz.domain.vo.system.online;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

/**
 * 在线用户 VO
 *
 * @author Hollis
 * @since 2024/06/12 下午5:21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OnlineResVO {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 登录账号
     */
    private String username;

    /**
     * 用户名称
     */
    private String nickname;

    /**
     * 企业Id
     */
    private Long enterpriseId;

    /**
     * 企业名称
     */
    private String enterpriseName;

    /**
     * 部门Id
     */
    private Long deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 登录Ip
     */
    private String loginIp;

    /**
     * 最后登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private ZonedDateTime loginDate;
}
