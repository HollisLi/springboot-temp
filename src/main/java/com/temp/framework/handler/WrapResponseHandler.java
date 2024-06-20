package com.temp.framework.handler;

import cn.hutool.json.JSONUtil;
import com.temp.biz.domain.vo.Result;
import com.temp.framework.annotation.NotWrapResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

/**
 * Controller 返回值统一封装
 *
 * @author Hollis
 * @since 2024-01-06 15:28
 */
@ControllerAdvice
public class WrapResponseHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        if (returnType.hasMethodAnnotation(ExceptionHandler.class)
                || returnType.hasMethodAnnotation(NotWrapResponse.class)) {
            return false;
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  @NotNull MethodParameter returnType,
                                  @NotNull MediaType selectedContentType,
                                  @NotNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  @NotNull ServerHttpRequest request,
                                  @NotNull ServerHttpResponse response) {
        // String 类型会导致序列化错误
        if (Objects.isNull(body) || body instanceof String) {
            return JSONUtil.toJsonStr(Result.success(body));
        }
        return Result.success(body);
    }
}
