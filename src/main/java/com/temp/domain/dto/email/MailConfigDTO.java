package com.temp.domain.dto.email;


import lombok.Data;


/**
 * 电子邮件配置 DTO
 *
 * @author Hollis
 * @since 2024-03-10 16:38
 */
@Data
public class MailConfigDTO {

    /**
     * 邮箱地址
     */
    private String emailAddress;

    /**
     * 邮箱密码
     */
    private String emailPassword;

    /**
     * smtp 服务器地址
     */
    private String smtpAddress;

    /**
     * smtp 端口号
     */
    private Integer smtpPort;
}
