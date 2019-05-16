package com.entities;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-11-05 15:17:03
 */
public class ActivityInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Date activityTime;
	//
	private String activityType;
	//
	private String activityAddress;
	//
	private String activitySubject;
	//
	private Integer orgId;
	//宣传品数量
	private Integer amount;
	//
	private String activityContent;
	//
	private Date createDate;
	//
	private Date updateDate;
	//
	private Integer operatorId;
	private String activityTarget;
	//参与人数
	private Integer participateAmount;

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
	public void setActivityTime(Date activityTime) {
		this.activityTime = activityTime;
	}
	/**
	 * 获取：
	 */
	public Date getActivityTime() {
		return activityTime;
	}
	/**
	 * 设置：
	 */
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	/**
	 * 获取：
	 */
	public String getActivityType() {
		return activityType;
	}
	/**
	 * 设置：
	 */
	public void setActivityAddress(String activityAddress) {
		this.activityAddress = activityAddress;
	}
	/**
	 * 获取：
	 */
	public String getActivityAddress() {
		return activityAddress;
	}
	/**
	 * 设置：
	 */
	public void setActivitySubject(String activitySubject) {
		this.activitySubject = activitySubject;
	}
	/**
	 * 获取：
	 */
	public String getActivitySubject() {
		return activitySubject;
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
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	/**
	 * 获取：
	 */
	public Integer getAmount() {
		return amount;
	}
	/**
	 * 设置：
	 */
	public void setActivityContent(String activityContent) {
		this.activityContent = activityContent;
	}
	/**
	 * 获取：
	 */
	public String getActivityContent() {
		return activityContent;
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

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public String getActivityTarget() {
		return activityTarget;
	}

	public void setActivityTarget(String activityTarget) {
		this.activityTarget = activityTarget;
	}

	public Integer getParticipateAmount() {
		return participateAmount;
	}

	public void setParticipateAmount(Integer participateAmount) {
		this.participateAmount = participateAmount;
	}
}
