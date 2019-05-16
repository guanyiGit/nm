package com.posiition.VO;

import java.util.Date;

public class DogRecordInfo {
    private  String traceId ;
    private  String dogMasterName;
    private  String dogName;
    private  double lng;
    private  double lat;
    private Date dateTime;

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getDogMasterName() {
        return dogMasterName;
    }

    public void setDogMasterName(String dogMasterName) {
        this.dogMasterName = dogMasterName;
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "DogRecordInfo{" +
                "traceId='" + traceId + '\'' +
                ", dogMasterName='" + dogMasterName + '\'' +
                ", dogName='" + dogName + '\'' +
                ", lng=" + lng +
                ", lat=" + lat +
                ", dateTime=" + dateTime +
                '}';
    }
}
