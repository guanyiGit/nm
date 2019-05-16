package com.entities;

import java.util.Date;

public class TManureAntigen {
    private Integer id;

    private Double testAmount;

    private Double positiveAmount;

    private Double positiveRate;

    private Date testDate;

    private Integer orgId;

    private Integer createdBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTestAmount() {
        return testAmount;
    }

    public void setTestAmount(Double testAmount) {
        this.testAmount = testAmount;
    }

    public Double getPositiveAmount() {
        return positiveAmount;
    }

    public void setPositiveAmount(Double positiveAmount) {
        this.positiveAmount = positiveAmount;
    }

    public Double getPositiveRate() {
        return positiveRate;
    }

    public void setPositiveRate(Double positiveRate) {
        this.positiveRate = positiveRate;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }
}