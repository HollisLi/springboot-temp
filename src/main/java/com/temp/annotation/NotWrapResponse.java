package com.temp.annotation;

import java.lang.annotation.*;

/**
 * 有此注释的类/方法, 返回时会跳过包装 Response
 *
 * @author Hollis
 * @since 2024-01-08 18:37
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotWrapResponse {
}
