package com.temp.task;

import lombok.extern.log4j.Log4j2;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务模板
 *
 * @author Hollis
 * @since 2024-03-19 22:15
 */
@Log4j2
@Component
public class TempTask {

    @Scheduled(cron = "${task.cron.temp:-}", zone = "GMT+8")
    public void process() {
        try {
            MDC.put("clientId", "TempTask");
            // do something
        } catch (Exception e) {
            log.error("TempTask#process has error, errorMessage: {}", e.getMessage(), e);
        } finally {
            MDC.put("clientId", null);
        }
    }
}
