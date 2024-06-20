package com.temp.biz.domain.dto.email;

import lombok.Data;


/**
 * 邮件信息文件dto
 *
 * @author Hollis
 * @since 2024-03-10 16:44
 */
@Data
public class MailInfoFileDTO {

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件URL地址
     */
    private String fileUrl;
}
