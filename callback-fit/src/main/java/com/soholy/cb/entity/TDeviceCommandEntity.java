package com.soholy.cb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.time.LocalDateTime;

@TableName("t_device_command")
public class TDeviceCommandEntity extends Model<TDeviceCommandEntity> {
  private static final long serialVersionUID = 1L;
  
  @TableId(value = "device_command_id", type = IdType.AUTO)
  private Long deviceCommandId;
  
  private Long deviceNo;
  
  private Integer cmdType;
  
  private Integer cmdValue;
  
  private Long operatorId;
  
  private LocalDateTime cmdIssuedTime;
  
  private Integer cmdMid;
  
  private Integer cmdStatus;
  
  private LocalDateTime cmdRspTime;
  
  private LocalDateTime creationTime;
  
  private String iotCommandId;
  
  public TDeviceCommandEntity setDeviceCommandId(Long deviceCommandId) { this.deviceCommandId = deviceCommandId;
    return this; }
  
  public TDeviceCommandEntity setDeviceNo(Long deviceNo) { this.deviceNo = deviceNo;
    return this; }
  
  public TDeviceCommandEntity setCmdType(Integer cmdType) { this.cmdType = cmdType;
    return this; }
  
  public TDeviceCommandEntity setCmdValue(Integer cmdValue) { this.cmdValue = cmdValue;
    return this; }
  
  public TDeviceCommandEntity setOperatorId(Long operatorId) { this.operatorId = operatorId;
    return this; }
  
  public TDeviceCommandEntity setCmdIssuedTime(LocalDateTime cmdIssuedTime) { this.cmdIssuedTime = cmdIssuedTime;
    return this; }
  
  public TDeviceCommandEntity setCmdMid(Integer cmdMid) { this.cmdMid = cmdMid;
    return this; }
  
  public TDeviceCommandEntity setCmdStatus(Integer cmdStatus) { this.cmdStatus = cmdStatus;
    return this; }
  
  public TDeviceCommandEntity setCmdRspTime(LocalDateTime cmdRspTime) { this.cmdRspTime = cmdRspTime;
    return this; }
  
  public TDeviceCommandEntity setCreationTime(LocalDateTime creationTime) { this.creationTime = creationTime;
    return this; }
  
  public TDeviceCommandEntity setIotCommandId(String iotCommandId) { this.iotCommandId = iotCommandId;
    return this; }
  
  public String toString() { return "TDeviceCommandEntity(deviceCommandId=" + getDeviceCommandId() + ", deviceNo=" + getDeviceNo() + ", cmdType=" + getCmdType() + ", cmdValue=" + getCmdValue() + ", operatorId=" + getOperatorId() + ", cmdIssuedTime=" + getCmdIssuedTime() + ", cmdMid=" + getCmdMid() + ", cmdStatus=" + getCmdStatus() + ", cmdRspTime=" + getCmdRspTime() + ", creationTime=" + getCreationTime() + ", iotCommandId=" + getIotCommandId() + ")"; }
  
  public boolean equals(Object o) { if (o == this)
      return true; 
    if (!(o instanceof TDeviceCommandEntity))
      return false; 
    TDeviceCommandEntity other = (TDeviceCommandEntity)o;
    if (!other.canEqual(this))
      return false; 
    Object this$deviceCommandId = getDeviceCommandId(), other$deviceCommandId = other.getDeviceCommandId();
    if ((this$deviceCommandId == null) ? (other$deviceCommandId != null) : !this$deviceCommandId.equals(other$deviceCommandId))
      return false; 
    Object this$deviceNo = getDeviceNo(), other$deviceNo = other.getDeviceNo();
    if ((this$deviceNo == null) ? (other$deviceNo != null) : !this$deviceNo.equals(other$deviceNo))
      return false; 
    Object this$cmdType = getCmdType(), other$cmdType = other.getCmdType();
    if ((this$cmdType == null) ? (other$cmdType != null) : !this$cmdType.equals(other$cmdType))
      return false; 
    Object this$cmdValue = getCmdValue(), other$cmdValue = other.getCmdValue();
    if ((this$cmdValue == null) ? (other$cmdValue != null) : !this$cmdValue.equals(other$cmdValue))
      return false; 
    Object this$operatorId = getOperatorId(), other$operatorId = other.getOperatorId();
    if ((this$operatorId == null) ? (other$operatorId != null) : !this$operatorId.equals(other$operatorId))
      return false; 
    Object this$cmdIssuedTime = getCmdIssuedTime(), other$cmdIssuedTime = other.getCmdIssuedTime();
    if ((this$cmdIssuedTime == null) ? (other$cmdIssuedTime != null) : !this$cmdIssuedTime.equals(other$cmdIssuedTime))
      return false; 
    Object this$cmdMid = getCmdMid(), other$cmdMid = other.getCmdMid();
    if ((this$cmdMid == null) ? (other$cmdMid != null) : !this$cmdMid.equals(other$cmdMid))
      return false; 
    Object this$cmdStatus = getCmdStatus(), other$cmdStatus = other.getCmdStatus();
    if ((this$cmdStatus == null) ? (other$cmdStatus != null) : !this$cmdStatus.equals(other$cmdStatus))
      return false; 
    Object this$cmdRspTime = getCmdRspTime(), other$cmdRspTime = other.getCmdRspTime();
    if ((this$cmdRspTime == null) ? (other$cmdRspTime != null) : !this$cmdRspTime.equals(other$cmdRspTime))
      return false; 
    Object this$creationTime = getCreationTime(), other$creationTime = other.getCreationTime();
    if ((this$creationTime == null) ? (other$creationTime != null) : !this$creationTime.equals(other$creationTime))
      return false; 
    Object this$iotCommandId = getIotCommandId(), other$iotCommandId = other.getIotCommandId();
    return ((this$iotCommandId == null) ? (other$iotCommandId != null) : !this$iotCommandId.equals(other$iotCommandId)) ? false : true; }
  
  protected boolean canEqual(Object other) { return other instanceof TDeviceCommandEntity; }
  

  
  public Long getDeviceCommandId() { return this.deviceCommandId; }
  
  public Long getDeviceNo() { return this.deviceNo; }
  
  public Integer getCmdType() { return this.cmdType; }
  
  public Integer getCmdValue() { return this.cmdValue; }
  
  public Long getOperatorId() { return this.operatorId; }
  
  public LocalDateTime getCmdIssuedTime() { return this.cmdIssuedTime; }
  
  public Integer getCmdMid() { return this.cmdMid; }
  
  public Integer getCmdStatus() { return this.cmdStatus; }
  
  public LocalDateTime getCmdRspTime() { return this.cmdRspTime; }
  
  public LocalDateTime getCreationTime() { return this.creationTime; }
  
  public String getIotCommandId() { return this.iotCommandId; }
  
  protected Serializable pkVal() { return this.deviceCommandId; }
}
