package com.temp.biz.domain.vo.system.logger;

import com.temp.biz.domain.vo.BasePageQuery;
import com.temp.common.enums.LoginResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 管理后台 - 登录日志分页列表 Request VO
 *
 * @author Hollis
 * @since 2024-06-17 15:06
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LoginLogPageReqVO extends BasePageQuery {

    /**
     * 用户 IP，模糊匹配
     */
    private String userIp;

    /**
     * 用户账号，模糊匹配
     */
    private String username;

    /**
     * 登录结果
     */
    private LoginResultEnum status;

    /**
     * 登录时间-开始时间(yyyy-MM-dd HH:mm:ss)
     * 示例: [2022-07-01 00:00:00,2022-07-01 23:59:59]
     */
    private String createTimeBegin;

    /**
     * 登录时间-结束时间(yyyy-MM-dd HH:mm:ss)
     */
    private String createTimeEnd;
}
