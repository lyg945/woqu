package com.paat.oa.woqu.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import com.paat.oa.woqu.service.YgyService;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class YgyServiceImpl implements YgyService {

    @Override
    public String changeMap(String appId, String appSecret, Map param) {
        //参数排序： 对所有请求参数，按照参数名 ASCII 码表升序顺序排序。如：foo=1，bar=2， foo_bar=3，baz=4 排序后的顺序是 bar=2，baz=4，foo=1，foobar=3
        Map map = sortByKey(baseMap(appId,appSecret));
        map.putAll(param);

        //拼接参数： 将排序好的参数名和参数值构造成 URL 字符串，格式为： key1=value1&key2=value2… 。根据上面的示例得到的构造结果为： bar2=baz4&foo=1&foobar=3
        String [] sign = new String[]{""};
        map.forEach((key, value) -> {
            sign[0] =  sign[0]+ key+"="+value+"&" ;
        });

        //参数HASH： 把上一步骤拼装好的字符串使用 Hmac256 算法进行哈希，计算得到 signature 参数 值，形式为 hmac256(拼接的参数, appSecret) 。将得到的结果通过 header 传输即可
        sign[0] = sign[0].substring(0,sign[0].lastIndexOf("&"));
        HMac mac = new HMac(HmacAlgorithm.HmacSHA256, appSecret.getBytes());
        return mac.digestHex((sign[0]));
    }

    @Override
    public String saveEmployed(String signature, Map map) {
        String result = HttpRequest.post("https://ygyapi.paat.vip/self_employed/save")
                .header(Header.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit /537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36")//头信息，多个头信息多次调用此方法即可
//                .cookie(cookie,cookie2)
//                .header("signature",signature)
                .form(map)
                .timeout(20000)//超时，毫秒
                .execute().body();

        System.out.println(result);
        return result;
    }


    /**
     * 公共信息
     * @param appId
     * @param appSecret
     * @return
     */
    public Map baseMap(String appId, String appSecret){
        Map map = MapUtil.newHashMap();
        map.put("version","1.0.0");
        map.put("appId",appId);
//        map.put("appSecret",appSecret);
        map.put("timestamp",String.valueOf(System.currentTimeMillis()).substring(0,10));
        map.put("nonce", IdUtil.objectId());
        return map;
    }

    /**
     * map 按 key 进行排序
     * @param map
     * @param <K>
     * @param <V>
     * @return
     */
    public <K extends Comparable<? super K>, V > Map<K, V> sortByKey(Map<K, V> map) {
        Map<K, V> result = new LinkedHashMap<>();
        map.entrySet().stream()
                .sorted(Map.Entry.<K, V>comparingByKey()
//                        .reversed()  //倒叙
                ).forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        return result;
    }

}
