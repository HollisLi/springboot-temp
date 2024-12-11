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

    public static final String THREAD_POOL_TASK_EXECUTOR = "THREAD_POOL_TASK_EXECUTOR";


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

    @Bean(THREAD_POOL_TASK_EXECUTOR)
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setCorePoolSize(4);
        // 设置最大线程数
        executor.setMaxPoolSize(8);
        // 设置空闲时间
        executor.setKeepAliveSeconds(60);
        // 设置队列大小
        executor.setQueueCapacity(200);
        // 配置线程池的前缀
        executor.setThreadNamePrefix("thread-pool-task-");
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 进行加载
        executor.initialize();
        return executor;
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
