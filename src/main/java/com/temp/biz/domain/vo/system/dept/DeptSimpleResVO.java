package com.temp.biz.domain.vo.system.dept;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 部门易于理解响应vo
 *
 * @author Hollis
 * @since 2024-06-19 17:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptSimpleResVO implements Serializable {

    private static final long serialVersionUID = -3535288997714420142L;

    /**
     * 部门Id
     */
    private Long id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 父部门 ID
     */
    private Long parentId;
}
