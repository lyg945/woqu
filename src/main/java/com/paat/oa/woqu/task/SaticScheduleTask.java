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

    //1分鐘一次
//    @Scheduled(cron = "0 0/1 * * * ? ")
    //每周四 9 10 11 14 15 16 点
    @Scheduled(cron = "0 0 9,10,11,14,15,16,17 ? 1-12 5 *")
    private void task() {
        oaService.checkCaiWu();
    }

    //1分鐘一次
//    @Scheduled(cron = "0 0/1 * * * ? ")
    private void task0() {
        oaService.pullOA(1);
    }

    //2019-12-16 9:50  9:55
    @Scheduled(cron = "0 50,55 9 * * ? ")
    private void task1() {
        oaService.pullOA(1);
    }

    //2019-12-16 10:00 10:30
    @Scheduled(cron = "0 0,30 10 * * ? ")
    private void task2() {
        oaService.pullOA(1);
    }


    //2019-12-16 22:00 22:30
    @Scheduled(cron = "0 0,3 22 * * ? ")
    private void task3() {
        oaService.pullOA(2);
    }


    //2019-12-16 23:00 23:30
    @Scheduled(cron = "0 0,30 23 * * ? ")
    private void task4() {
        oaService.pullOA(2);
    }
}