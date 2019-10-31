package com.soholy.cb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.time.LocalDateTime;

@TableName("t_device_data_wifi")
public class TDeviceDataWifiEntity extends Model<TDeviceDataWifiEntity> {
  private static final long serialVersionUID = 1L;
  
  @TableId(value = "device_data_wifi_id", type = IdType.ID_WORKER)
  private String deviceDataWifiId;
  
  private String bssid;
  
  private Float rssi;
  
  private String deviceDataId;
  
  private Long deviceNo;
  
  private LocalDateTime creationTime;
  
  private Integer mark;
  
  public TDeviceDataWifiEntity setDeviceDataWifiId(String deviceDataWifiId) { this.deviceDataWifiId = deviceDataWifiId;
    return this; }
  
  public TDeviceDataWifiEntity setBssid(String bssid) { this.bssid = bssid;
    return this; }
  
  public TDeviceDataWifiEntity setRssi(Float rssi) { this.rssi = rssi;
    return this; }
  
  public TDeviceDataWifiEntity setDeviceDataId(String deviceDataId) { this.deviceDataId = deviceDataId;
    return this; }
  
  public TDeviceDataWifiEntity setDeviceNo(Long deviceNo) { this.deviceNo = deviceNo;
    return this; }
  
  public TDeviceDataWifiEntity setCreationTime(LocalDateTime creationTime) { this.creationTime = creationTime;
    return this; }
  
  public TDeviceDataWifiEntity setMark(Integer mark) { this.mark = mark;
    return this; }
  
  public String toString() { return "TDeviceDataWifiEntity(deviceDataWifiId=" + getDeviceDataWifiId() + ", bssid=" + getBssid() + ", rssi=" + getRssi() + ", deviceDataId=" + getDeviceDataId() + ", deviceNo=" + getDeviceNo() + ", creationTime=" + getCreationTime() + ", mark=" + getMark() + ")"; }
  
  public boolean equals(Object o) { if (o == this)
      return true; 
    if (!(o instanceof TDeviceDataWifiEntity))
      return false; 
    TDeviceDataWifiEntity other = (TDeviceDataWifiEntity)o;
    if (!other.canEqual(this))
      return false; 
    Object this$deviceDataWifiId = getDeviceDataWifiId(), other$deviceDataWifiId = other.getDeviceDataWifiId();
    if ((this$deviceDataWifiId == null) ? (other$deviceDataWifiId != null) : !this$deviceDataWifiId.equals(other$deviceDataWifiId))
      return false; 
    Object this$bssid = getBssid(), other$bssid = other.getBssid();
    if ((this$bssid == null) ? (other$bssid != null) : !this$bssid.equals(other$bssid))
      return false; 
    Object this$rssi = getRssi(), other$rssi = other.getRssi();
    if ((this$rssi == null) ? (other$rssi != null) : !this$rssi.equals(other$rssi))
      return false; 
    Object this$deviceDataId = getDeviceDataId(), other$deviceDataId = other.getDeviceDataId();
    if ((this$deviceDataId == null) ? (other$deviceDataId != null) : !this$deviceDataId.equals(other$deviceDataId))
      return false; 
    Object this$deviceNo = getDeviceNo(), other$deviceNo = other.getDeviceNo();
    if ((this$deviceNo == null) ? (other$deviceNo != null) : !this$deviceNo.equals(other$deviceNo))
      return false; 
    Object this$creationTime = getCreationTime(), other$creationTime = other.getCreationTime();
    if ((this$creationTime == null) ? (other$creationTime != null) : !this$creationTime.equals(other$creationTime))
      return false; 
    Object this$mark = getMark(), other$mark = other.getMark();
    return ((this$mark == null) ? (other$mark != null) : !this$mark.equals(other$mark)) ? false : true; }
  
  protected boolean canEqual(Object other) { return other instanceof TDeviceDataWifiEntity; }

  public String getDeviceDataWifiId() { return this.deviceDataWifiId; }
  
  public String getBssid() { return this.bssid; }
  
  public Float getRssi() { return this.rssi; }
  
  public String getDeviceDataId() { return this.deviceDataId; }
  
  public Long getDeviceNo() { return this.deviceNo; }
  
  public LocalDateTime getCreationTime() { return this.creationTime; }
  
  public Integer getMark() { return this.mark; }
  
  protected Serializable pkVal() { return this.deviceDataWifiId; }
}
