package com.soholy.cb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.time.LocalDateTime;

@TableName("t_device_info")
public class TDeviceInfoEntity extends Model<TDeviceInfoEntity> {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String deviceNo;

    private Integer status;

    private String brand;

    private String model;

    private LocalDateTime dateOfProduction;

    private String remarks;

    private Integer type;

    private LocalDateTime startTime;

    private LocalDateTime wirteOffTime;

    private Integer orgId;

    private Integer receiveOrg;

    private Integer receiver;

    private LocalDateTime receiveTime;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private Integer isDistribution;

    private LocalDateTime activateTime;

    private LocalDateTime freezeTime;

    private String imei;

    private String traceabilityNum;

    private String batch;

    private String psk;

    private String manufacturerNum;

    private String deviceIotId;

    private String firm;

    private LocalDateTime deadline;

    private Integer importBy;

    public TDeviceInfoEntity setId(Integer id) {
        this.id = id;
        return this;
    }

    public TDeviceInfoEntity setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
        return this;
    }

    public TDeviceInfoEntity setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public TDeviceInfoEntity setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public TDeviceInfoEntity setModel(String model) {
        this.model = model;
        return this;
    }

    public TDeviceInfoEntity setDateOfProduction(LocalDateTime dateOfProduction) {
        this.dateOfProduction = dateOfProduction;
        return this;
    }

    public TDeviceInfoEntity setRemarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public TDeviceInfoEntity setType(Integer type) {
        this.type = type;
        return this;
    }

    public TDeviceInfoEntity setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
        return this;
    }

    public TDeviceInfoEntity setWirteOffTime(LocalDateTime wirteOffTime) {
        this.wirteOffTime = wirteOffTime;
        return this;
    }

    public TDeviceInfoEntity setOrgId(Integer orgId) {
        this.orgId = orgId;
        return this;
    }

    public TDeviceInfoEntity setReceiveOrg(Integer receiveOrg) {
        this.receiveOrg = receiveOrg;
        return this;
    }

    public TDeviceInfoEntity setReceiver(Integer receiver) {
        this.receiver = receiver;
        return this;
    }

    public TDeviceInfoEntity setReceiveTime(LocalDateTime receiveTime) {
        this.receiveTime = receiveTime;
        return this;
    }

    public TDeviceInfoEntity setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public TDeviceInfoEntity setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    public TDeviceInfoEntity setIsDistribution(Integer isDistribution) {
        this.isDistribution = isDistribution;
        return this;
    }

    public TDeviceInfoEntity setActivateTime(LocalDateTime activateTime) {
        this.activateTime = activateTime;
        return this;
    }

    public TDeviceInfoEntity setFreezeTime(LocalDateTime freezeTime) {
        this.freezeTime = freezeTime;
        return this;
    }

    public TDeviceInfoEntity setImei(String imei) {
        this.imei = imei;
        return this;
    }

    public TDeviceInfoEntity setTraceabilityNum(String traceabilityNum) {
        this.traceabilityNum = traceabilityNum;
        return this;
    }

    public TDeviceInfoEntity setBatch(String batch) {
        this.batch = batch;
        return this;
    }

    public TDeviceInfoEntity setPsk(String psk) {
        this.psk = psk;
        return this;
    }

    public TDeviceInfoEntity setManufacturerNum(String manufacturerNum) {
        this.manufacturerNum = manufacturerNum;
        return this;
    }

    public TDeviceInfoEntity setDeviceIotId(String deviceIotId) {
        this.deviceIotId = deviceIotId;
        return this;
    }

    public TDeviceInfoEntity setFirm(String firm) {
        this.firm = firm;
        return this;
    }

    public TDeviceInfoEntity setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
        return this;
    }

    public TDeviceInfoEntity setImportBy(Integer importBy) {
        this.importBy = importBy;
        return this;
    }

    public String toString() {
        return "TDeviceInfoEntity(id=" + getId() + ", deviceNo=" + getDeviceNo() + ", status=" + getStatus() + ", brand=" + getBrand() + ", model=" + getModel() + ", dateOfProduction=" + getDateOfProduction() + ", remarks=" + getRemarks() + ", type=" + getType() + ", startTime=" + getStartTime() + ", wirteOffTime=" + getWirteOffTime() + ", orgId=" + getOrgId() + ", receiveOrg=" + getReceiveOrg() + ", receiver=" + getReceiver() + ", receiveTime=" + getReceiveTime() + ", createDate=" + getCreateDate() + ", updateDate=" + getUpdateDate() + ", isDistribution=" + getIsDistribution() + ", activateTime=" + getActivateTime() + ", freezeTime=" + getFreezeTime() + ", imei=" + getImei() + ", traceabilityNum=" + getTraceabilityNum() + ", batch=" + getBatch() + ", psk=" + getPsk() + ", manufacturerNum=" + getManufacturerNum() + ", deviceIotId=" + getDeviceIotId() + ", firm=" + getFirm() + ", deadline=" + getDeadline() + ", importBy=" + getImportBy() + ")";
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TDeviceInfoEntity))
            return false;
        TDeviceInfoEntity other = (TDeviceInfoEntity) o;
        if (!other.canEqual(this))
            return false;
        Object this$id = getId(), other$id = other.getId();
        if ((this$id == null) ? (other$id != null) : !this$id.equals(other$id))
            return false;
        Object this$deviceNo = getDeviceNo(), other$deviceNo = other.getDeviceNo();
        if ((this$deviceNo == null) ? (other$deviceNo != null) : !this$deviceNo.equals(other$deviceNo))
            return false;
        Object this$status = getStatus(), other$status = other.getStatus();
        if ((this$status == null) ? (other$status != null) : !this$status.equals(other$status))
            return false;
        Object this$brand = getBrand(), other$brand = other.getBrand();
        if ((this$brand == null) ? (other$brand != null) : !this$brand.equals(other$brand))
            return false;
        Object this$model = getModel(), other$model = other.getModel();
        if ((this$model == null) ? (other$model != null) : !this$model.equals(other$model))
            return false;
        Object this$dateOfProduction = getDateOfProduction(), other$dateOfProduction = other.getDateOfProduction();
        if ((this$dateOfProduction == null) ? (other$dateOfProduction != null) : !this$dateOfProduction.equals(other$dateOfProduction))
            return false;
        Object this$remarks = getRemarks(), other$remarks = other.getRemarks();
        if ((this$remarks == null) ? (other$remarks != null) : !this$remarks.equals(other$remarks))
            return false;
        Object this$type = getType(), other$type = other.getType();
        if ((this$type == null) ? (other$type != null) : !this$type.equals(other$type))
            return false;
        Object this$startTime = getStartTime(), other$startTime = other.getStartTime();
        if ((this$startTime == null) ? (other$startTime != null) : !this$startTime.equals(other$startTime))
            return false;
        Object this$wirteOffTime = getWirteOffTime(), other$wirteOffTime = other.getWirteOffTime();
        if ((this$wirteOffTime == null) ? (other$wirteOffTime != null) : !this$wirteOffTime.equals(other$wirteOffTime))
            return false;
        Object this$orgId = getOrgId(), other$orgId = other.getOrgId();
        if ((this$orgId == null) ? (other$orgId != null) : !this$orgId.equals(other$orgId))
            return false;
        Object this$receiveOrg = getReceiveOrg(), other$receiveOrg = other.getReceiveOrg();
        if ((this$receiveOrg == null) ? (other$receiveOrg != null) : !this$receiveOrg.equals(other$receiveOrg))
            return false;
        Object this$receiver = getReceiver(), other$receiver = other.getReceiver();
        if ((this$receiver == null) ? (other$receiver != null) : !this$receiver.equals(other$receiver))
            return false;
        Object this$receiveTime = getReceiveTime(), other$receiveTime = other.getReceiveTime();
        if ((this$receiveTime == null) ? (other$receiveTime != null) : !this$receiveTime.equals(other$receiveTime))
            return false;
        Object this$createDate = getCreateDate(), other$createDate = other.getCreateDate();
        if ((this$createDate == null) ? (other$createDate != null) : !this$createDate.equals(other$createDate))
            return false;
        Object this$updateDate = getUpdateDate(), other$updateDate = other.getUpdateDate();
        if ((this$updateDate == null) ? (other$updateDate != null) : !this$updateDate.equals(other$updateDate))
            return false;
        Object this$isDistribution = getIsDistribution(), other$isDistribution = other.getIsDistribution();
        if ((this$isDistribution == null) ? (other$isDistribution != null) : !this$isDistribution.equals(other$isDistribution))
            return false;
        Object this$activateTime = getActivateTime(), other$activateTime = other.getActivateTime();
        if ((this$activateTime == null) ? (other$activateTime != null) : !this$activateTime.equals(other$activateTime))
            return false;
        Object this$freezeTime = getFreezeTime(), other$freezeTime = other.getFreezeTime();
        if ((this$freezeTime == null) ? (other$freezeTime != null) : !this$freezeTime.equals(other$freezeTime))
            return false;
        Object this$imei = getImei(), other$imei = other.getImei();
        if ((this$imei == null) ? (other$imei != null) : !this$imei.equals(other$imei))
            return false;
        Object this$traceabilityNum = getTraceabilityNum(), other$traceabilityNum = other.getTraceabilityNum();
        if ((this$traceabilityNum == null) ? (other$traceabilityNum != null) : !this$traceabilityNum.equals(other$traceabilityNum))
            return false;
        Object this$batch = getBatch(), other$batch = other.getBatch();
        if ((this$batch == null) ? (other$batch != null) : !this$batch.equals(other$batch))
            return false;
        Object this$psk = getPsk(), other$psk = other.getPsk();
        if ((this$psk == null) ? (other$psk != null) : !this$psk.equals(other$psk))
            return false;
        Object this$manufacturerNum = getManufacturerNum(), other$manufacturerNum = other.getManufacturerNum();
        if ((this$manufacturerNum == null) ? (other$manufacturerNum != null) : !this$manufacturerNum.equals(other$manufacturerNum))
            return false;
        Object this$deviceIotId = getDeviceIotId(), other$deviceIotId = other.getDeviceIotId();
        if ((this$deviceIotId == null) ? (other$deviceIotId != null) : !this$deviceIotId.equals(other$deviceIotId))
            return false;
        Object this$firm = getFirm(), other$firm = other.getFirm();
        if ((this$firm == null) ? (other$firm != null) : !this$firm.equals(other$firm))
            return false;
        Object this$deadline = getDeadline(), other$deadline = other.getDeadline();
        if ((this$deadline == null) ? (other$deadline != null) : !this$deadline.equals(other$deadline))
            return false;
        Object this$importBy = getImportBy(), other$importBy = other.getImportBy();
        return ((this$importBy == null) ? (other$importBy != null) : !this$importBy.equals(other$importBy)) ? false : true;
    }

    protected boolean canEqual(Object other) {
        return other instanceof TDeviceInfoEntity;
    }


    public Integer getId() {
        return this.id;
    }

    public String getDeviceNo() {
        return this.deviceNo;
    }

    public Integer getStatus() {
        return this.status;
    }

    public String getBrand() {
        return this.brand;
    }

    public String getModel() {
        return this.model;
    }

    public LocalDateTime getDateOfProduction() {
        return this.dateOfProduction;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public Integer getType() {
        return this.type;
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public LocalDateTime getWirteOffTime() {
        return this.wirteOffTime;
    }

    public Integer getOrgId() {
        return this.orgId;
    }

    public Integer getReceiveOrg() {
        return this.receiveOrg;
    }

    public Integer getReceiver() {
        return this.receiver;
    }

    public LocalDateTime getReceiveTime() {
        return this.receiveTime;
    }

    public LocalDateTime getCreateDate() {
        return this.createDate;
    }

    public LocalDateTime getUpdateDate() {
        return this.updateDate;
    }

    public Integer getIsDistribution() {
        return this.isDistribution;
    }

    public LocalDateTime getActivateTime() {
        return this.activateTime;
    }

    public LocalDateTime getFreezeTime() {
        return this.freezeTime;
    }

    public String getImei() {
        return this.imei;
    }

    public String getTraceabilityNum() {
        return this.traceabilityNum;
    }

    public String getBatch() {
        return this.batch;
    }

    public String getPsk() {
        return this.psk;
    }

    public String getManufacturerNum() {
        return this.manufacturerNum;
    }

    public String getDeviceIotId() {
        return this.deviceIotId;
    }

    public String getFirm() {
        return this.firm;
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    public Integer getImportBy() {
        return this.importBy;
    }

    protected Serializable pkVal() {
        return this.id;
    }
}
