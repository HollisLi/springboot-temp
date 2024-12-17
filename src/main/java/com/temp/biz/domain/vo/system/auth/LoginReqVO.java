package com.temp.biz.domain.vo.system.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 账号密码登录 Request VO
 *
 * @author Hollis
 * @since 2024-04-11 15:48
 */
@Data
public class LoginReqVO {

    @NotBlank(message = "登录账号不能为空")
    @Size(min = 4, max = 16, message = "账号长度为 4-16 位")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "账号格式为数字以及字母")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 4, max = 16, message = "密码长度为 4-16 位")
    private String password;

}
