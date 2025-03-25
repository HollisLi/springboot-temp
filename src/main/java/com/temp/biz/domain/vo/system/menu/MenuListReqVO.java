package com.temp.biz.domain.vo.system.menu;

import com.temp.common.enums.CommonStatusEnum;
import lombok.Data;

/**
 * 管理后台 - 菜单列表 Request VO
 *
 * @author Hollis
 * @since 2024-06-13 16:37
 */
@Data
public class MenuListReqVO {

    /**
     * 菜单名称，模糊匹配
     */
    private String name;

    /**
     * 状态
     */
    private CommonStatusEnum status;
}
