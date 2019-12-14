# -*- coding: utf-8 -*-

# Author: 桑葚ICE
# Email: 152516cc@gmail.com
# Blog: iicey.github.io
# JueJin: juejin.im/user/5c64dce8e51d45013c40742c
import time
from pprint import pprint
import jsonpath

import requests


def select(mor_eve, name, _wcf_, _wcfl_):
    year, month = time.strftime("%Y-%m", time.localtime()).split("-")
    result = requests.post("https://app.woqu365.com/app/employee/myAttendanceData.htm",
                           headers={
                               'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit'
                                             '/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36',
                           },
                           data={"year": year, "month": month},
                           cookies={
                               '_wcf_': _wcf_,
                               '_wcfl_': _wcfl_,
                           })
    days = jsonpath.jsonpath(result.json(), '$.data.attendance.days')[0]
    for day in days:
        AFormatDay = time.strftime("%Y-%m-%d", time.localtime())
        if day.get("dayType") == "workday" and day.get("AFormatDay") == AFormatDay:
            if (not day.get("workStart")) and (mor_eve == "上班"):
                send(name, mor_eve)
            if (not day.get("workEnd")) and (mor_eve == "下班"):
                send(name, mor_eve)


def send(name, mor_eve):
    AFormatDay = time.strftime("%Y-%m-%d", time.localtime())
    result = requests.post("https://qyapi.weixin.qq.com/cgi-bin/webhook/send",
                           params={"key": "6f8fee88-0281-4da8-b1be-d120d296d606"},
                           headers={
                               "Content-Type": "application/json"},
                           json={
                               "msgtype": "markdown",
                               "markdown": {
                                   "content": f"{name}—{AFormatDay}—<font color=\"warning\">未打{mor_eve}卡</font>"
                               }
                           }
                           )
    pprint(result.json())


if __name__ == "__main__":
    from apscheduler.schedulers.blocking import BlockingScheduler

    scheduler = BlockingScheduler()


    def job(mor_eve):
        _wcf_ = '"5BsZNgjBVnfT+oFiW7DWnbCeBCrulX+SCrvnXpkgqQSwja81cqro/oIE8KnReDd9azIG9VcbBt+yLQERaU22Zs8bXnjiIOWLgZDJo2tzcM1R7iV1Ip5uk/hbIkSWrwi1S9jG+pItrE4QK1MKYrfzMG80QhWfznJ0"'
        _wcfl_ = "rsD7C/WlmNXMDGxod/0kQW80QhWfznJ0"
        select(mor_eve, "刘勇岗", _wcf_, _wcfl_)


    scheduler.add_job(job, 'cron', args=("上班", ), day_of_week='0-6', hour=10, minute=30, second=00)
    scheduler.add_job(job, 'cron', args=("上班", ), day_of_week='0-6', hour=10, minute=55, second=00)
    scheduler.add_job(job, 'cron', args=("下班", ), day_of_week='0-6', hour=22, minute=00, second=00)
    scheduler.add_job(job, 'cron', args=("下班", ), day_of_week='0-6', hour=23, minute=00, second=00)
    scheduler.start()  # 开始任务
