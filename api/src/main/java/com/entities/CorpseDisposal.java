package com.entities;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-28 09:52:35
 */
public class CorpseDisposal implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String traceId;
	//
	private Integer areaId;
	//
	private String causeOfDeath;
	//
	private String processingMethod;
	//
	private String methodDescription;
	//
	private Date dealTime;
	//
	private Integer operatorId;
	//
	private String description;
	//
	private Integer orgId;
	//
	private Date createDate;
	//
	private Date updateDate;
	private Integer deviceDealWay;
	
	public Integer getDeviceDealWay() {
		return deviceDealWay;
	}
	public void setDeviceDealWay(Integer deviceDealWay) {
		this.deviceDealWay = deviceDealWay;
	}
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

	
	
	public String getTraceId() {
		return traceId;
	}
	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}
	/**
	 * 设置：
	 */
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	/**
	 * 获取：
	 */
	public Integer getAreaId() {
		return areaId;
	}
	/**
	 * 设置：
	 */
	public void setCauseOfDeath(String causeOfDeath) {
		this.causeOfDeath = causeOfDeath;
	}
	/**
	 * 获取：
	 */
	public String getCauseOfDeath() {
		return causeOfDeath;
	}
	/**
	 * 设置：
	 */
	public void setProcessingMethod(String processingMethod) {
		this.processingMethod = processingMethod;
	}
	/**
	 * 获取：
	 */
	public String getProcessingMethod() {
		return processingMethod;
	}
	/**
	 * 设置：
	 */
	public void setMethodDescription(String methodDescription) {
		this.methodDescription = methodDescription;
	}
	/**
	 * 获取：
	 */
	public String getMethodDescription() {
		return methodDescription;
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
	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}
	/**
	 * 获取：
	 */
	public Integer getOperatorId() {
		return operatorId;
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
}
