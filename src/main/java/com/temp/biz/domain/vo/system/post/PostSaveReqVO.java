package com.temp.biz.domain.vo.system.post;

import com.temp.common.enums.CommonStatusEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 岗位 新增/更新 Request VO
 *
 * @author Hollis
 * @since 2024/06/03 下午1:54
 */
@Data
public class PostSaveReqVO {

    /**
     * 岗位ID
     */
    private Long id;

    /**
     * 岗位名称
     */
    @NotBlank(message = "岗位名称不能为空")
    @Size(max = 50, message = "岗位名称长度不能超过 50 个字符")
    private String name;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 岗位状态
     */
    @NotNull(message = "岗位状态不能为空")
    private CommonStatusEnum status;

    /**
     * 岗位备注
     */
    private String remark;
}
