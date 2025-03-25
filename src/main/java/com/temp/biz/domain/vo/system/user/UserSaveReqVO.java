package com.temp.biz.domain.vo.system.user;

import com.temp.common.enums.CommonStatusEnum;
import com.temp.common.enums.SexEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 用户 新增/修改 Request VO
 *
 * @author Hollis
 * @since 2024/06/03 下午6:51
 */
@Data
public class UserSaveReqVO {

    /**
     * 角色ID
     */
    private Long id;

    /**
     * 登录账号
     */
    @Size(max = 30, message = "登录账号长度不能超过30个字符")
    @NotBlank(message = "登录账号不能为空")
    private String username;

    /**
     * 用户密码, 仅新增时传递
     */
    private String password;

    /**
     * 用户名称
     */
    @Size(max = 30, message = "用户名称长度不能超过30个字符")
    @NotBlank(message = "用户名称不能为空")
    private String nickname;

    /**
     * 邮件
     */
    @Size(max = 50, message = "邮件长度不能超过50个字符")
    private String email;

    /**
     * 手机号
     */
    @Size(max = 11, message = "手机号长度不能超过11个字符")
    private String mobile;

    /**
     * 性别
     */
    private SexEnum sex;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 备注
     */
    @Size(max = 500, message = "备注长度不能超过500个字符")
    private String remark;

    /**
     * 用户状态
     */
    @NotNull(message = "用户状态不能为空")
    private CommonStatusEnum status;

    /**
     * 企业ID
     */
    @NotNull(message = "企业ID不能为空")
    private Long enterpriseId;

    /**
     * 部门ID
     */
    @NotNull(message = "部门ID不能为空")
    private Long deptId;

    /**
     * 岗位ID集合
     */
    private List<Long> postIds;
}
