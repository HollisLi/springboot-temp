package com.temp.biz.domain.vo.system.dict;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 字典数据信息 Response VO
 *
 * @author Hollis
 * @since 2024-06-13 10:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DictDataResVO implements Serializable {

    private static final long serialVersionUID = 932714336229186788L;

    /**
     * 字典数据ID
     */
    private Long id;

    /**
     * 字典标签
     */
    private String label;

    /**
     * 字典值
     */
    private String value;

}
