package com.temp.biz.domain.vo.system.user;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 管理后台 - 用户更新密码 Request VO
 *
 * @author Hollis
 * @since 2024-06-14 11:19
 */
@Data
public class UserUpdatePasswordReqVO {

    @NotNull(message = "用户编号不能为空")
    private Long id;

    @NotEmpty(message = "密码不能为空")
    @Length(min = 4, max = 16, message = "密码长度为 4-16 位")
    private String password;

}
