package com.entities;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-27 18:21:05
 */
public class ManureDisposal implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String title;
	//
	private String processMode;
	//
	private String methodDes;
	//
	private String period;
	//
	private Date dealTime;
	//
	private Integer operator;
	//
	private String description;
	//
	private Integer orgId;
	//
	private Date createDate;
	//
	private Date updateDate;

	private String deviceNo;	//溯源ID(废弃)
	private String traceId;		//溯源ID

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：
	 */
	public void setProcessMode(String processMode) {
		this.processMode = processMode;
	}
	/**
	 * 获取：
	 */
	public String getProcessMode() {
		return processMode;
	}
	/**
	 * 设置：
	 */
	public void setMethodDes(String methodDes) {
		this.methodDes = methodDes;
	}
	/**
	 * 获取：
	 */
	public String getMethodDes() {
		return methodDes;
	}
	/**
	 * 设置：
	 */
	public void setPeriod(String period) {
		this.period = period;
	}
	/**
	 * 获取：
	 */
	public String getPeriod() {
		return period;
	}
	/**
	 * 设置：
	 */
	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}
	/**
	 * 获取：
	 */
	public Date getDealTime() {
		return dealTime;
	}
	/**
	 * 设置：
	 */
	public void setOperator(Integer operator) {
		this.operator = operator;
	}
	/**
	 * 获取：
	 */
	public Integer getOperator() {
		return operator;
	}
	/**
	 * 设置：
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置：
	 */
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	/**
	 * 获取：
	 */
	public Integer getOrgId() {
		return orgId;
	}
	/**
	 * 设置：
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * 获取：
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

	@Override
	public String toString() {
		return "ManureDisposal{" +
				"id=" + id +
				", title='" + title + '\'' +
				", processMode='" + processMode + '\'' +
				", methodDes='" + methodDes + '\'' +
				", period='" + period + '\'' +
				", dealTime=" + dealTime +
				", operator=" + operator +
				", description='" + description + '\'' +
				", orgId=" + orgId +
				", createDate=" + createDate +
				", updateDate=" + updateDate +
				", deviceNo='" + deviceNo + '\'' +
				", traceId='" + traceId + '\'' +
				'}';
	}
}
