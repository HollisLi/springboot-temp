package com.temp.biz.domain.vo.system.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 修改密码DTO
 *
 * @author Hollis
 * @since 2024/04/16 上午11:03
 */
@Data
public class ChangePasswordReqVO {

    /**
     * 用户Id
     */
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    /**
     * 新密码
     */
    @Length(min = 4, max = 16, message = "密码长度为 4-16 位")
    @NotBlank(message = "新密码不能为空")
    private String newPassword;
}
