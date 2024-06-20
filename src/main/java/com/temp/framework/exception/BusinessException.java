package com.temp.framework.exception;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 业务异常, 异常信息会返回给用户
 *
 * @author Hollis
 * @since 2024-01-06 14:58
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class BusinessException extends RuntimeException {

    /**
     * 用户看到的异常提示信息
     */
    private String message;

    /**
     * 格式化文本, {} 表示占位符<br>
     * 此方法只是简单将占位符 {} 按照顺序替换为参数<br>
     * 如果想输出 {} 使用 \\转义 { 即可，如果想输出 {} 之前的 \ 使用双转义符 \\\\ 即可<br>
     * 例：<br>
     * 通常使用：format("this is {} for {}", "a", "b") =》 this is a for b<br>
     * 转义{}： format("this is \\{} for {}", "a", "b") =》 this is {} for a<br>
     * 转义\： format("this is \\\\{} for {}", "a", "b") =》 this is \a for b<br>
     *
     * @param template 文本模板，被替换的部分用 {} 表示，如果模板为null，返回"null"
     * @param params   参数值
     */
    public BusinessException(String template, Object... params) {
        this.message = StrUtil.format(template, params);
    }
}
