package com.paat.oa.woqu.service;

public interface OAService {

    /**
     * 拉取OA新  type:1 早上漏打卡提醒  2：晚上漏打卡提醒
     */
    void pullOA(int type);

}
