package com.soholy.pojo.vo;

public class ComandSend {

    /**
     * 指令内容，必填，格式为Json， 此处 NB网关无profile的透传(无物模型)content内容:
     */
    private Object content;
    /**
     * 设备ID，必填
     */
    private String deviceId;
    /**
     * 操作者，必填
     */
    private String operator;
    /**
     * 产品ID，必填
     */
    private Integer productId;
    /**
     * 指令在缓存时长默认7200秒，选填
     */
    private Integer ttl = 7200;



    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getTtl() {
        return ttl;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }
}
