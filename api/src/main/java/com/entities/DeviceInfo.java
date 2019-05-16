package com.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-30 14:01:54
 */
public class DeviceInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String deviceNo;
	//
	private Integer status;
	//
	private String brand;
	//
	private String model;
	//
	private Date dateOfProduction;
	//
	private String remarks;
	//
	private Integer type;
	//
	private Date startTime;
	//
	private Date wirteOffTime;
	//
	private Integer orgId;
	//
	private Integer receiveOrg;
	//
	private Integer receiver;
	//
	private Date receiveTime;
	//
	private Date createDate;
	//
	private Date updateDate;
	//
	private String isDistribution;

	private Date activateTime;
	private Date freezeTime;

	private String imei;
	private String traceabilityNum;
	private String batch;
	private String psk;

	private String manufacturerNum;
	private String deviceIotId;

	private List<DeviceRecord> records;

	public List<DeviceRecord> getRecords() {
		return records;
	}

	public void setRecords(List<DeviceRecord> records) {
		this.records = records;
	}

	public String getManufacturerNum() {
		return manufacturerNum;
	}

	public void setManufacturerNum(String manufacturerNum) {
		this.manufacturerNum = manufacturerNum;
	}

	public String getDeviceIotId() {
		return deviceIotId;
	}

	public void setDeviceIotId(String deviceIotId) {
		this.deviceIotId = deviceIotId;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getTraceabilityNum() {
		return traceabilityNum;
	}

	public void setTraceabilityNum(String traceabilityNum) {
		this.traceabilityNum = traceabilityNum;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getPsk() {
		return psk;
	}

	public void setPsk(String psk) {
		this.psk = psk;
	}

	public Date getActivateTime() {
		return activateTime;
	}

	public void setActivateTime(Date activateTime) {
		this.activateTime = activateTime;
	}

	public Date getFreezeTime() {
		return freezeTime;
	}

	public void setFreezeTime(Date freezeTime) {
		this.freezeTime = freezeTime;
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
	/**
	 * 设置：
	 */
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	/**
	 * 获取：
	 */
	public String getDeviceNo() {
		return deviceNo;
	}
	/**
	 * 设置：
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	/**
	 * 获取：
	 */
	public String getBrand() {
		return brand;
	}
	/**
	 * 设置：
	 */
	public void setModel(String model) {
		this.model = model;
	}
	/**
	 * 获取：
	 */
	public String getModel() {
		return model;
	}
	/**
	 * 设置：
	 */
	public void setDateOfProduction(Date dateOfProduction) {
		this.dateOfProduction = dateOfProduction;
	}
	/**
	 * 获取：
	 */
	public Date getDateOfProduction() {
		return dateOfProduction;
	}
	/**
	 * 设置：
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * 设置：
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取：
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * 设置：
	 */
	public void setWirteOffTime(Date wirteOffTime) {
		this.wirteOffTime = wirteOffTime;
	}
	/**
	 * 获取：
	 */
	public Date getWirteOffTime() {
		return wirteOffTime;
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
	public void setReceiveOrg(Integer receiveOrg) {
		this.receiveOrg = receiveOrg;
	}
	/**
	 * 获取：
	 */
	public Integer getReceiveOrg() {
		return receiveOrg;
	}
	/**
	 * 设置：
	 */
	public void setReceiver(Integer receiver) {
		this.receiver = receiver;
	}
	/**
	 * 获取：
	 */
	public Integer getReceiver() {
		return receiver;
	}
	/**
	 * 设置：
	 */
	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}
	/**
	 * 获取：
	 */
	public Date getReceiveTime() {
		return receiveTime;
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
	/**
	 * 设置：
	 */
	public void setIsDistribution(String isDistribution) {
		this.isDistribution = isDistribution;
	}
	/**
	 * 获取：
	 */
	public String getIsDistribution() {
		return isDistribution;
	}
}
