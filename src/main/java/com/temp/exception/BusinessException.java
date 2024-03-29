package com.temp.exception;

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
}
