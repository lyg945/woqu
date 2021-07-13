package com.paat.oa.woqu.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.paat.oa.woqu.service.OAService;
import com.paat.oa.woqu.UserAccountEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.HttpCookie;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

@Service
@Slf4j
public class OAServiceImpl implements OAService {

    @Override
    public void pullOA(int type) {
        log.info("开始检查拉取打卡记录:"+DateUtil.now());
        //链式构建请求
        Map map = new HashMap<>();
        map.put("year", DateUtil.format(new Date(), "yyyy"));
        map.put("month",DateUtil.format(new Date(), "MM"));

        List<String> students = FileUtil.readUtf8Lines("/opt/student.txt");

        students.forEach(x->{
            String [] array = x.split(",");
            log.info("************************切换员工****************************");
            log.info(JSONUtil.toJsonStr(array));
            HttpCookie cookie = new HttpCookie("_wcf_", array[3]);
            HttpCookie cookie2 = new HttpCookie("_wcfl_",array[2]);
            String result = HttpRequest.post("https://app.woqu365.com/app/employee/myAttendanceData.htm")
                    .header(Header.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit /537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36")//头信息，多个头信息多次调用此方法即可
                    .cookie(cookie,cookie2)
                    .form(map)
                    .timeout(20000)//超时，毫秒
                    .execute().body();

            sendMsg(type,array[0],array[1],result);
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void pullMall() {
        dataSend("同志们，周五了，发周报了");
    }




















    @Override
    public void checkCaiWu() {
        if(!checkResult("http://180.167.151.213:12363/")){
            send("财务纸质发票-http://180.167.151.213:12363/  拒绝访问");
        }

        if(!checkResult("http://180.167.151.213:4040/")){
            send("财务快递单号-http://180.167.151.213:4040/  拒绝访问");
        }

        if(!checkResult("http://180.167.151.213:4445/")){
            send("财务快递单号-http://180.167.151.213:4445/  拒绝访问");
        }

        if(!checkResult("http://180.167.151.213:12364/")){
            send("财务电子发票-http://180.167.151.213:12364/  拒绝访问");
        }
    }

    //指定必须有6个运动员到达才行
    private static CyclicBarrier barrier = new CyclicBarrier(10, () -> {
        System.out.println("所有运动员入场，裁判员一声令下！！！！！");
    });

    @Override
    public void checkJyb() {
//        System.out.println("运动员准备进场，全场欢呼............");
//        ExecutorService service = Executors.newFixedThreadPool(10);
//        for (int i = 0; i < 10; i++) {
//            service.submit(() -> {
//                try {
//                    System.out.println(Thread.currentThread().getName() + " 运动员，进场");
//                    barrier.await();
//                    System.out.println(getJyb());
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (BrokenBarrierException e) {
//                    e.printStackTrace();
//                }
//            });
//        }
        getJyb();
    }

    private int getJyb() {
        String str ="https://www.jieyuanbao.com";
        str ="https://ygy.paat.com";
        str ="https://www.paat.com";
        str ="http://www.jieshui8.com";
        str ="https://www.zhaoshang88.net";
        str ="https://www.jieyuanbao.com";
        str ="https://www.jieyuanbao.vip";

        int status = HttpRequest.get(str).execute().getStatus();
        System.out.println(status);
//        log.info("_________jyb_________{}",status);
        if(status != 200){
            send("捷园宝网站挂了，请求返回："+status);
            log.info("_________jyb_________{}",status);
        }
        return status;
    }


    public void sendMsg(int type, String userId,String studentName, String KaoQinResult){
        log.info("{} ,{}, - 开始检查-打卡记录:{}",DateUtil.now(),studentName,KaoQinResult);
        if(!StringUtils.isEmpty(KaoQinResult)){
            JSONArray days = JSONUtil.parseObj(KaoQinResult).getJSONObject("data").getJSONObject("attendance").getJSONArray("days");
            for (Object day : days) {
                JSONObject obj = (JSONObject) day;
                if(DateUtil.format(new Date(), "yyyy-MM-dd").equals(obj.getStr("AFormatDay"))
                        && "workday".equals(obj.getStr("dayType"))
                ){
                    String start = obj.getStr("workStart");
                    String end = obj.getStr("workEnd");
                    long startTime = date2TimeStamp(obj.getStr("AFormatDay")+" 10:00:00");
                    long endTime = date2TimeStamp(obj.getStr("AFormatDay")+" 18:30:00");
                    String msg = "";

                    log.info(String.format("%s-%s-%s-%s-%s",obj.getStr("AFormatDay"),start,startTime,end,endTime));

                    if(type == 1 && StringUtils.isEmpty(start)){
                        msg = studentName+"-"+obj.getStr("AFormatDay")+"早上-漏打卡";
                    }else if(type == 1 &&  Long.valueOf(start).longValue() >= startTime ){
                        msg = studentName+"-10点以后打卡："+timeStamp2Date(Long.valueOf(start));
                    }else if(type == 2 && StringUtils.isEmpty(end)){
                        msg = studentName+"-"+obj.getStr("AFormatDay")+"晚上-漏打卡";
                    }else if(type == 2 && Long.valueOf(end).longValue() <= endTime){
                        msg = studentName+"-18点30前打卡："+timeStamp2Date(Long.valueOf(end));
                    }

                    if(!StringUtils.isEmpty(msg)){
                        send(msg,userId);
                    }
                }
            }
        }
    }

    private boolean checkResult(String s) {
        int flag = 0;
        try {
            flag = HttpRequest.get(s).execute().getStatus();
        } catch (Exception e) {
            flag = 0;
        }
        return flag == 200;
    }

    private void send(String content,String userId){
        JSONObject json = JSONUtil.parseObj("{\"msgtype\":\"text\"," +
                "\"text\":{\"mentioned_list\":[\""+userId+"\"],\"content\":\""+content+"\"}}");
        HttpRequest.post(
//                "https://oapi.dingtalk.com/robot/send?access_token=20135a9b5e01427146ac92d25374a888f5060e28ae90ef9ed055d460bf95e806"
                "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=1e8097a0-f042-4bc5-a532-7cfa47350f0d"
        )
                .body(json.toString())
                .timeout(20000)//超时，毫秒
                .execute().body();
    }
    private void send(String content){
        JSONObject json = JSONUtil.parseObj("{\"msgtype\":\"text\"," +
                "\"text\":{\"content\":\""+content+"\"}}");
        HttpRequest.post(
//                "https://oapi.dingtalk.com/robot/send?access_token=20135a9b5e01427146ac92d25374a888f5060e28ae90ef9ed055d460bf95e806"
                "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=1e8097a0-f042-4bc5-a532-7cfa47350f0d"
        )
                .body(json.toString())
                .timeout(20000)//超时，毫秒
                .execute().body();
    }

    private void dataSend(String content){
        JSONObject json = JSONUtil.parseObj("{\"msgtype\":\"markdown\"," +
                "\"markdown\":{\"content\":\""+content+"\"}}");
        HttpRequest.post("https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=825a3612-b0f3-4ae9-ac27-84f493495b68")
                .body(json.toString())
                .timeout(20000)//超时，毫秒
                .execute().body();
    }


   public static long date2TimeStamp(String date_str){
         try {
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return Long.valueOf(sdf.parse(date_str).getTime());
     } catch (Exception e) {
            e.printStackTrace();
     }
       return 0;
   }

    public static String timeStamp2Date(long time){
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(new Date(time)); // 时间戳转换日期
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
