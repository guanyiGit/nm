package com.soholy.pojo;

public class TupPayload implements PayLoad {

    private String Updata;

    private Integer Length;

    public String getUpdata() {
        return Updata;
    }

    public void setUpdata(String updata) {
        Updata = updata;
    }

    public Integer getLength() {
        return Length;
    }

    public void setLength(Integer length) {
        Length = length;
    }
}