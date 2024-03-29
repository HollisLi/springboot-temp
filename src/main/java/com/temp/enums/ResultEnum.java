package com.temp.enums;

import lombok.AllArgsConstructor;

/**
 * 公共响应码 Enum
 *
 * @author Hollis
 * @since 2023/08/15 19:49
 */
@AllArgsConstructor
public enum ResultEnum {

    /**
     * 1xx（信息性状态码）：服务器已接收到请求，需要进一步处理.
     */
    CONTINUE(100, "服务器已经接收到请求头, 客户端应该继续发送请求体"),
    SWITCHING_PROTOCOLS(101, "服务器已经理解了客户端的请求，并将通过Upgrade消息头通知客户端采用不同的协议来完成这个请求"),

    /**
     * 2xx（成功状态码）：请求已成功处理并返回响应
     */
    SUCCESS(200, "系统处理已完成"),
    CREATED(201, "请求已成功，并且服务器创建了新的资源"),
    NO_CONTENT(204, "请求已成功，但响应不包含任何内容"),

    /**
     * 3xx（重定向状态码）：客户端需要采取进一步的操作才能完成请求。
     */
    MOVED_PERMANENTLY(301, "请求的资源已被永久移动到新位置"),
    FOUND(302, "请求的资源临时被移动到新位置"),
    NOT_MODIFIED(304, "后端无变化,客户端请使用缓存版本"),

    /**
     * 4xx（客户端错误状态码）：客户端发送的请求有错误。
     */
    BAD_REQUEST(400, "参数校验不通过, 请提供正确的参数"),

    UNAUTHORIZED(401, "请求需要携带鉴权信息"),
    UNAUTHORIZED_LOGIN_FAILURE(401001, "登录信息无效"),

    FORBIDDEN(403, "服务器拒绝请求"),
    FORBIDDEN_INSUFFICIENT_PERMISSIONS(403001, "权限不足,请联系管理员授权"),

    NOT_FOUND(404, "请求的资源不存在"),
    NOT_FOUND_MEMBER(404001, "用户不存在"),
    NOT_FOUND_METHOD(404002, "请使用正确的请求类型"),

    REQUEST_TIMEOUT(408, "请求超时"),

    /**
     * 5xx（服务器错误状态码）：服务器在处理请求时发生了错误。
     */
    FAIL(500, "系统出现运行时异常，请稍后再试或联系管理员处理。"),

    FAIL_NULL_POINTER(500101, "系统出现空指针异常，请稍后再试或联系管理员处理。"),
    FAIL_ARRAY_INDEX_OUT_OF_BOUNDS(500102, "系统出现数组越界异常，请稍后再试或联系管理员处理。"),
    FAIL_DATABASE(500103, "系统出现数据库异常，请稍后再试或联系管理员处理。"),

    FAIL_BUSINESS(500200, "系统出现业务处理异常，请稍后再试或联系管理员处理。"),

    BAD_GATEWAY(502, "服务器作为网关或代理时，从上游服务器接收到无效的响应"),
    SERVICE_UNAVAILABLE(503, "服务器当前无法处理请求，可能是由于服务器维护或过载, 请稍后再试"),
    GATEWAY_TIMEOUT(504, "服务器作为网关或代理时，未能及时从上游服务器接收到请求"),
    ;

    public final Integer code;
    public final String message;
}
