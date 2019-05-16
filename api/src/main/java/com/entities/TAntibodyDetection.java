package com.entities;

import java.util.Date;

public class TAntibodyDetection {
    private Integer id;

    private Double testAmount;

    private Double qualifiedAmount;

    private Double qualifiedRate;

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

    public Double getQualifiedAmount() {
        return qualifiedAmount;
    }

    public void setQualifiedAmount(Double qualifiedAmount) {
        this.qualifiedAmount = qualifiedAmount;
    }

    public Double getQualifiedRate() {
        return qualifiedRate;
    }

    public void setQualifiedRate(Double qualifiedRate) {
        this.qualifiedRate = qualifiedRate;
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