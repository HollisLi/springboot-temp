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
@MapperScan("com.temp.mapper.**")
@SpringBootApplication(scanBasePackages = {"com.temp"})
public class SpringBootTempApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTempApplication.class, args);
    }

}
