package com.statisanalysis.wx.vo;

/**
 * 犬只犬主、防疫管理的VO
 * @Description
 * @Author: linchong
 * @Date: 2018/11/21 14:24
 * @Version 1.0
 */
public class CountVO {
    private Integer stateId;    //州区域ID
    private Integer countyId;   //县区域ID
    private Integer villageId;  //乡区域ID
    private Integer protectorId;    //防疫员ID
    private String stateName;   //州区域名
    private String countyName;  //县区域名
    private String villageName; //乡区域名
    private String protectorName;   //防疫员姓名
    private Integer count;      //数量、次数
    private Integer totalAmount;    //犬只总数
    private Integer antiAmount;     //已防疫数量
    private Integer unAntiAmount;   //未防疫数量

    public CountVO() {
    }

    public CountVO(Integer protectorId, String protectorName, Integer count) {
        this.protectorId = protectorId;
        this.protectorName = protectorName;
        this.count = count;
    }

    public CountVO(Integer stateId, Integer countyId, Integer villageId, Integer protectorId, String stateName, String countyName, String villageName, String protectorName, Integer count) {
        this.stateId = stateId;
        this.countyId = countyId;
        this.villageId = villageId;
        this.protectorId = protectorId;
        this.stateName = stateName;
        this.countyName = countyName;
        this.villageName = villageName;
        this.protectorName = protectorName;
        this.count = count;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getAntiAmount() {
        return antiAmount;
    }

    public void setAntiAmount(Integer antiAmount) {
        this.antiAmount = antiAmount;
    }

    public Integer getUnAntiAmount() {
        return unAntiAmount;
    }

    public void setUnAntiAmount(Integer unAntiAmount) {
        this.unAntiAmount = unAntiAmount;
    }

    public Integer getCountyId() {
        return countyId;
    }

    public void setCountyId(Integer countyId) {
        this.countyId = countyId;
    }

    public Integer getVillageId() {
        return villageId;
    }

    public void setVillageId(Integer villageId) {
        this.villageId = villageId;
    }

    public Integer getProtectorId() {
        return protectorId;
    }

    public void setProtectorId(Integer protectorId) {
        this.protectorId = protectorId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getProtectorName() {
        return protectorName;
    }

    public void setProtectorName(String protectorName) {
        this.protectorName = protectorName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    @Override
    public String toString() {
        return "CountVO{" +
                "stateId=" + stateId +
                ", countyId=" + countyId +
                ", villageId=" + villageId +
                ", protectorId=" + protectorId +
                ", stateName='" + stateName + '\'' +
                ", countyName='" + countyName + '\'' +
                ", villageName='" + villageName + '\'' +
                ", protectorName='" + protectorName + '\'' +
                ", count=" + count +
                ", totalAmount=" + totalAmount +
                ", antiAmount=" + antiAmount +
                ", unAntiAmount=" + unAntiAmount +
                '}';
    }
}
