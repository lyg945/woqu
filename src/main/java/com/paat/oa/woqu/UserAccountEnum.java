package com.paat.oa.woqu;

import lombok.Getter;

@Getter
public enum  UserAccountEnum {
    LiuYongGang("刘勇岗","ZTbp/XYO5rVUvsAYk0US+G80QhWfznJ0","5BsZNgjBVndOd/nPjO4aQptIVRQgUPxyQvFIV4bTQI2lg7lSKJcYEyCdDFloA+Ql2tSwYSkTxp5Ixpa8Zbf+mP5CjbAUtz0AG3Iw+/bySpr2IyEFFkn9NaPppnq9dGORpPU+Ux2ZaidveOX5HPs94280QhWfznJ0"),
    XiMingCong( "习明聪","ZTbp/XYO5rUSpeGiTfHPzm80QhWfznJ0","PzsHe2/qqvZDbIBVdijunuuz6Y5gFPHzCdO64wQEXIVWgT3PQoQEqES2QAsAIJ0mWN3kibgvBZHOD6YUXIUl0HYgygClLJht+BYIt9FztIPVU2lEzkOTenDASFNDBc3OByAXf8s02mcQK1MKYrfzMG80QhWfznJ0"),
    WangChenXi( "王晨曦","ZTbp/XYO5rVIU6/tRKKRTW80QhWfznJ0","KkDO7M/5us+EcE3GiaEym+OiISWg0kufECtTCmK38zCgc0+rgbKY58OYk9CWP19nYFlJMYRT3KWx/Dlk7Y7FrMb6ErmNDGdBfs9Q2jTvy/qV2MVzbzlVevEplQKUW19Z8YCcdyNkVsw/1aPmY1Om7iPr+PT/EKSa"),
    HeMengYuan( "何梦媛","ZTbp/XYO5rXIUGkjTb3sv280QhWfznJ0","J31hPh7QBHoUFSKkHWHGhVG/bH1kJ2hN3ltaYFlZT02grpfZ6rHcldt1SKJKMCK0JpCM13zmyvKx/oRXFmQNgUarKK3iHK0O+YVnHPasZQcj29/Xj7wL/3SGIPji8XyNF3fjudT8ey3WZItZFBTTFoPabaNLNlsT"),
    HanBingBing("韩冰冰","ZTbp/XYO5rVwkmu205W55280QhWfznJ0","/f0czPuWwGaOAPuqvlBJuMkox/Vxo1mpEN/gI/01wZOn5PqnF9PQTQPWrObL9iSX5tif5N9k3zuPJcgLi5TtA82sQz2uUTEFRP05mRY9Kyf6rwxVpQeV/L2V8Fu4sOSaZuSfZXTwZudbZ2Qox09ZwIPabaNLNlsT"),
    ShuZhiXiong("舒志雄","ZTbp/XYO5rUX7CgfAuZ+4m80QhWfznJ0","UV8vh65EJMDmE1P2dDJL1iy0t4MUdySAN9qL5tH/l8tw0wIPQg/gqf+9Q04qBnJcZmgst+UBELpIxpa8Zbf+mI9LMlnACr9swpi5Nca5CsIKT9Xk2sT0wg+vjoOdjpeYpPU+Ux2ZaidveOX5HPs94280QhWfznJ0"),

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
