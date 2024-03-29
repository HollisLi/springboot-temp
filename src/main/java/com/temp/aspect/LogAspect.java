package com.temp.aspect;

import cn.hutool.json.JSONUtil;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

/**
 * 日志切面, 打印接口入参和返回值
 *
 * @author Hollis
 * @since 2024-03-29 15:42
 */
@Log4j2
@Aspect
@Component
public class LogAspect {

    /**
     * 定义一个切点, 只看Controller包下的类
     */
    @Pointcut("execution(public * com.temp.controller..*Controller.*(..))")
    public void controllerPointcut() {
    }

    @Before("controllerPointcut()")
    public void doBefore(JoinPoint joinPoint) {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            log.error("logAspect#doBefore has error, requestAttributes is null");
            return;
        }

        HttpServletRequest request = attributes.getRequest();
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        // 打印请求参数
        Object[] args = joinPoint.getArgs();
        Object[] arguments = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest
                    || args[i] instanceof ServletResponse
                    || args[i] instanceof MultipartFile) {
                continue;
            }
            arguments[i] = args[i];
        }

        // 打印前端请求信息
        log.info("""
                        请求类型: {}
                         请求地址: {}
                         类名方法: {}#{}
                         请求参数: {}""",
                request.getMethod(), request.getRequestURI(),
                signature.getDeclaringTypeName(), name,
                JSONUtil.toJsonPrettyStr(arguments));
    }

    @Around("controllerPointcut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        log.info("返回结果: {}" +
                "\n接口执行耗时：{} ms", JSONUtil.toJsonPrettyStr(result), System.currentTimeMillis() - startTime);
        return result;
    }
}
