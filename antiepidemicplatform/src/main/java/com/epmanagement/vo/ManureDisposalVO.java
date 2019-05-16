package com.epmanagement.vo;

import java.util.Date;

/**
 * @Description
 * @Author: linchong
 * @Date: 2018/10/10 14:19
 * @Version 1.0
 */
public class ManureDisposalVO{
    private Integer id;
    private String town;  // 暂定返回格式：州-县-乡
    private String title;
    private String mode;    //处理方法ID
    private String processMode;     //处理方法ID(冗余，不要删)
    private String modeName;    //处理方法名
    private String methodDes; //方法说明
    private String period;  //处理周期
    private Date dealTime;  //处理时间
    private String name;    //处理人员
    private Integer operator; //处理人员ID
    private String url;     //照片
    private String description;

    private Integer orgId;
    private Date createDate;
    //
    private Date updateDate;
    private Integer areaId;
    private String deviceNo;	//设备编号
    private String traceId;     //溯源ID
    private String dogName;
    private String breed;
    private String ownerName;
    private String phoneNum;
    private String cardNum;
    private String areaName;
    private String detailAddress;
    private String protecterName;



    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getProcessMode() {
        return processMode;
    }

    public void setProcessMode(String processMode) {
        this.processMode = processMode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getModeName() {
        return modeName;
    }

    public void setModeName(String modeName) {
        this.modeName = modeName;
    }

    public String getMethodDes() {
        return methodDes;
    }

    public void setMethodDes(String methodDes) {
        this.methodDes = methodDes;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getProtecterName() {
        return protecterName;
    }

    public void setProtecterName(String protecterName) {
        this.protecterName = protecterName;
    }

    @Override
    public String toString() {
        return "ManureDisposalVO{" +
                "id=" + id +
                ", town='" + town + '\'' +
                ", title='" + title + '\'' +
                ", mode='" + mode + '\'' +
                ", processMode='" + processMode + '\'' +
                ", modeName='" + modeName + '\'' +
                ", methodDes='" + methodDes + '\'' +
                ", period='" + period + '\'' +
                ", dealTime=" + dealTime +
                ", name='" + name + '\'' +
                ", operator=" + operator +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", orgId=" + orgId +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", areaId=" + areaId +
                ", deviceNo='" + deviceNo + '\'' +
                ", traceId='" + traceId + '\'' +
                ", dogName='" + dogName + '\'' +
                ", breed='" + breed + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", cardNum='" + cardNum + '\'' +
                ", areaName='" + areaName + '\'' +
                ", detailAddress='" + detailAddress + '\'' +
                ", protecterName='" + protecterName + '\'' +
                '}';
    }
}
