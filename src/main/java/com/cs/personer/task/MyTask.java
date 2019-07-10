package com.cs.personer.task;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author Jack
 */
@Slf4j
@Component
public class MyTask {

    @Scheduled(fixedDelay = 10 * 1000 /**ms**/, initialDelay = 10 * 1000)
//    @SchedulerLock(name = "scheduledTaskName")
    public void reportCurrentTime() {
        if (false) {
            log.info("现在时间：" + LocalDateTime.now());
        }

    }
}

