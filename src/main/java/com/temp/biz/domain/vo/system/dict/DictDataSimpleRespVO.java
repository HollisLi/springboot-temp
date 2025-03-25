package com.temp.biz.domain.vo.system.dict;

import com.temp.common.enums.ColorTypeEnumEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 管理后台 - 数据字典精简 Response VO
 *
 * @author Hollis
 * @since 2024-06-17 18:17
 */
@Data
public class DictDataSimpleRespVO implements Serializable {

    private static final long serialVersionUID = -3722851255136428513L;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 字典键值
     */
    private String value;

    /**
     * 字典标签
     */
    private String label;

    /**
     * 颜色类型，default、primary、success、info、warning、danger
     *
     * @see ColorTypeEnumEnum
     */
    private String colorType;

    /**
     * css 样式
     */
    private String cssClass;

}
