/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TDeviceRefDog
 * Author:   Administrator
 * Date:     2018/10/12 16:21
 * Description: 设备与狗的关联表
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.entities;

import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈设备与狗的关联表〉
 *
 * @author Administrator
 * @create 2018/10/12
 * @since 1.0.0
 */

public class TDeviceRefDog {

    private  Integer id;
    private  String traceId;
    private  String deviceNo;
    private  Date   startDate;
    private  Date  expireDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
}
