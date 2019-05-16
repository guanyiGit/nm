package com.entities;

import java.util.Date;

public class TInfectionInfo {
    private Integer id;

    private Double detectionAmount;

    private Double infectionAmount;

    private Double harmlessTreatAmount;

    private Date detectionDate;

    private Integer orgId;

    private Integer createdBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getDetectionAmount() {
        return detectionAmount;
    }

    public void setDetectionAmount(Double detectionAmount) {
        this.detectionAmount = detectionAmount;
    }

    public Double getInfectionAmount() {
        return infectionAmount;
    }

    public void setInfectionAmount(Double infectionAmount) {
        this.infectionAmount = infectionAmount;
    }

    public Double getHarmlessTreatAmount() {
        return harmlessTreatAmount;
    }

    public void setHarmlessTreatAmount(Double harmlessTreatAmount) {
        this.harmlessTreatAmount = harmlessTreatAmount;
    }

    public Date getDetectionDate() {
        return detectionDate;
    }

    public void setDetectionDate(Date detectionDate) {
        this.detectionDate = detectionDate;
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