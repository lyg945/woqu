package com.paat.oa.woqu.task;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Configuration
@EnableScheduling
public class SaticScheduleTask {

    @Scheduled(cron = "* 30 10 * * ?")
    private void task1() {
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
    }

    @Scheduled(cron = "* 50 10 * * ?")
    private void task2() {
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
    }

    @Scheduled(cron = "* 30 10 * * ?")
    private void task3() {
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
    }

    @Scheduled(cron = "* 30 10 * * ?")
    private void task4() {
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
    }
}