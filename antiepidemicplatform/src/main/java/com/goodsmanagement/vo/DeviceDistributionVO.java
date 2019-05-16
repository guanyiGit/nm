package com.goodsmanagement.vo;

import com.entities.DeviceDistributionDO;

import java.io.Serializable;

/**
 * @Description
 * @Author: linchong
 * @Date: 2018/11/5 23:18
 * @Version 1.0
 */
public class DeviceDistributionVO extends DeviceDistributionDO implements Serializable {
    private String distributeOrgName;   //发放单位
    private String receivorName;         //接收人(特指乡级组织指定的防疫员的姓名)
    private String receiveOrgName;       //接收单位名称
    private Integer userId;         //用于前端判断“修改”，“删除”操作是否显示

    public String getDistributeOrgName() {
        return distributeOrgName;
    }

    public void setDistributeOrgName(String distributeOrgName) {
        this.distributeOrgName = distributeOrgName;
    }

    public String getReceivorName() {
        return receivorName;
    }

    public void setReceivorName(String receivorName) {
        this.receivorName = receivorName;
    }

    public String getReceiveOrgName() {
        return receiveOrgName;
    }

    public void setReceiveOrgName(String receiveOrgName) {
        this.receiveOrgName = receiveOrgName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
