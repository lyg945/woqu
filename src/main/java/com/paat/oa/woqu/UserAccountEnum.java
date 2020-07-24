package com.paat.oa.woqu;

import lombok.Getter;

@Getter
public enum  UserAccountEnum {
    LiuYongGang("刘勇岗","xILqjpmyvXwb/Z7uh+14EW80QhWfznJ0","nBVxa3mFdyizVI2mrgmQf0PaKacsQMeXVbmbPpWcmzs3epeg/6ImBbl4UqzWzSkqdiDKAKUsmG12Fz1xCD/Ke9ysapjkXq0B5uoFEVfU5sxBnDv63DgeqEYAkmDdnmg18QDes5uzummBvXINb3Eba280QhWfznJ0"),
    XiMingCong("习明聪","xILqjpmyvXwb/Z7uh+14EW80QhWfznJ0","nBVxa3mFdyizVI2mrgmQf0PaKacsQMeXVbmbPpWcmzs3epeg/6ImBbl4UqzWzSkqdiDKAKUsmG12Fz1xCD/Ke9ysapjkXq0B5uoFEVfU5sxBnDv63DgeqEYAkmDdnmg18QDes5uzummBvXINb3Eba280QhWfznJ0")
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
