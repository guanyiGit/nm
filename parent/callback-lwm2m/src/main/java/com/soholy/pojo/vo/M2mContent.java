package com.soholy.pojo.vo;

public class M2mContent {

    /**
     * 指令内容,数据格式为十六进制时需要填十六进制字符串
     */
    private String payload;
    /**
     * 数据类型：1字符串，2十六进制
     */
    private Integer dataType = 1;

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }
}
