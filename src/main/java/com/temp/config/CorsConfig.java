package com.temp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <pre>
 * Cors配置, 解决跨域问题
 * //* 这个类和 SecurityConfig#corsConfigurationSource() 只要启用一个就行
 * </pre>
 *
 * @author Hollis
 * @see com.temp.security.config.SecurityConfig#corsConfigurationSource()
 * @since 2024-03-25 20:17
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                // 允许跨域访问的路径
                .addMapping("/**")
                // 是否发送 Cookie
                .allowCredentials(true)
                // 支持域
                .allowedOriginPatterns("*")
                // 支持方法
                .allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"})
                .allowedHeaders("*")
                .exposedHeaders("*");
    }
}