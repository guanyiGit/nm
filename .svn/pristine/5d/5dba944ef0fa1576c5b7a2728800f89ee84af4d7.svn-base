package com.soholy.pojo.vo;

import com.alibaba.fastjson.JSONObject;

public class AddDevice {

    /**
     * 设备名称，必填
     */
    private String deviceName;
    /**
     * 设备编号，MQTT,T_Link协议必填
     */
    private String deviceSn;
    /**
     * imei号，LWM2M,NB网关必填
     */
    private String imei;
    /**
     * 操作者，必填
     */
    private String operator;
    /**
     * 选填，LWM2M协议选填参数,其他协议不填：
     * {
     * autoObserver:0.自动订阅 1.取消自动订阅，选填;
     * imsi:imsi号,选填;
     * pskValue:由大小写字母加0-9数字组成的32位字符串,选填
     * }
     */
    private JSONObject other;

    public AddDevice() {
        this.other = new JSONObject();
        this.other.put("autoObserver", 0);
    }

    /**
     * 产品ID，必填
     */
    private Integer productId;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public JSONObject getOther() {
        return other;
    }

    public void setOther(JSONObject other) {
        this.other = other;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
