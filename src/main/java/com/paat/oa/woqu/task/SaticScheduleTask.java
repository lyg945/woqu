package com.paat.oa.woqu.task;

import cn.hutool.core.io.FileUtil;
import com.paat.oa.woqu.service.OAService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Configuration
@EnableScheduling
public class SaticScheduleTask {

    @Resource
    OAService oaService;

//    @Scheduled(cron = "5-10 * * * * ?")
    private void task0() {
        oaService.pullOA();
    }

    //2019-12-16 10:00:00
    //2019-12-16 10:30:00
    //2019-12-16 11:00:00
    //2019-12-16 11:30:00
    @Scheduled(cron = "0 0,30 10,11 * * ?")
    private void task1() {
        oaService.pullOA();
    }

    //2019-12-16 10:00:00
    //2019-12-16 10:10:00
    //2019-12-16 10:20:00
    //2019-12-16 10:30:00
    //2019-12-16 10:40:00
    //2019-12-16 10:50:00
    @Scheduled(cron = "0 0/10 10 * * ?")
    private void task2() {
        oaService.sendMsg(1);
    }

    //2019-12-16 22:00:00
    //2019-12-16 22:30:00
    //2019-12-16 23:00:00
    //2019-12-16 23:30:00
    @Scheduled(cron = "0 0,30 22,23 * * ?")
    private void task3() {
        oaService.pullOA();
    }

    //2019-12-16 22:00:00
    //2019-12-16 22:10:00
    //2019-12-16 22:20:00
    //2019-12-16 22:30:00
    //2019-12-16 22:40:00
    //2019-12-16 22:50:00
    @Scheduled(cron = "0 0/10 22 * * ?")
//    @Scheduled(cron = "5-10 * * * * ?")
    private void task4() {
        oaService.sendMsg(2);
    }
}