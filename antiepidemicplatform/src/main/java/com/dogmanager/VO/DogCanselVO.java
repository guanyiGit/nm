/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: DogCanselVO
 * Author:   Administrator
 * Date:     2018/9/28 15:35
 * Description: 犬只注销VO
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.dogmanager.VO;

import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈犬只注销VO〉
 *
 * @author Administrator
 * @create 2018/9/28
 * @since 1.0.0
 */
public class DogCanselVO {
    //小图片
    private String smallUrl;
    //大图
    private String bigUrl;
    //溯源号
    private String traceId;
    //描述
    private String description;
    //注销时间
    private Date CanselDate;
    //原因
    private String reason;
    //id
    private  Integer id;
   //犬主姓名
    private String name;

    private String dogName;

    private String breed;
   //犬主身份证
    private String cardNum;
    //电犬主话
    private String phoneNum;

    private String town;
    //门牌号
    private String no;
    //防疫员名称
    private String porName;
    //防疫员电话
    private String porPhoneNum;
    //牧委会名称
    private String pcName;
    //设备号
    private String deviceNo;
    //项圈处理方式
    private String deviceDealWay;

    public String getDeviceDealWay() {
        return deviceDealWay;
    }

    public void setDeviceDealWay(String deviceDealWay) {
        this.deviceDealWay = deviceDealWay;
    }

    public String getSmallUrl() {
        return smallUrl;
    }

    public void setSmallUrl(String smallUrl) {
        this.smallUrl = smallUrl;
    }

    public String getBigUrl() {
        return bigUrl;
    }

    public void setBigUrl(String bigUrl) {
        this.bigUrl = bigUrl;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCanselDate() {
        return CanselDate;
    }

    public void setCanselDate(Date canselDate) {
        CanselDate = canselDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getPorName() {
        return porName;
    }

    public void setPorName(String porName) {
        this.porName = porName;
    }

    public String getPorPhoneNum() {
        return porPhoneNum;
    }

    public void setPorPhoneNum(String porPhoneNum) {
        this.porPhoneNum = porPhoneNum;
    }

    public String getPcName() {
        return pcName;
    }

    public void setPcName(String pcName) {
        this.pcName = pcName;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }
}
