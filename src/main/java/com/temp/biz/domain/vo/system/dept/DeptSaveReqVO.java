package com.temp.biz.domain.vo.system.dept;

import com.temp.common.enums.CommonStatusEnum;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 管理后台 - 部门创建/修改 Request VO
 *
 * @author Hollis
 * @since 2024-06-03 11:34
 */
@Data
public class DeptSaveReqVO {

    /**
     * 部门ID
     */
    private Long id;

    /**
     * 部门名称
     */
    @NotBlank(message = "部门名称不能为空")
    @Size(max = 30, message = "部门名称长度不能超过 30 个字符")
    private String name;

    /**
     * 企业ID
     */
    @NotNull(message = "企业 ID 不能为空")
    private Long enterpriseId;

    /**
     * 父部门 ID
     */
    private Long parentId;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 负责人的用户编号
     */
    private Long leaderUserId;

    /**
     * 联系电话
     */
    @Size(max = 11, message = "联系电话长度不能超过11个字符")
    private String phone;

    /**
     * 邮箱
     */
    @Email(message = "邮箱格式不正确")
    @Size(max = 50, message = "邮箱长度不能超过 50 个字符")
    private String email;

    /**
     * 部门 状态, ENABLE:开启, DISABLE:关闭
     */
    @NotNull(message = "状态不能为空")
    private CommonStatusEnum status;
}
