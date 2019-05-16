package com.entities;

import java.util.Date;

public class TNeckletChange {
    private Integer id;

    private String oldNeckletNo;

    private String newNeckletNo;

    private Integer changeReasons;

    private String description;

    private String traceId;

    private  Integer orgId;

    private Integer operator;

    private Date createtime;

    private Date updatetime;

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOldNeckletNo() {
        return oldNeckletNo;
    }

    public void setOldNeckletNo(String oldNeckletNo) {
        this.oldNeckletNo = oldNeckletNo;
    }

    public String getNewNeckletNo() {
        return newNeckletNo;
    }

    public void setNewNeckletNo(String newNeckletNo) {
        this.newNeckletNo = newNeckletNo;
    }

    public Integer getChangeReasons() {
        return changeReasons;
    }

    public void setChangeReasons(Integer changeReasons) {
        this.changeReasons = changeReasons;
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

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}