package com.temp.framework.handler;

import com.temp.biz.domain.vo.Result;
import com.temp.common.enums.ResultEnum;
import com.temp.framework.exception.BusinessException;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Objects;

/**
 * 全局异常处理
 *
 * @author Hollis
 * @since 2024-01-06 15:55
 */
@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 登录-账号密码错误异常
     */
    @ExceptionHandler(BadCredentialsException.class)
    public Result<String> exceptionHandler(HttpServletRequest req, BadCredentialsException e) {
        log.error("请求类型: {}, 请求地址: {}, 用户登录失败: {}", req.getMethod(), req.getRequestURI(), e.getMessage(), e);
        return Result.fail(ResultEnum.UNAUTHORIZED_PASSWORD_FAILURE, e.getMessage());
    }

    /**
     * hasRole 权限校验异常
     */
    @ExceptionHandler(AccessDeniedException.class)
    public Result<String> exceptionHandler(HttpServletRequest req, AccessDeniedException e) {
        log.error("请求类型: {}, 请求地址: {}, 权限校验失败: {}", req.getMethod(), req.getRequestURI(), e.getMessage(), e);
        return Result.fail(ResultEnum.FORBIDDEN_INSUFFICIENT_PERMISSIONS, e.getMessage());
    }

    /**
     * 空指针异常
     */
    @ExceptionHandler(value = NullPointerException.class)
    public Result<String> exceptionHandler(HttpServletRequest req, NullPointerException e) {
        log.error("请求类型: {}, 请求地址: {}, 发生空指针异常: {}", req.getMethod(), req.getRequestURI(), e.getMessage(), e);
        return Result.fail(ResultEnum.FAIL_NULL_POINTER, e.getMessage());
    }

    /**
     * 数组越界异常
     */
    @ExceptionHandler(value = ArrayIndexOutOfBoundsException.class)
    public Result<String> exceptionHandler(HttpServletRequest req, ArrayIndexOutOfBoundsException e) {
        log.error("请求类型: {}, 请求地址: {}, 发生数组越界异常: {}", req.getMethod(), req.getRequestURI(), e.getMessage(), e);
        return Result.fail(ResultEnum.FAIL_ARRAY_INDEX_OUT_OF_BOUNDS, e.getMessage());
    }

    /**
     * 处理SQL异常
     */
    @ExceptionHandler(value = SQLException.class)
    public Result<String> exceptionHandler(HttpServletRequest req, SQLException e) {
        log.error("请求类型: {}, 请求地址: {}, 发生SQL异常: {}", req.getMethod(), req.getRequestURI(), e.getMessage(), e);
        return Result.fail(ResultEnum.FAIL_DATABASE, e.getMessage());
    }

    /**
     * 参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> exceptionHandler(HttpServletRequest req, MethodArgumentNotValidException e) {
        log.error("请求类型: {}, 请求地址: {}, 入参校验不通过: {}", req.getMethod(), req.getRequestURI(), e.getMessage(), e);
        FieldError fieldError = e.getBindingResult().getFieldError();
        if (Objects.isNull(fieldError)) {
            return Result.fail(ResultEnum.BAD_REQUEST, e.getMessage());
        }
        return Result.fail(ResultEnum.BAD_REQUEST.code, fieldError.getDefaultMessage());
    }

    /**
     * 参数校验异常
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Result<String> exceptionHandler(HttpServletRequest req, IllegalArgumentException e) {
        log.error("请求类型: {}, 请求地址: {}, 发生参数校验异常: {}", req.getMethod(), req.getRequestURI(), e.getMessage(), e);
        return Result.fail(ResultEnum.BAD_REQUEST_PARAM.code, e.getMessage());
    }

    /**
     * 业务处理异常
     */
    @ExceptionHandler(BusinessException.class)
    public Result<String> exceptionHandler(HttpServletRequest req, BusinessException e) {
        log.error("请求类型: {}, 请求地址: {}, 发生业务异常: {}", req.getMethod(), req.getRequestURI(), e.getMessage(), e);
        return Result.fail(ResultEnum.FAIL_BUSINESS.code, e.getMessage());
    }

    /**
     * 不支持的请求类型, 例如用 GET 请求了一个请求方法为 POST 的接口
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<String> exceptionHandler(HttpServletRequest req, HttpRequestMethodNotSupportedException e) {
        log.error("请求类型: {}, 请求地址: {}, 不支持的请求类型: {}", req.getMethod(), req.getRequestURI(), e.getMessage(), e);
        return Result.fail(ResultEnum.NOT_FOUND_METHOD, e.getMessage());
    }

    /**
     * 请求参数无法映射, 例如: 后台参数名为 userId , 但是前台传参名为 user_id
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result<String> exceptionHandler(HttpServletRequest req, MissingServletRequestParameterException e) {
        log.error("请求类型: {}, 请求地址: {}, 请求参数无法映射: {}", req.getMethod(), req.getRequestURI(), e.getMessage(), e);
        return Result.fail(ResultEnum.BAD_REQUEST, e.getMessage());
    }

    /**
     * 运行时异常
     */
    @ExceptionHandler(value = RuntimeException.class)
    public Result<String> exceptionHandler(HttpServletRequest req, RuntimeException e) {
        log.error("请求类型: {}, 请求地址: {}, 发生运行时异常: {}", req.getMethod(), req.getRequestURI(), e.getMessage(), e);
        return Result.fail(ResultEnum.FAIL, e.getMessage());
    }

    /**
     * 处理其他异常, 兜底
     */
    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest req, Exception e) {
        log.error("请求类型: {}, 请求地址: {}, 发生未知异常: {}", req.getMethod(), req.getRequestURI(), e.getMessage(), e);
        return Result.fail(ResultEnum.FAIL, e.getMessage());
    }
}