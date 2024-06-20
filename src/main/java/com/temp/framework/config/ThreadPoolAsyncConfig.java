package com.temp.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步线程池配置
 *
 * @author Hollis
 * @since 2023-09-20 14:18
 */
@EnableAsync
@Configuration
public class ThreadPoolAsyncConfig {

    /**
     * 可抛弃线程
     */
    @Bean("asyncDiscardExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setThreadNamePrefix("异步线程池-");
        pool.setCorePoolSize(5);
        pool.setMaxPoolSize(20);
        pool.setKeepAliveSeconds(60);
        pool.setQueueCapacity(0);
        // 多的任务直接丢弃
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        // 初始化
        pool.initialize();
        return pool;
    }

    /**
     * 异步线程
     */
    @Bean(name = "asyncThreadPoolTaskExecutor")
    public ThreadPoolTaskExecutor asyncThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(20);
        executor.setKeepAliveSeconds(60);
        executor.setQueueCapacity(200);
        // 线程池对拒绝任务(无线程可用)的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        return executor;
    }
}
