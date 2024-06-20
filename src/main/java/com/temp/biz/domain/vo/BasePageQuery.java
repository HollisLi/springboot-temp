package com.temp.biz.domain.vo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 分页查询
 *
 * @author Hollis
 * @since 2023/10/18 14:41
 */
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BasePageQuery {

    public static final Integer DEF_PAGE_NUM = 1;
    public static final Integer DEF_PAGE_SIZE = 20;

    /**
     * 当前页码, 默认: 1
     */
    private Integer pageNum;

    /**
     * 查询条数, 默认: 20
     */
    private Integer pageSize;

    public Integer getPageNum() {
        return null == pageNum ? DEF_PAGE_NUM : Math.abs(pageNum);
    }

    public Integer getPageSize() {
        return null == pageSize ? DEF_PAGE_SIZE : Math.abs(pageSize);
    }
}
