package com.statisanalysis.entity;

import java.util.Date;

/**
 * @Description
 * @Author: linchong
 * @Date: 2018/10/5 11:27
 * @Version 1.0
 */
public class DrugVO {
    private String drugName;
    private Date createDate;

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "DrugVO{" +
                "drugName='" + drugName + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
