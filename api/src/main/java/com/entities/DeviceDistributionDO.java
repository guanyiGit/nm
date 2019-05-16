package com.entities;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-11-05 15:17:22
 */
public class DeviceDistributionDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//发放单位
	private Integer distributorOrgId;
	//接收单位(组织：组织id )
	private Integer receiveOrg;
	//接收人(特指乡级指定的接收人)
	private Integer receiver;
	//接收人姓名（特指州、县指定的接收人）
	private String receiverName;
	//发放数量
	private Integer amount;
	//发放时间
	private Date distributeDate;
	//创建时间
	private Date createDate;
	//修改时间
	private Date updateDate;
	//操作人
	private Integer operatorId;
	//备注
	private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDistributorOrgId() {
		return distributorOrgId;
	}

	public void setDistributorOrgId(Integer distributorOrgId) {
		this.distributorOrgId = distributorOrgId;
	}

	public Integer getReceiveOrg() {
		return receiveOrg;
	}

	public void setReceiveOrg(Integer receiveOrg) {
		this.receiveOrg = receiveOrg;
	}

	public Integer getReceiver() {
		return receiver;
	}

	public void setReceiver(Integer receiver) {
		this.receiver = receiver;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Date getDistributeDate() {
		return distributeDate;
	}

	public void setDistributeDate(Date distributeDate) {
		this.distributeDate = distributeDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
