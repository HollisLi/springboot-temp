package com.temp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 项目启动类
 *
 * @author Hollis
 * @since 2024-01-03 18:58
 */
@EnableScheduling
@MapperScan("com.temp.biz.mapper.**")
@SpringBootApplication(scanBasePackages = {"com.temp"})
public class SpringBootTempApplication {

    public static void main(String[] args) {
        //下面语句使得日志输出使用异步处理，减小输出日志对性能的影响
        System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
        SpringApplication.run(SpringBootTempApplication.class, args);
    }

}
