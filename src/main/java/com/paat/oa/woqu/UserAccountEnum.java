package com.paat.oa.woqu;

import lombok.Getter;

@Getter
public enum  UserAccountEnum {
    LiuYongGang ("刘勇岗","0rl4q5KGV6aqj45UbtPaA280QhWfznJ0","5BsZNgjBVnfqh8QrHT5D590Akce+0hfvCQYV9Xk408Yr7zlgv81TzGf0NZwAC85W2tSwYSkTxp7a1O4YykXMOGfd0T4G0lSCLITJiPSR/6UckbiLDk/H6mCpvlGf2oiT3JEzPSeB/da8ggVOD+/Zxm80QhWfznJ0"),
    XiMingCong  ("习明聪","i/ZPiUGMX49yNXHFQPXPnm80QhWfznJ0","F3fjudT8ey1gGkEkanNjmEP7LWHkZ7VENzSYqMLF9yP95CAEqF5rjEjGlrxlt/6YDBGJ03AzPQJEGFeMFE+WkdXyCn1hyrvHGj0wMRExGviwngQq7pV/klcxwgjcf6j4mYpBvEy2Xe1WgT3PQoQEqG80QhWfznJ0"),
    WangChenXi  ("王晨曦","0rl4q5KGV6aqMs8PWQVh9280QhWfznJ0","YFlJMYRT3KWx/Dlk7Y7FrNmv3xVFNgqLcuqAsCmUNzS5EALPBiixqVDpYBdMoT3m+sedGRerUNl3OQwDyC7dyurm3j/kduJUAVbrPGHMsoaqOp90cSdLF2aV1ZXausxxpLnnUO6yKrGLFADXKrgiObiy8CvzQy+Y"),
    HeMengYuan  ("何梦媛","0rl4q5KGV6afBpu1GwgjlG80QhWfznJ0","J31hPh7QBHoUFSKkHWHGhVG/bH1kJ2hN3ltaYFlZT02grpfZ6rHcldt1SKJKMCK0JpCM13zmyvKx/oRXFmQNgUarKK3iHK0O+YVnHPasZQcj29/Xj7wL/3SGIPji8XyNF3fjudT8ey3WZItZFBTTFoPabaNLNlsT"),
    HanBingBing ("韩冰冰","0rl4q5KGV6bzpKzQgMIhM280QhWfznJ0","/f0czPuWwGaRv+92+kW+Vxr0Ad/RJoTblDaPlkMkSFkJCjKkyt/nAOTT+ufy8XBFtyUZp5o27Grrac68HvwcMxeLqSxNgLpEkuB6mev78lUQK1MKYrfzMMe5GM1scECrhcbHAd8LxC7m2J/k32TfO1+Af/Fvd1OP"),
    ShuZhiXiong ("舒志雄","i/ZPiUGMX4+W+J2YqYIIb280QhWfznJ0","nBVxa3mFdyjuAxzz0oTsa8nHwN4+b0X8izjVOveBYo++OLNhsDD4j8e5GM1scECrp4bAYbZPReHwgYPr8RXkHTGwK1qs36aKIv3gmB85XArgEpTA4T0iP4MWU+Ow1lTdgHJrRrh64ovuzC4nXUeU8G80QhWfznJ0"),
    XiongHouYou ("熊厚有","i/ZPiUGMX48AUuN3n+z6sG80QhWfznJ0","F3fjudT8ey1gGkEkanNjmCME7RsFgbj/xPUe1ddJLSqoXyR43kHR5kjGlrxlt/6YFuDLQzWwuVPHzplpg1G1CRHxQ63g7fRPWkr/G0sccyiwngQq7pV/khAMJdDkonIBLI4aBByvp8+FTqatsUSphG80QhWfznJ0"),
    ZhuXiBo     ("朱希博","0rl4q5KGV6aMS/YelcQ3WW80QhWfznJ0","F3fjudT8ey1HDV3vSTg96LuHa2OO6YgHwt0yNkoIvsGxI9Wabi+DgOKpohbV4CsoWov7hebss7ZKjS0D6hKa4rCgKlNN9Txz87O4EbDiZg+qOp90cSdLF+mbXoKi7sWDB9ip+KsdSbiAUbStSSRlOiJ5/g/frOCV"),
    GuoRanRan   ("郭然然","0rl4q5KGV6bY69dzbMTRb280QhWfznJ0","8JpxY//NGsqv7J0I1/os+hr0Ad/RJoTbhzVLgOHuYOzBmLIqf3GLLWi4nL8AsSk2EAnSpg09rMskfWXRmyhLYAq8ht/QgTEckuB6mev78lUQK1MKYrfzMMe5GM1scECr8bqZ7ewoJgwGJOOtKAcwf+QpPUypYbP6"),
    LiLiPing    ("李丽萍","0rl4q5KGV6bY69dzbMTRb280QhWfznJ0","F3fjudT8ey1HDV3vSTg96KKyXYUDewAe0rP+H/8jTCKG0bfRUXoI59FotFONzypLWov7hebss7YwGMtRNDatkZjJG7e6S+0SMT5E3lrUs2aqOp90cSdLF9JNIoODD4ce0l0enrC0JUhegdkgvtE00bjEWycOfKFr"),

            ;

    private String name;
    private String _wcfl_;
    private String _wcf_;

    UserAccountEnum(String name,String _wcfl_,String _wcf_){
        this.name = name;
        this._wcf_=_wcf_;
        this._wcfl_ = _wcfl_;
    }

}
