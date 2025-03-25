package com.temp.biz.domain.vo.system.logger;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.temp.common.enums.LoginLogTypeEnum;
import com.temp.common.enums.LoginResultEnum;
import lombok.Data;

import java.time.ZonedDateTime;

/**
 * 管理后台 - 登录日志 Response VO
 *
 * @author Hollis
 * @since 2024-06-17 15:22
 */
@Data
public class LoginLogRespVO {

    /**
     * 日志编号
     */
    private Long id;

    /**
     * 登录类型
     */
    private LoginLogTypeEnum logType;

    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 登录账号
     */
    private String username;

    /**
     * 登录结果
     */
    private LoginResultEnum result;

    /**
     * 用户 IP
     */
    private String userIp;

    /**
     * 浏览器 UA
     */
    private String userAgent;

    /**
     * 登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private ZonedDateTime createTime;
}
