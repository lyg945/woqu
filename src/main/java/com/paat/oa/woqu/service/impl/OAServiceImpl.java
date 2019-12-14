package com.paat.oa.woqu.service.impl;

import cn.hutool.core.lang.Console;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import com.paat.oa.woqu.service.OAService;
import org.springframework.stereotype.Service;

import java.net.HttpCookie;
import java.util.HashMap;
import java.util.Map;

@Service
public class OAServiceImpl implements OAService {

    @Override
    public void pullOA() {
        oa();
    }

    private static String oa() {
        //链式构建请求
        Map map = new HashMap<>();
        map.put("year","2019");
        map.put("month","12");


        HttpCookie cookie = new HttpCookie("_wcf_","5BsZNgjBVnfT+oFiW7DWnbCeBCrulX+SCrvnXpkgqQSwja81cqro/oIE8KnReDd9azIG9VcbBt+yLQERaU22Zs8bXnjiIOWLgZDJo2tzcM1R7iV1Ip5uk/hbIkSWrwi1S9jG+pItrE4QK1MKYrfzMG80QhWfznJ0");
        HttpCookie cookie2 = new HttpCookie("_wcfl_","rsD7C/WlmNXMDGxod/0kQW80QhWfznJ0");

        String result2 = HttpRequest.post("https://app.woqu365.com/app/employee/myAttendanceData.htm")
                .header(Header.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit /537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36")//头信息，多个头信息多次调用此方法即可
                .cookie(cookie,cookie2)
                .form(map)
                .timeout(20000)//超时，毫秒
                .execute().body();
        return result2;
    }

    @Override
    public void sendMsg(String msg) {

    }

    public static void main(String[] args) {
        System.out.println(oa());
    }
}
