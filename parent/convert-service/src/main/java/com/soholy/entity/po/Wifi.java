package com.soholy.entity.po;

import java.util.Date;

public class Wifi {
    private String priId;

    private String bssid1;

    private Float rssi1;

    private String bssid2;

    private Float rssi2;

    private String bssid3;

    private Float rssi3;

    private Double longitude;

    private Double latitude;

    private Float radius;

    private String address;

    private Integer remark;

    private Date updateTime;

    private Date creationTime;

    public String getPriId() {
        return priId;
    }

    public void setPriId(String priId) {
        this.priId = priId == null ? null : priId.trim();
    }

    public String getBssid1() {
        return bssid1;
    }

    public void setBssid1(String bssid1) {
        this.bssid1 = bssid1 == null ? null : bssid1.trim();
    }

    public Float getRssi1() {
        return rssi1;
    }

    public void setRssi1(Float rssi1) {
        this.rssi1 = rssi1;
    }

    public String getBssid2() {
        return bssid2;
    }

    public void setBssid2(String bssid2) {
        this.bssid2 = bssid2 == null ? null : bssid2.trim();
    }

    public Float getRssi2() {
        return rssi2;
    }

    public void setRssi2(Float rssi2) {
        this.rssi2 = rssi2;
    }

    public String getBssid3() {
        return bssid3;
    }

    public void setBssid3(String bssid3) {
        this.bssid3 = bssid3 == null ? null : bssid3.trim();
    }

    public Float getRssi3() {
        return rssi3;
    }

    public void setRssi3(Float rssi3) {
        this.rssi3 = rssi3;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Float getRadius() {
        return radius;
    }

    public void setRadius(Float radius) {
        this.radius = radius;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getRemark() {
        return remark;
    }

    public void setRemark(Integer remark) {
        this.remark = remark;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
}