package com.temp.biz.domain.vo.system.role;

import com.temp.common.enums.CommonStatusEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 角色 新增/更新 Request VO
 *
 * @author Hollis
 * @since 2024/06/03 下午1:54
 */
@Data
public class RoleSaveReqVO {

    /**
     * 角色ID
     */
    private Long id;

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    @Size(max = 30, message = "角色名称长度不能超过 30 个字符")
    private String name;

    /**
     * 角色Code
     */
    @NotBlank(message = "角色Code不能为空")
    @Size(max = 100, message = "角色Code长度不能超过 100 个字符")
    private String code;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 角色状态
     */
    @NotNull(message = "角色状态不能为空")
    private CommonStatusEnum status;

    /**
     * 角色备注
     */
    private String remark;
}
