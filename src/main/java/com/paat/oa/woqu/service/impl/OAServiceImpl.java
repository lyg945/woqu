package com.paat.oa.woqu.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.paat.oa.woqu.service.OAService;
import com.paat.oa.woqu.UserAccountEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.net.HttpCookie;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class OAServiceImpl implements OAService {

    @Override
    public void pullOA() {
        log.info("开始检查拉取打卡记录:"+DateUtil.now());
        //链式构建请求
        Map map = new HashMap<>();
        map.put("year", DateUtil.format(new Date(), "yyyy"));
        map.put("month",DateUtil.format(new Date(), "MM"));

        Arrays.stream(UserAccountEnum.values()).forEach(x->{
            log.info(JSONUtil.toJsonStr(x));
            HttpCookie cookie = new HttpCookie("_wcf_", x.get_wcf_());
            HttpCookie cookie2 = new HttpCookie("_wcfl_",x.get_wcfl_());
            String result = HttpRequest.post("https://app.woqu365.com/app/employee/myAttendanceData.htm")
                    .header(Header.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit /537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36")//头信息，多个头信息多次调用此方法即可
                    .cookie(cookie,cookie2)
                    .form(map)
                    .timeout(20000)//超时，毫秒
                    .execute().body();

            sendMsg(x.getName(),result);
        });
    }

    public void sendMsg(String str,String message){
        log.info(str+" - 开始检查-打卡记录:"+DateUtil.now());
        JSONArray days = JSONUtil.parseObj(message).getJSONObject("data").
                getJSONObject("attendance").getJSONArray("days");
        for (Object day : days) {
            JSONObject obj = (JSONObject) day;
            if(DateUtil.format(new Date(), "yyyy-MM-dd").equals(obj.getStr("AFormatDay"))
                    && "workday".equals(obj.getStr("dayType"))){
                String time = "";
                if(StringUtils.isEmpty(obj.getStr("workStart"))){
                    time = "上午";
                }
                if(StringUtils.isEmpty(obj.getStr("workEnd"))){
                    time = "下午";
                }
                send(str,obj.getStr("AFormatDay"),time);
            }
        }
    }

    private void send(String name,String date,String day){
        log.info("姓名：%s 日期：%s 班次：%s",name,date,day);
        JSONObject json = JSONUtil.parseObj("{\"msgtype\":\"markdown\"," +
                "\"markdown\":{\"content\":\""+name+"—"+date+"—<font color=\\\"warning\\\">未打"+day+"卡</font>\"}}");
        HttpRequest.post("https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=6f8fee88-0281-4da8-b1be-d120d296d606")
                .body(json.toString())
                .timeout(20000)//超时，毫秒
                .execute().body();
    }
}
