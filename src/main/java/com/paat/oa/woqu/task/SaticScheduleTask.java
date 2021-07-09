package com.paat.oa.woqu.task;

import com.paat.oa.woqu.service.OAService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

@Configuration
@EnableScheduling
public class SaticScheduleTask {

    @Resource
    OAService oaService;

//    @Scheduled(cron = "0/1 * * * * ?")
    private void task11() {
        oaService.checkJyb();
        oaService.checkCaiWu();
    }

//    @Scheduled(cron = "* 4 * * *")
    private void mail(){
        oaService.pullMall();
    }





    //2021-07-10 09:50:00
    //2021-07-10 09:55:00
    @Scheduled(cron = "0 50,55 9 * * ? *")
    private void task1() {
        oaService.pullOA(1);
    }

    //2021-07-10 10:00:00
    //2021-07-10 10:30:00
    //2021-07-10 10:50:00
    @Scheduled(cron = "0 0,30,50 10 * * ? *")
    private void task2() {
        oaService.pullOA(1);
    }

    //2021-07-09 18:00:00
    //2021-07-09 18:30:00
    //2021-07-09 18:50:00
    @Scheduled(cron = "0 0,30,50 18 * * ? *")
    private void task3() {
        oaService.pullOA(2);
    }

    //2021-07-10 20:00:00
    //2021-07-10 20:33:00
    //2021-07-10 20:40:00
    @Scheduled(cron = "0 0,33,40 20 * * ? *")
    private void task4() {
        oaService.pullOA(2);
    }

    //2021-07-09 22:00:00
    //2021-07-09 22:05:00
    //2021-07-09 22:30:00
    @Scheduled(cron = "0 0,5,30 22 * * ? *")
    private void task5() {
        oaService.pullOA(2);
    }

}