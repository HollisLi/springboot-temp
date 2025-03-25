package com.temp.biz.domain.vo;

import com.temp.common.enums.ResultEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 公共响应
 *
 * @author Hollis
 * @since 2024-01-06 15:02
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 7520341178274901974L;

    /**
     * 公共响应 - 状态码
     */
    private Integer code;

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

    public static <T> Result<T> fail(String message) {
        return new Result<>(ResultEnum.FAIL.code, message, null);
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

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}

