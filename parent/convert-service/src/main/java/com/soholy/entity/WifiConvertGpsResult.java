package com.soholy.entity;

public class WifiConvertGpsResult {

    /**
     * 0: 成功
     * 10000: 参数错误
     * 10001: 无查询结果
     */
    private Integer errcode;
    /**
     * 纬度
     */
    private Double lat;
    /**
     * 经度
     */
    private Double lon;
    /**
     * 精度半径
     */
    private Float radius;

    /**
     * 地址信息
     */
    private String address;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Float getRadius() {
        return radius;
    }

    public void setRadius(Float radius) {
        this.radius = radius;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public static enum WifiConvertApiResult {

        SUCCESS(0, "成功"),
        PARAMS_ERR(10000, "参数错误"),
        RESULT_NONE(10001, "无查询结果");

        private WifiConvertApiResult(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        private Integer code;
        private String msg;

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
