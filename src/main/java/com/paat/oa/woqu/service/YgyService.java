package com.paat.oa.woqu.service;

import java.util.Map;

public interface YgyService {
    String changeMap(String appId, String appSecret, Map map);

    String saveEmployed(String signature, Map map);
}
