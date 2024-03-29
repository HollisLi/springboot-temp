package com.temp.domain.vo;

import com.temp.enums.ResultEnum;
import lombok.Data;

/**
 * 公共响应
 *
 * @author Hollis
 * @since 2024-01-06 15:02
 */
@Data
public class Result<T> {

    /**
     * 公共响应 - 状态码
     */
    private Integer status;

    /**
     * 公共响应 - 消息
     */
    private String message;

    /**
     * 公共响应 - 数据
     */
    private T data;


    public static <T> Result<T> success() {
        return new Result<T>(ResultEnum.SUCCESS.code, ResultEnum.SUCCESS.message, null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(ResultEnum.SUCCESS.code, ResultEnum.SUCCESS.message, data);
    }

    public static <T> Result<T> success(String message, T data) {
        return new Result<T>(ResultEnum.SUCCESS.code, message, data);
    }

    public static <T> Result<T> fail(String message) {
        return new Result<>(ResultEnum.FAIL.code, message, null);
    }

    public static <T> Result<T> fail(T data) {
        return new Result<>(ResultEnum.FAIL.code, ResultEnum.FAIL.message, data);
    }

    public static <T> Result<T> fail(Integer code, String message) {
        return new Result<>(code, message, null);
    }

    public static <T> Result<T> fail(String message, T data) {
        return new Result<>(ResultEnum.FAIL.code, message, data);
    }

    public static <T> Result<T> fail(ResultEnum resultEnum) {
        return new Result<>(resultEnum.code, resultEnum.message, null);
    }

    public static <T> Result<T> fail(ResultEnum resultEnum, T data) {
        return new Result<>(resultEnum.code, resultEnum.message, data);
    }

    public Result(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}

