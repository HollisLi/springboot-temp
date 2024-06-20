package com.temp.biz.domain.dto.email;


import lombok.Data;

import java.util.List;

/**
 * 邮件信息dto
 *
 * @author Hollis
 * @since 2024-03-10 16:44
 */
@Data
public class MailInfoDTO {

    /**
     * 邮件主题
     */
    private String emailSubject;

    /**
     * 邮件正文
     */
    private String emailContent;

    /**
     * 收件人邮箱地址
     */
    private List<String> emailToAddress;

    /**
     * 抄送人邮箱地址
     */
    private List<String> emailCcAddress;

    /**
     * 密送人邮箱地址
     */
    private List<String> emailBccAddress;
}
