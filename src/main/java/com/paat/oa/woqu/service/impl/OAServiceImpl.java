package com.paat.oa.woqu.service.impl;

import cn.hutool.core.date.DateUtil;
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
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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

        Arrays.stream(UserAccountEnum.values()).forEach(x->{
            log.info("************************切换员工****************************");
            log.info(JSONUtil.toJsonStr(x));
            HttpCookie cookie = new HttpCookie("_wcf_", x.get_wcf_());
            HttpCookie cookie2 = new HttpCookie("_wcfl_",x.get_wcfl_());
            String result = HttpRequest.post("https://app.woqu365.com/app/employee/myAttendanceData.htm")
                    .header(Header.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit /537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36")//头信息，多个头信息多次调用此方法即可
                    .cookie(cookie,cookie2)
                    .form(map)
                    .timeout(20000)//超时，毫秒
                    .execute().body();

            sendMsg(type,x.getName(),result);
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
//        sendMsg("习明聪",JSONUtil.toJsonStr("{\"result\":0,\"data\":{\"showSignTime\":true,\"noSignShowTitle\":true,\"gmtEmployed\":\"2016-09-25 00:00:00\",\"showOvertimeRange\":true,\"menus\":[{\"identity\":\"workdays\",\"style\":\"woqu-daySun\",\"title\":\"排班天数\"},{\"identity\":\"gooutdays\",\"style\":\"woqu-goout\",\"title\":\"外出天数\"},{\"identity\":\"tripdays\",\"style\":\"woqu-trip\",\"title\":\"出差天数\"},{\"identity\":\"overtimedays\",\"style\":\"woqu-overtime\",\"title\":\"加班工时\"},{\"identity\":\"leavedays\",\"style\":\"woqu-leave\",\"title\":\"请假天数\"},{\"identity\":\"latedays\",\"style\":\"woqu-late_early\",\"title\":\"迟到次数\"},{\"identity\":\"earlydays\",\"style\":\"woqu-late_early\",\"title\":\"早退次数\"},{\"identity\":\"nosigndays\",\"style\":\"woqu-no_sign\",\"title\":\"漏打卡\"},{\"identity\":\"legworktimes\",\"style\":\"woqu-legwork_sign\",\"title\":\"外勤次数\"}],\"attendance\":{\"dayDetails\":[],\"dayUnitList\":[{\"dateMillionSeconds\":1593446400000,\"minutes\":480},{\"dateMillionSeconds\":1593532800000,\"minutes\":480},{\"dateMillionSeconds\":1593619200000,\"minutes\":480},{\"dateMillionSeconds\":1593705600000,\"minutes\":480},{\"dateMillionSeconds\":1593792000000,\"minutes\":480},{\"dateMillionSeconds\":1593878400000,\"minutes\":480},{\"dateMillionSeconds\":1593964800000,\"minutes\":480},{\"dateMillionSeconds\":1594051200000,\"minutes\":480},{\"dateMillionSeconds\":1594137600000,\"minutes\":480},{\"dateMillionSeconds\":1594224000000,\"minutes\":480},{\"dateMillionSeconds\":1594310400000,\"minutes\":480},{\"dateMillionSeconds\":1594396800000,\"minutes\":480},{\"dateMillionSeconds\":1594483200000,\"minutes\":480},{\"dateMillionSeconds\":1594569600000,\"minutes\":480},{\"dateMillionSeconds\":1594656000000,\"minutes\":480},{\"dateMillionSeconds\":1594742400000,\"minutes\":480},{\"dateMillionSeconds\":1594828800000,\"minutes\":480},{\"dateMillionSeconds\":1594915200000,\"minutes\":480},{\"dateMillionSeconds\":1595001600000,\"minutes\":480},{\"dateMillionSeconds\":1595088000000,\"minutes\":480},{\"dateMillionSeconds\":1595174400000,\"minutes\":480},{\"dateMillionSeconds\":1595260800000,\"minutes\":480},{\"dateMillionSeconds\":1595347200000,\"minutes\":480},{\"dateMillionSeconds\":1595433600000,\"minutes\":480},{\"dateMillionSeconds\":1595520000000,\"minutes\":480},{\"dateMillionSeconds\":1595606400000,\"minutes\":480},{\"dateMillionSeconds\":1595692800000,\"minutes\":480},{\"dateMillionSeconds\":1595779200000,\"minutes\":480},{\"dateMillionSeconds\":1595865600000,\"minutes\":480},{\"dateMillionSeconds\":1595952000000,\"minutes\":480},{\"dateMillionSeconds\":1596038400000,\"minutes\":480},{\"dateMillionSeconds\":1596124800000,\"minutes\":480},{\"dateMillionSeconds\":1596211200000,\"minutes\":480}],\"days\":[{\"AFormatDay\":\"2020-06-28\",\"customDays\":0,\"date\":1593273600000,\"delayWorkTime\":0,\"early\":0,\"early1\":0,\"early2\":0,\"early3\":0,\"early4\":0,\"earlyClearup\":false,\"earlyClearup1\":false,\"earlyClearup2\":false,\"earlyClearup3\":false,\"earlyClearup4\":false,\"goout\":false,\"late\":0,\"late1\":0,\"late2\":0,\"late3\":0,\"late4\":0,\"lateClearup\":false,\"lateClearup1\":false,\"lateClearup2\":false,\"lateClearup3\":false,\"lateClearup4\":false,\"leave\":false,\"needSignIn\":false,\"nosignTime\":0,\"outWorkTime\":0,\"overtime\":false,\"removeEarlyMinute\":0,\"removeEarlyTime\":0,\"removeLateMinute\":0,\"removeLateTime\":0,\"shiftVersion\":0,\"timeRange\":0,\"trip\":false,\"workTime\":0},{\"AFormatDay\":\"2020-06-29\",\"customDays\":0,\"date\":1593360000000,\"delayWorkTime\":0,\"early\":0,\"early1\":0,\"early2\":0,\"early3\":0,\"early4\":0,\"earlyClearup\":false,\"earlyClearup1\":false,\"earlyClearup2\":false,\"earlyClearup3\":false,\"earlyClearup4\":false,\"goout\":false,\"late\":0,\"late1\":0,\"late2\":0,\"late3\":0,\"late4\":0,\"lateClearup\":false,\"lateClearup1\":false,\"lateClearup2\":false,\"lateClearup3\":false,\"lateClearup4\":false,\"leave\":false,\"needSignIn\":false,\"nosignTime\":0,\"outWorkTime\":0,\"overtime\":false,\"removeEarlyMinute\":0,\"removeEarlyTime\":0,\"removeLateMinute\":0,\"removeLateTime\":0,\"shiftVersion\":0,\"timeRange\":0,\"trip\":false,\"workTime\":0},{\"AFormatDay\":\"2020-06-30\",\"customDays\":0,\"date\":1593446400000,\"delayWorkTime\":0,\"early\":0,\"early1\":0,\"early2\":0,\"early3\":0,\"early4\":0,\"earlyClearup\":false,\"earlyClearup1\":false,\"earlyClearup2\":false,\"earlyClearup3\":false,\"earlyClearup4\":false,\"goout\":false,\"late\":0,\"late1\":0,\"late2\":0,\"late3\":0,\"late4\":0,\"lateClearup\":false,\"lateClearup1\":false,\"lateClearup2\":false,\"lateClearup3\":false,\"lateClearup4\":false,\"leave\":false,\"needSignIn\":false,\"nosignTime\":0,\"outWorkTime\":0,\"overtime\":false,\"removeEarlyMinute\":0,\"removeEarlyTime\":0,\"removeLateMinute\":0,\"removeLateTime\":0,\"shiftVersion\":0,\"timeRange\":0,\"trip\":false,\"workTime\":0},{\"AFormatDay\":\"2020-07-01\",\"customDays\":1,\"date\":1593532800000,\"dayType\":\"workday\",\"delayWorkTime\":0,\"early\":30,\"early1\":30,\"early2\":0,\"early3\":0,\"early4\":0,\"earlyClearup\":false,\"earlyClearup1\":false,\"earlyClearup2\":false,\"earlyClearup3\":false,\"earlyClearup4\":false,\"goout\":false,\"late\":142,\"late1\":142,\"late2\":0,\"late3\":0,\"late4\":0,\"lateClearup\":false,\"lateClearup1\":false,\"lateClearup2\":false,\"lateClearup3\":false,\"lateClearup4\":false,\"leave\":false,\"needSignIn\":true,\"nosignTime\":0,\"outWorkTime\":480,\"overtime\":false,\"removeEarlyMinute\":0,\"removeEarlyTime\":0,\"removeLateMinute\":0,\"removeLateTime\":0,\"shiftId\":7,\"shiftType\":\"office\",\"shiftVersion\":2,\"timeRange\":1,\"trip\":false,\"workEnd\":1593603042000,\"workEnd1\":1593603042000,\"workStart\":1593584573000,\"workStart1\":1593584573000,\"workTime\":309},{\"AFormatDay\":\"2020-07-02\",\"customDays\":1,\"date\":1593619200000,\"dayType\":\"workday\",\"delayWorkTime\":0,\"early\":0,\"early1\":0,\"early2\":0,\"early3\":0,\"early4\":0,\"earlyClearup\":false,\"earlyClearup1\":false,\"earlyClearup2\":false,\"earlyClearup3\":false,\"earlyClearup4\":false,\"goout\":false,\"late\":0,\"late1\":0,\"late2\":0,\"late3\":0,\"late4\":0,\"lateClearup\":false,\"lateClearup1\":false,\"lateClearup2\":false,\"lateClearup3\":false,\"lateClearup4\":false,\"leave\":false,\"needSignIn\":true,\"nosignTime\":0,\"outWorkTime\":480,\"overtime\":false,\"removeEarlyMinute\":0,\"removeEarlyTime\":0,\"removeLateMinute\":0,\"removeLateTime\":0,\"shiftId\":7,\"shiftType\":\"office\",\"shiftVersion\":2,\"timeRange\":1,\"trip\":false,\"workEnd\":1593700164000,\"workEnd1\":1593700164000,\"workStart\":1593653449000,\"workStart1\":1593653449000,\"workTime\":719},{\"AFormatDay\":\"2020-07-03\",\"customDays\":1,\"date\":1593705600000,\"dayType\":\"workday\",\"delayWorkTime\":0,\"early\":0,\"early1\":0,\"early2\":0,\"early3\":0,\"early4\":0,\"earlyClearup\":false,\"earlyClearup1\":false,\"earlyClearup2\":false,\"earlyClearup3\":false,\"earlyClearup4\":false,\"goout\":false,\"late\":0,\"late1\":0,\"late2\":0,\"late3\":0,\"late4\":0,\"lateClearup\":false,\"lateClearup1\":false,\"lateClearup2\":false,\"lateClearup3\":false,\"lateClearup4\":false,\"leave\":false,\"needSignIn\":true,\"nosignTime\":0,\"outWorkTime\":480,\"overtime\":false,\"removeEarlyMinute\":0,\"removeEarlyTime\":0,\"removeLateMinute\":0,\"removeLateTime\":0,\"shiftId\":7,\"shiftType\":\"office\",\"shiftVersion\":2,\"timeRange\":1,\"trip\":false,\"workEnd\":1593776664000,\"workEnd1\":1593776664000,\"workStart\":1593741032000,\"workStart1\":1593741032000,\"workTime\":534},{\"AFormatDay\":\"2020-07-04\",\"customDays\":0,\"date\":1593792000000,\"dayType\":\"offday\",\"delayWorkTime\":0,\"early\":0,\"early1\":0,\"early2\":0,\"early3\":0,\"early4\":0,\"earlyClearup\":false,\"earlyClearup1\":false,\"earlyClearup2\":false,\"earlyClearup3\":false,\"earlyClearup4\":false,\"goout\":false,\"late\":0,\"late1\":0,\"late2\":0,\"late3\":0,\"late4\":0,\"lateClearup\":false,\"lateClearup1\":false,\"lateClearup2\":false,\"lateClearup3\":false,\"lateClearup4\":false,\"leave\":false,\"needSignIn\":false,\"nosignTime\":0,\"outWorkTime\":0,\"overtime\":false,\"removeEarlyMinute\":0,\"removeEarlyTime\":0,\"removeLateMinute\":0,\"removeLateTime\":0,\"shiftId\":0,\"shiftVersion\":0,\"timeRange\":0,\"trip\":false,\"workTime\":0},{\"AFormatDay\":\"2020-07-05\",\"customDays\":0,\"date\":1593878400000,\"dayType\":\"offday\",\"delayWorkTime\":0,\"early\":0,\"early1\":0,\"early2\":0,\"early3\":0,\"early4\":0,\"earlyClearup\":false,\"earlyClearup1\":false,\"earlyClearup2\":false,\"earlyClearup3\":false,\"earlyClearup4\":false,\"goout\":false,\"late\":0,\"late1\":0,\"late2\":0,\"late3\":0,\"late4\":0,\"lateClearup\":false,\"lateClearup1\":false,\"lateClearup2\":false,\"lateClearup3\":false,\"lateClearup4\":false,\"leave\":false,\"needSignIn\":false,\"nosignTime\":0,\"outWorkTime\":0,\"overtime\":false,\"removeEarlyMinute\":0,\"removeEarlyTime\":0,\"removeLateMinute\":0,\"removeLateTime\":0,\"shiftId\":0,\"shiftVersion\":0,\"timeRange\":0,\"trip\":false,\"workTime\":0},{\"AFormatDay\":\"2020-07-06\",\"customDays\":1,\"date\":1593964800000,\"dayType\":\"workday\",\"delayWorkTime\":0,\"early\":0,\"early1\":0,\"early2\":0,\"early3\":0,\"early4\":0,\"earlyClearup\":false,\"earlyClearup1\":false,\"earlyClearup2\":false,\"earlyClearup3\":false,\"earlyClearup4\":false,\"goout\":false,\"late\":0,\"late1\":0,\"late2\":0,\"late3\":0,\"late4\":0,\"lateClearup\":false,\"lateClearup1\":false,\"lateClearup2\":false,\"lateClearup3\":false,\"lateClearup4\":false,\"leave\":false,\"needSignIn\":true,\"nosignTime\":0,\"outWorkTime\":480,\"overtime\":false,\"removeEarlyMinute\":0,\"removeEarlyTime\":0,\"removeLateMinute\":0,\"removeLateTime\":0,\"shiftId\":7,\"shiftType\":\"office\",\"shiftVersion\":2,\"timeRange\":1,\"trip\":false,\"workEnd\":1594039555000,\"workEnd1\":1594039555000,\"workStart\":1593999975000,\"workStart1\":1593999975000,\"workTime\":599},{\"AFormatDay\":\"2020-07-07\",\"customDays\":1,\"date\":1594051200000,\"dayType\":\"workday\",\"delayWorkTime\":0,\"early\":0,\"early1\":0,\"early2\":0,\"early3\":0,\"early4\":0,\"earlyClearup\":false,\"earlyClearup1\":false,\"earlyClearup2\":false,\"earlyClearup3\":false,\"earlyClearup4\":false,\"goout\":false,\"late\":0,\"late1\":0,\"late2\":0,\"late3\":0,\"late4\":0,\"lateClearup\":false,\"lateClearup1\":false,\"lateClearup2\":false,\"lateClearup3\":false,\"lateClearup4\":false,\"leave\":false,\"needSignIn\":true,\"nosignTime\":0,\"outWorkTime\":480,\"overtime\":false,\"removeEarlyMinute\":0,\"removeEarlyTime\":0,\"removeLateMinute\":0,\"removeLateTime\":0,\"shiftId\":7,\"shiftType\":\"office\",\"shiftVersion\":2,\"timeRange\":1,\"trip\":false,\"workEnd\":1594130888000,\"workEnd1\":1594130888000,\"workStart\":1594085736000,\"workStart1\":1594085736000,\"workTime\":693},{\"AFormatDay\":\"2020-07-08\",\"customDays\":1,\"date\":1594137600000,\"dayType\":\"workday\",\"delayWorkTime\":0,\"early\":0,\"early1\":0,\"early2\":0,\"early3\":0,\"early4\":0,\"earlyClearup\":false,\"earlyClearup1\":false,\"earlyClearup2\":false,\"earlyClearup3\":false,\"earlyClearup4\":false,\"goout\":false,\"late\":0,\"late1\":0,\"late2\":0,\"late3\":0,\"late4\":0,\"lateClearup\":false,\"lateClearup1\":false,\"lateClearup2\":false,\"lateClearup3\":false,\"lateClearup4\":false,\"leave\":false,\"needSignIn\":true,\"nosignTime\":0,\"outWorkTime\":480,\"overtime\":false,\"removeEarlyMinute\":0,\"removeEarlyTime\":0,\"removeLateMinute\":0,\"removeLateTime\":0,\"shiftId\":7,\"shiftType\":\"office\",\"shiftVersion\":2,\"timeRange\":1,\"trip\":false,\"workEnd\":1594216971000,\"workEnd1\":1594216971000,\"workStart\":1594172784000,\"workStart1\":1594172784000,\"workTime\":676},{\"AFormatDay\":\"2020-07-09\",\"customDays\":1,\"date\":1594224000000,\"dayType\":\"workday\",\"delayWorkTime\":0,\"early\":0,\"early1\":0,\"early2\":0,\"early3\":0,\"early4\":0,\"earlyClearup\":false,\"earlyClearup1\":false,\"earlyClearup2\":false,\"earlyClearup3\":false,\"earlyClearup4\":false,\"goout\":false,\"late\":0,\"late1\":0,\"late2\":0,\"late3\":0,\"late4\":0,\"lateClearup\":false,\"lateClearup1\":false,\"lateClearup2\":false,\"lateClearup3\":false,\"lateClearup4\":false,\"leave\":false,\"needSignIn\":true,\"nosignTime\":0,\"outWorkTime\":480,\"overtime\":false,\"removeEarlyMinute\":0,\"removeEarlyTime\":0,\"removeLateMinute\":0,\"removeLateTime\":0,\"shiftId\":7,\"shiftType\":\"office\",\"shiftVersion\":2,\"timeRange\":1,\"trip\":false,\"workEnd\":1594305127000,\"workEnd1\":1594305127000,\"workStart\":1594259264000,\"workStart1\":1594259264000,\"workTime\":705},{\"AFormatDay\":\"2020-07-10\",\"customDays\":1,\"date\":1594310400000,\"dayType\":\"workday\",\"delayWorkTime\":0,\"early\":0,\"early1\":0,\"early2\":0,\"early3\":0,\"early4\":0,\"earlyClearup\":false,\"earlyClearup1\":false,\"earlyClearup2\":false,\"earlyClearup3\":false,\"earlyClearup4\":false,\"goout\":false,\"late\":0,\"late1\":0,\"late2\":0,\"late3\":0,\"late4\":0,\"lateClearup\":false,\"lateClearup1\":false,\"lateClearup2\":false,\"lateClearup3\":false,\"lateClearup4\":false,\"leave\":false,\"needSignIn\":true,\"nosignTime\":0,\"outWorkTime\":480,\"overtime\":false,\"removeEarlyMinute\":0,\"removeEarlyTime\":0,\"removeLateMinute\":0,\"removeLateTime\":0,\"shiftId\":7,\"shiftType\":\"office\",\"shiftVersion\":2,\"timeRange\":1,\"trip\":false,\"workEnd\":1594394637000,\"workEnd1\":1594394637000,\"workStart\":1594345398000,\"workStart1\":1594345398000,\"workTime\":760},{\"AFormatDay\":\"2020-07-11\",\"customDays\":0,\"date\":1594396800000,\"dayType\":\"offday\",\"delayWorkTime\":0,\"early\":0,\"early1\":0,\"early2\":0,\"early3\":0,\"early4\":0,\"earlyClearup\":false,\"earlyClearup1\":false,\"earlyClearup2\":false,\"earlyClearup3\":false,\"earlyClearup4\":false,\"goout\":false,\"late\":0,\"late1\":0,\"late2\":0,\"late3\":0,\"late4\":0,\"lateClearup\":false,\"lateClearup1\":false,\"lateClearup2\":false,\"lateClearup3\":false,\"lateClearup4\":false,\"leave\":false,\"needSignIn\":false,\"nosignTime\":0,\"outWorkTime\":0,\"overtime\":false,\"removeEarlyMinute\":0,\"removeEarlyTime\":0,\"removeLateMinute\":0,\"removeLateTime\":0,\"shiftId\":0,\"shiftVersion\":0,\"timeRange\":0,\"trip\":false,\"workEnd\":1594464479000,\"workEnd1\":1594464479000,\"workStart\":1594451961000,\"workStart1\":1594451961000,\"workTime\":208},{\"AFormatDay\":\"2020-07-12\",\"customDays\":0,\"date\":1594483200000,\"dayType\":\"offday\",\"delayWorkTime\":0,\"early\":0,\"early1\":0,\"early2\":0,\"early3\":0,\"early4\":0,\"earlyClearup\":false,\"earlyClearup1\":false,\"earlyClearup2\":false,\"earlyClearup3\":false,\"earlyClearup4\":false,\"goout\":false,\"late\":0,\"late1\":0,\"late2\":0,\"late3\":0,\"late4\":0,\"lateClearup\":false,\"lateClearup1\":false,\"lateClearup2\":false,\"lateClearup3\":false,\"lateClearup4\":false,\"leave\":false,\"needSignIn\":false,\"nosignTime\":0,\"outWorkTime\":0,\"overtime\":false,\"removeEarlyMinute\":0,\"removeEarlyTime\":0,\"removeLateMinute\":0,\"removeLateTime\":0,\"shiftId\":0,\"shiftVersion\":0,\"timeRange\":0,\"trip\":false,\"workTime\":0},{\"AFormatDay\":\"2020-07-13\",\"customDays\":1,\"date\":1594569600000,\"dayType\":\"workday\",\"delayWorkTime\":0,\"early\":0,\"early1\":0,\"early2\":0,\"early3\":0,\"early4\":0,\"earlyClearup\":false,\"earlyClearup1\":false,\"earlyClearup2\":false,\"earlyClearup3\":false,\"earlyClearup4\":false,\"goout\":false,\"late\":0,\"late1\":0,\"late2\":0,\"late3\":0,\"late4\":0,\"lateClearup\":false,\"lateClearup1\":false,\"lateClearup2\":false,\"lateClearup3\":false,\"lateClearup4\":false,\"leave\":false,\"needSignIn\":true,\"nosignTime\":0,\"outWorkTime\":480,\"overtime\":false,\"removeEarlyMinute\":0,\"removeEarlyTime\":0,\"removeLateMinute\":0,\"removeLateTime\":0,\"shiftId\":7,\"shiftType\":\"office\",\"shiftVersion\":2,\"timeRange\":1,\"trip\":false,\"workEnd\":1594648957000,\"workEnd1\":1594648957000,\"workStart\":1594604031000,\"workStart1\":1594604031000,\"workTime\":689},{\"AFormatDay\":\"2020-07-14\",\"customDays\":1,\"date\":1594656000000,\"dayType\":\"workday\",\"delayWorkTime\":0,\"early\":0,\"early1\":0,\"early2\":0,\"early3\":0,\"early4\":0,\"earlyClearup\":false,\"earlyClearup1\":false,\"earlyClearup2\":false,\"earlyClearup3\":false,\"earlyClearup4\":false,\"goout\":false,\"late\":0,\"late1\":0,\"late2\":0,\"late3\":0,\"late4\":0,\"lateClearup\":false,\"lateClearup1\":false,\"lateClearup2\":false,\"lateClearup3\":false,\"lateClearup4\":false,\"leave\":false,\"needSignIn\":true,\"nosignTime\":0,\"outWorkTime\":480,\"overtime\":false,\"removeEarlyMinute\":0,\"removeEarlyTime\":0,\"removeLateMinute\":0,\"removeLateTime\":0,\"shiftId\":7,\"shiftType\":\"office\",\"shiftVersion\":2,\"timeRange\":1,\"trip\":false,\"workEnd\":1594730622000,\"workEnd1\":1594730622000,\"workStart\":1594691794000,\"workStart1\":1594691794000,\"workTime\":587},{\"AFormatDay\":\"2020-07-15\",\"customDays\":1,\"date\":1594742400000,\"dayType\":\"workday\",\"delayWorkTime\":0,\"early\":0,\"early1\":0,\"early2\":0,\"early3\":0,\"early4\":0,\"earlyClearup\":false,\"earlyClearup1\":false,\"earlyClearup2\":false,\"earlyClearup3\":false,\"earlyClearup4\":false,\"goout\":false,\"late\":0,\"late1\":0,\"late2\":0,\"late3\":0,\"late4\":0,\"lateClearup\":false,\"lateClearup1\":false,\"lateClearup2\":false,\"lateClearup3\":false,\"lateClearup4\":false,\"leave\":false,\"needSignIn\":true,\"nosignTime\":0,\"outWorkTime\":480,\"overtime\":false,\"removeEarlyMinute\":0,\"removeEarlyTime\":0,\"removeLateMinute\":0,\"removeLateTime\":0,\"shiftId\":7,\"shiftType\":\"office\",\"shiftVersion\":2,\"timeRange\":1,\"trip\":false,\"workEnd\":1594822241000,\"workEnd1\":1594822241000,\"workStart\":1594778216000,\"workStart1\":1594778216000,\"workTime\":674},{\"AFormatDay\":\"2020-07-16\",\"customDays\":1,\"date\":1594828800000,\"dayType\":\"workday\",\"delayWorkTime\":0,\"early\":0,\"early1\":0,\"early2\":0,\"early3\":0,\"early4\":0,\"earlyClearup\":false,\"earlyClearup1\":false,\"earlyClearup2\":false,\"earlyClearup3\":false,\"earlyClearup4\":false,\"goout\":false,\"late\":0,\"late1\":0,\"late2\":0,\"late3\":0,\"late4\":0,\"lateClearup\":false,\"lateClearup1\":false,\"lateClearup2\":false,\"lateClearup3\":false,\"lateClearup4\":false,\"leave\":false,\"needSignIn\":true,\"nosignTime\":0,\"outWorkTime\":480,\"overtime\":false,\"removeEarlyMinute\":0,\"removeEarlyTime\":0,\"removeLateMinute\":0,\"removeLateTime\":0,\"shiftId\":7,\"shiftType\":\"office\",\"shiftVersion\":2,\"timeRange\":1,\"trip\":false,\"workEnd\":1594913951000,\"workEnd1\":1594913951000,\"workStart\":1594864344000,\"workStart1\":1594864344000,\"workTime\":767},{\"AFormatDay\":\"2020-07-17\",\"customDays\":1,\"date\":1594915200000,\"dayType\":\"workday\",\"delayWorkTime\":0,\"early\":15,\"early1\":15,\"early2\":0,\"early3\":0,\"early4\":0,\"earlyClearup\":false,\"earlyClearup1\":false,\"earlyClearup2\":false,\"earlyClearup3\":false,\"earlyClearup4\":false,\"goout\":false,\"late\":0,\"late1\":0,\"late2\":0,\"late3\":0,\"late4\":0,\"lateClearup\":false,\"lateClearup1\":false,\"lateClearup2\":false,\"lateClearup3\":false,\"lateClearup4\":false,\"leave\":false,\"needSignIn\":true,\"nosignTime\":0,\"outWorkTime\":480,\"overtime\":false,\"removeEarlyMinute\":0,\"removeEarlyTime\":0,\"removeLateMinute\":0,\"removeLateTime\":0,\"shiftId\":7,\"shiftType\":\"office\",\"shiftVersion\":2,\"timeRange\":1,\"trip\":false,\"workEnd\":1594985754000,\"workEnd1\":1594985754000,\"workStart\":1594954200000,\"workStart1\":1594954200000,\"workTime\":466},{\"AFormatDay\":\"2020-07-18\",\"customDays\":0,\"date\":1595001600000,\"dayType\":\"offday\",\"delayWorkTime\":0,\"early\":0,\"early1\":0,\"early2\":0,\"early3\":0,\"early4\":0,\"earlyClearup\":false,\"earlyClearup1\":false,\"earlyClearup2\":false,\"earlyClearup3\":false,\"earlyClearup4\":false,\"goout\":false,\"late\":0,\"late1\":0,\"late2\":0,\"late3\":0,\"late4\":0,\"lateClearup\":false,\"lateClearup1\":false,\"lateClearup2\":false,\"lateClearup3\":false,\"lateClearup4\":false,\"leave\":false,\"needSignIn\":false,\"nosignTime\":0,\"outWorkTime\":0,\"overtime\":false,\"removeEarlyMinute\":0,\"removeEarlyTime\":0,\"removeLateMinute\":0,\"removeLateTime\":0,\"shiftId\":0,\"shiftVersion\":0,\"timeRange\":0,\"trip\":false,\"workTime\":0},{\"AFormatDay\":\"2020-07-19\",\"customDays\":0,\"date\":1595088000000,\"dayType\":\"offday\",\"delayWorkTime\":0,\"early\":0,\"early1\":0,\"early2\":0,\"early3\":0,\"early4\":0,\"earlyClearup\":false,\"earlyClearup1\":false,\"earlyClearup2\":false,\"earlyClearup3\":false,\"earlyClearup4\":false,\"goout\":false,\"late\":0,\"late1\":0,\"late2\":0,\"late3\":0,\"late4\":0,\"lateClearup\":false,\"lateClearup1\":false,\"lateClearup2\":false,\"lateClearup3\":false,\"lateClearup4\":false,\"leave\":false,\"needSignIn\":false,\"nosignTime\":0,\"outWorkTime\":0,\"overtime\":false,\"removeEarlyMinute\":0,\"removeEarlyTime\":0,\"removeLateMinute\":0,\"removeLateTime\":0,\"shiftId\":0,\"shiftVersion\":0,\"timeRange\":0,\"trip\":false,\"workTime\":0},{\"AFormatDay\":\"2020-07-20\",\"customDays\":1,\"date\":1595174400000,\"dayType\":\"workday\",\"delayWorkTime\":0,\"early\":0,\"early1\":0,\"early2\":0,\"early3\":0,\"early4\":0,\"earlyClearup\":false,\"earlyClearup1\":false,\"earlyClearup2\":false,\"earlyClearup3\":false,\"earlyClearup4\":false,\"goout\":false,\"late\":0,\"late1\":0,\"late2\":0,\"late3\":0,\"late4\":0,\"lateClearup\":false,\"lateClearup1\":false,\"lateClearup2\":false,\"lateClearup3\":false,\"lateClearup4\":false,\"leave\":false,\"needSignIn\":true,\"nosignTime\":0,\"outWorkTime\":480,\"overtime\":false,\"removeEarlyMinute\":0,\"removeEarlyTime\":0,\"removeLateMinute\":0,\"removeLateTime\":0,\"shiftId\":7,\"shiftType\":\"office\",\"shiftVersion\":2,\"timeRange\":1,\"trip\":false,\"workEnd\":1595249216000,\"workEnd1\":1595249216000,\"workStart\":1595209591000,\"workStart1\":1595209591000,\"workTime\":600},{\"AFormatDay\":\"2020-07-21\",\"customDays\":1,\"date\":1595260800000,\"dayType\":\"workday\",\"delayWorkTime\":0,\"early\":0,\"early1\":0,\"early2\":0,\"early3\":0,\"early4\":0,\"earlyClearup\":false,\"earlyClearup1\":false,\"earlyClearup2\":false,\"earlyClearup3\":false,\"earlyClearup4\":false,\"goout\":false,\"late\":0,\"late1\":0,\"late2\":0,\"late3\":0,\"late4\":0,\"lateClearup\":false,\"lateClearup1\":false,\"lateClearup2\":false,\"lateClearup3\":false,\"lateClearup4\":false,\"leave\":false,\"needSignIn\":true,\"nosignTime\":0,\"outWorkTime\":480,\"overtime\":false,\"removeEarlyMinute\":0,\"removeEarlyTime\":0,\"removeLateMinute\":0,\"removeLateTime\":0,\"shiftId\":7,\"shiftType\":\"office\",\"shiftVersion\":2,\"timeRange\":1,\"trip\":false,\"workEnd\":1595340109000,\"workEnd1\":1595340109000,\"workStart\":1595295169000,\"workStart1\":1595295169000,\"workTime\":689},{\"AFormatDay\":\"2020-07-22\",\"customDays\":1,\"date\":1595347200000,\"dayType\":\"workday\",\"delayWorkTime\":0,\"early\":0,\"early1\":0,\"early2\":0,\"early3\":0,\"early4\":0,\"earlyClearup\":false,\"earlyClearup1\":false,\"earlyClearup2\":false,\"earlyClearup3\":false,\"earlyClearup4\":false,\"goout\":false,\"late\":0,\"late1\":0,\"late2\":0,\"late3\":0,\"late4\":0,\"lateClearup\":false,\"lateClearup1\":false,\"lateClearup2\":false,\"lateClearup3\":false,\"lateClearup4\":false,\"leave\":false,\"needSignIn\":true,\"nosignTime\":0,\"outWorkTime\":480,\"overtime\":false,\"removeEarlyMinute\":0,\"removeEarlyTime\":0,\"removeLateMinute\":0,\"removeLateTime\":0,\"shiftId\":7,\"shiftType\":\"office\",\"shiftVersion\":2,\"timeRange\":1,\"trip\":false,\"workEnd\":1595426683000,\"workEnd1\":1595426683000,\"workStart\":1595381698000,\"workStart1\":1595381698000,\"workTime\":690},{\"AFormatDay\":\"2020-07-23\",\"customDays\":1,\"date\":1595433600000,\"dayType\":\"workday\",\"delayWorkTime\":0,\"early\":0,\"early1\":0,\"early2\":0,\"early3\":0,\"early4\":0,\"earlyClearup\":false,\"earlyClearup1\":false,\"earlyClearup2\":false,\"earlyClearup3\":false,\"earlyClearup4\":false,\"goout\":false,\"late\":0,\"late1\":0,\"late2\":0,\"late3\":0,\"late4\":0,\"lateClearup\":false,\"lateClearup1\":false,\"lateClearup2\":false,\"lateClearup3\":false,\"lateClearup4\":false,\"leave\":false,\"needSignIn\":true,\"nosignTime\":0,\"outWorkTime\":480,\"overtime\":false,\"removeEarlyMinute\":0,\"removeEarlyTime\":0,\"removeLateMinute\":0,\"removeLateTime\":0,\"shiftId\":7,\"shiftType\":\"office\",\"shiftVersion\":2,\"timeRange\":1,\"trip\":false,\"workEnd\":1595513296000,\"workEnd1\":1595513296000,\"workStart\":1595468681000,\"workStart1\":1595468681000,\"workTime\":684},{\"AFormatDay\":\"2020-07-24\",\"customDays\":1,\"date\":1595520000000,\"dayType\":\"workday\",\"delayWorkTime\":0,\"early\":0,\"early1\":0,\"early2\":0,\"early3\":0,\"early4\":0,\"earlyClearup\":false,\"earlyClearup1\":false,\"earlyClearup2\":false,\"earlyClearup3\":false,\"earlyClearup4\":false,\"goout\":false,\"late\":0,\"late1\":0,\"late2\":0,\"late3\":0,\"late4\":0,\"lateClearup\":false,\"lateClearup1\":false,\"lateClearup2\":false,\"lateClearup3\":false,\"lateClearup4\":false,\"leave\":false,\"needSignIn\":true,\"nosignTime\":0,\"outWorkTime\":480,\"overtime\":false,\"removeEarlyMinute\":0,\"removeEarlyTime\":0,\"removeLateMinute\":0,\"removeLateTime\":0,\"shiftId\":7,\"shiftType\":\"office\",\"shiftVersion\":2,\"timeRange\":1,\"trip\":false,\"workEnd\":1595588242000,\"workEnd1\":1595588242000,\"workStart\":1595555387000,\"workStart1\":1595555387000,\"workTime\":488},{\"AFormatDay\":\"2020-07-25\",\"customDays\":0,\"date\":1595606400000,\"dayType\":\"offday\",\"delayWorkTime\":0,\"early\":0,\"early1\":0,\"early2\":0,\"early3\":0,\"early4\":0,\"earlyClearup\":false,\"earlyClearup1\":false,\"earlyClearup2\":false,\"earlyClearup3\":false,\"earlyClearup4\":false,\"goout\":false,\"late\":0,\"late1\":0,\"late2\":0,\"late3\":0,\"late4\":0,\"lateClearup\":false,\"lateClearup1\":false,\"lateClearup2\":false,\"lateClearup3\":false,\"lateClearup4\":false,\"leave\":false,\"needSignIn\":false,\"nosignTime\":0,\"outWorkTime\":0,\"overtime\":false,\"removeEarlyMinute\":0,\"removeEarlyTime\":0,\"removeLateMinute\":0,\"removeLateTime\":0,\"shiftId\":0,\"shiftVersion\":0,\"timeRange\":0,\"trip\":false,\"workStart\":1595639978000,\"workStart1\":1595639978000,\"workTime\":0},{\"AFormatDay\":\"2020-07-26\",\"customDays\":0,\"date\":1595692800000,\"dayType\":\"offday\",\"delayWorkTime\":0,\"early\":0,\"early1\":0,\"early2\":0,\"early3\":0,\"early4\":0,\"earlyClearup\":false,\"earlyClearup1\":false,\"earlyClearup2\":false,\"earlyClearup3\":false,\"earlyClearup4\":false,\"goout\":false,\"late\":0,\"late1\":0,\"late2\":0,\"late3\":0,\"late4\":0,\"lateClearup\":false,\"lateClearup1\":false,\"lateClearup2\":false,\"lateClearup3\":false,\"lateClearup4\":false,\"leave\":false,\"needSignIn\":false,\"nosignTime\":0,\"outWorkTime\":0,\"overtime\":false,\"removeEarlyMinute\":0,\"removeEarlyTime\":0,\"removeLateMinute\":0,\"removeLateTime\":0,\"shiftId\":0,\"shiftVersion\":0,\"timeRange\":0,\"trip\":false,\"workTime\":0},{\"AFormatDay\":\"2020-07-27\",\"customDays\":1,\"date\":1595779200000,\"dayType\":\"workday\",\"delayWorkTime\":0,\"early\":0,\"early1\":0,\"early2\":0,\"early3\":0,\"early4\":0,\"earlyClearup\":false,\"earlyClearup1\":false,\"earlyClearup2\":false,\"earlyClearup3\":false,\"earlyClearup4\":false,\"goout\":false,\"late\":0,\"late1\":0,\"late2\":0,\"late3\":0,\"late4\":0,\"lateClearup\":false,\"lateClearup1\":false,\"lateClearup2\":false,\"lateClearup3\":false,\"lateClearup4\":false,\"leave\":false,\"needSignIn\":true,\"nosignTime\":0,\"outWorkTime\":0,\"overtime\":false,\"removeEarlyMinute\":0,\"removeEarlyTime\":0,\"removeLateMinute\":0,\"removeLateTime\":0,\"shiftId\":7,\"shiftType\":\"office\",\"shiftVersion\":0,\"timeRange\":1,\"trip\":false,\"workTime\":0},{\"AFormatDay\":\"2020-07-28\",\"customDays\":1,\"date\":1595865600000,\"dayType\":\"workday\",\"delayWorkTime\":0,\"early\":0,\"early1\":0,\"early2\":0,\"early3\":0,\"early4\":0,\"earlyClearup\":false,\"earlyClearup1\":false,\"earlyClearup2\":false,\"earlyClearup3\":false,\"earlyClearup4\":false,\"goout\":false,\"late\":0,\"late1\":0,\"late2\":0,\"late3\":0,\"late4\":0,\"lateClearup\":false,\"lateClearup1\":false,\"lateClearup2\":false,\"lateClearup3\":false,\"lateClearup4\":false,\"leave\":false,\"needSignIn\":true,\"nosignTime\":0,\"outWorkTime\":0,\"overtime\":false,\"removeEarlyMinute\":0,\"removeEarlyTime\":0,\"removeLateMinute\":0,\"removeLateTime\":0,\"shiftId\":7,\"shiftType\":\"office\",\"shiftVersion\":0,\"timeRange\":1,\"trip\":false,\"workTime\":0},{\"AFormatDay\":\"2020-07-29\",\"customDays\":1,\"date\":1595952000000,\"dayType\":\"workday\",\"delayWorkTime\":0,\"early\":0,\"early1\":0,\"early2\":0,\"early3\":0,\"early4\":0,\"earlyClearup\":false,\"earlyClearup1\":false,\"earlyClearup2\":false,\"earlyClearup3\":false,\"earlyClearup4\":false,\"goout\":false,\"late\":0,\"late1\":0,\"late2\":0,\"late3\":0,\"late4\":0,\"lateClearup\":false,\"lateClearup1\":false,\"lateClearup2\":false,\"lateClearup3\":false,\"lateClearup4\":false,\"leave\":false,\"needSignIn\":true,\"nosignTime\":0,\"outWorkTime\":0,\"overtime\":false,\"removeEarlyMinute\":0,\"removeEarlyTime\":0,\"removeLateMinute\":0,\"removeLateTime\":0,\"shiftId\":7,\"shiftType\":\"office\",\"shiftVersion\":0,\"timeRange\":1,\"trip\":false,\"workTime\":0},{\"AFormatDay\":\"2020-07-30\",\"customDays\":1,\"date\":1596038400000,\"dayType\":\"workday\",\"delayWorkTime\":0,\"early\":0,\"early1\":0,\"early2\":0,\"early3\":0,\"early4\":0,\"earlyClearup\":false,\"earlyClearup1\":false,\"earlyClearup2\":false,\"earlyClearup3\":false,\"earlyClearup4\":false,\"goout\":false,\"late\":0,\"late1\":0,\"late2\":0,\"late3\":0,\"late4\":0,\"lateClearup\":false,\"lateClearup1\":false,\"lateClearup2\":false,\"lateClearup3\":false,\"lateClearup4\":false,\"leave\":false,\"needSignIn\":true,\"nosignTime\":0,\"outWorkTime\":0,\"overtime\":false,\"removeEarlyMinute\":0,\"removeEarlyTime\":0,\"removeLateMinute\":0,\"removeLateTime\":0,\"shiftId\":7,\"shiftType\":\"office\",\"shiftVersion\":0,\"timeRange\":1,\"trip\":false,\"workTime\":0},{\"AFormatDay\":\"2020-07-31\",\"customDays\":1,\"date\":1596124800000,\"dayType\":\"workday\",\"delayWorkTime\":0,\"early\":0,\"early1\":0,\"early2\":0,\"early3\":0,\"early4\":0,\"earlyClearup\":false,\"earlyClearup1\":false,\"earlyClearup2\":false,\"earlyClearup3\":false,\"earlyClearup4\":false,\"goout\":false,\"late\":0,\"late1\":0,\"late2\":0,\"late3\":0,\"late4\":0,\"lateClearup\":false,\"lateClearup1\":false,\"lateClearup2\":false,\"lateClearup3\":false,\"lateClearup4\":false,\"leave\":false,\"needSignIn\":true,\"nosignTime\":0,\"outWorkTime\":0,\"overtime\":false,\"removeEarlyMinute\":0,\"removeEarlyTime\":0,\"removeLateMinute\":0,\"removeLateTime\":0,\"shiftId\":7,\"shiftType\":\"office\",\"shiftVersion\":0,\"timeRange\":1,\"trip\":false,\"workTime\":0},{\"AFormatDay\":\"2020-08-01\",\"customDays\":0,\"date\":1596211200000,\"delayWorkTime\":0,\"early\":0,\"early1\":0,\"early2\":0,\"early3\":0,\"early4\":0,\"earlyClearup\":false,\"earlyClearup1\":false,\"earlyClearup2\":false,\"earlyClearup3\":false,\"earlyClearup4\":false,\"goout\":false,\"late\":0,\"late1\":0,\"late2\":0,\"late3\":0,\"late4\":0,\"lateClearup\":false,\"lateClearup1\":false,\"lateClearup2\":false,\"lateClearup3\":false,\"lateClearup4\":false,\"leave\":false,\"needSignIn\":false,\"nosignTime\":0,\"outWorkTime\":0,\"overtime\":false,\"removeEarlyMinute\":0,\"removeEarlyTime\":0,\"removeLateMinute\":0,\"removeLateTime\":0,\"shiftVersion\":0,\"timeRange\":0,\"trip\":false,\"workTime\":0}],\"goouts\":[],\"leaves\":[],\"legworks\":[],\"month\":7,\"overtimes\":[],\"shiftAfterHours\":0,\"shiftBeforeHours\":0,\"shifts\":[{\"acrossIndex\":0,\"acrossTheDay\":\"NO\",\"advanceSetting\":\"late_start_work\",\"endTimeName\":\"白班\",\"endTimeName1\":\"白班\",\"halfShift\":false,\"shiftEnd\":36000000,\"shiftEnd1\":36000000,\"shiftId\":7,\"shiftName\":\"弹性班次（通）\",\"shiftShortName\":\"弹\",\"shiftStart\":3600000,\"shiftStart1\":3600000,\"shiftType\":\"office\",\"shiftVersion\":2,\"signInCount\":2,\"startTimeName\":\"白班\",\"startTimeName1\":\"白班\",\"timeRange\":1,\"timeoutEnd1\":18000000,\"timeoutRange\":1,\"timeoutStart1\":14400000,\"worktime\":480},{\"acrossIndex\":0,\"acrossTheDay\":\"NO\",\"advanceSetting\":\"late_start_work\",\"endTimeName\":\"白班\",\"endTimeName1\":\"白班\",\"halfShift\":false,\"shiftEnd\":36000000,\"shiftEnd1\":36000000,\"shiftId\":7,\"shiftName\":\"弹性班次（通）\",\"shiftShortName\":\"弹\",\"shiftStart\":3600000,\"shiftStart1\":3600000,\"shiftType\":\"office\",\"shiftVersion\":0,\"signInCount\":2,\"startTimeName\":\"白班\",\"startTimeName1\":\"白班\",\"timeRange\":1,\"timeoutEnd1\":18000000,\"timeoutRange\":1,\"timeoutStart1\":14400000,\"worktime\":480}],\"showNoSign\":\"YES\",\"trips\":[],\"year\":2020}},\"message\":\"操作成功\"}"));
    }

    @Override
    public void checkCaiWu() {
        if(!checkResult("http://180.167.151.210:12363/")){
            send("财务纸质发票-http://180.167.151.210:12363/  拒绝访问");
        }

        if(!checkResult("http://180.167.151.210:4040/")){
            send("财务快递单号-http://180.167.151.210:4040/  拒绝访问");
        }

        if(!checkResult("http://180.167.151.210:4445/")){
            send("财务快递单号-http://180.167.151.210:4445/  拒绝访问");
        }

        if(!checkResult("http://180.167.151.210:12364/")){
            send("财务电子发票-http://180.167.151.210:12364/  拒绝访问");
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
        int status = HttpRequest.get(str).execute().getStatus();
//        System.out.println(status);
//        log.info("_________jyb_________{}",status);
        if(status != 200){
            send("捷园宝网站挂了，请求返回："+status);
            log.info("_________jyb_________{}",status);
        }
        return status;
    }


    public void sendMsg(int type, String str, String message){
        log.info("{} ,{}, - 开始检查-打卡记录:{}",DateUtil.now(),str,message);
        JSONArray days = JSONUtil.parseObj(message).getJSONObject("data").
                getJSONObject("attendance").getJSONArray("days");
        for (Object day : days) {
            JSONObject obj = (JSONObject) day;
            if(DateUtil.format(new Date(), "yyyy-MM-dd").equals(obj.getStr("AFormatDay"))
                    && "workday".equals(obj.getStr("dayType"))
            ){
                if(StringUtils.isEmpty(obj.getStr("workStart")) && type == 1){
                    send(str,obj.getStr("AFormatDay"),"早上");
                }
                if(StringUtils.isEmpty(obj.getStr("workEnd"))  && type == 2){
                    send(str,obj.getStr("AFormatDay"),"晚上");
                }
            }
        }
    }

    private void send(String name,String date,String day){
        log.info("姓名：{} 日期：{} 班次：{}",name,date,day);
        send(name+"—"+date+"—<font color='#FF0000'>"+day+"—漏打卡</font>");
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


    private void send(String content){
        JSONObject json = JSONUtil.parseObj("{\"msgtype\":\"markdown\"," +
                "\"markdown\":{\"content\":\""+content+"\"}}");
        HttpRequest.post("https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=6f8fee88-0281-4da8-b1be-d120d296d606")
                .body(json.toString())
                .timeout(20000)//超时，毫秒
                .execute().body();
    }
}
