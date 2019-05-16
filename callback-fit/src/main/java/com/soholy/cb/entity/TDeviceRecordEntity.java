package com.soholy.cb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.time.LocalDateTime;

@TableName("t_device_record")
public class TDeviceRecordEntity extends Model<TDeviceRecordEntity> {
  private static final long serialVersionUID = 1L;
  
  @TableId(value = "id", type = IdType.ID_WORKER)
  private String id;
  
  private Double lng;
  
  private Double lat;
  
  private Double quantity;
  
  private String deviceNo;
  
  private LocalDateTime createDate;
  
  private LocalDateTime dataTime;
  
  private LocalDateTime uploadTime;
  
  private Integer dataType;
  
  private String traceId;
  
  private Double voltage;
  
  public TDeviceRecordEntity setId(String id) { this.id = id;
    return this; }
  
  public TDeviceRecordEntity setLng(Double lng) { this.lng = lng;
    return this; }
  
  public TDeviceRecordEntity setLat(Double lat) { this.lat = lat;
    return this; }
  
  public TDeviceRecordEntity setQuantity(Double quantity) { this.quantity = quantity;
    return this; }
  
  public TDeviceRecordEntity setDeviceNo(String deviceNo) { this.deviceNo = deviceNo;
    return this; }
  
  public TDeviceRecordEntity setCreateDate(LocalDateTime createDate) { this.createDate = createDate;
    return this; }
  
  public TDeviceRecordEntity setDataTime(LocalDateTime dataTime) { this.dataTime = dataTime;
    return this; }
  
  public TDeviceRecordEntity setUploadTime(LocalDateTime uploadTime) { this.uploadTime = uploadTime;
    return this; }
  
  public TDeviceRecordEntity setDataType(Integer dataType) { this.dataType = dataType;
    return this; }
  
  public TDeviceRecordEntity setTraceId(String traceId) { this.traceId = traceId;
    return this; }
  
  public TDeviceRecordEntity setVoltage(Double voltage) { this.voltage = voltage;
    return this; }
  
  public String toString() { return "TDeviceRecordEntity(id=" + getId() + ", lng=" + getLng() + ", lat=" + getLat() + ", quantity=" + getQuantity() + ", deviceNo=" + getDeviceNo() + ", createDate=" + getCreateDate() + ", dataTime=" + getDataTime() + ", uploadTime=" + getUploadTime() + ", dataType=" + getDataType() + ", traceId=" + getTraceId() + ", voltage=" + getVoltage() + ")"; }
  
  public boolean equals(Object o) { if (o == this)
      return true; 
    if (!(o instanceof TDeviceRecordEntity))
      return false; 
    TDeviceRecordEntity other = (TDeviceRecordEntity)o;
    if (!other.canEqual(this))
      return false; 
    Object this$id = getId(), other$id = other.getId();
    if ((this$id == null) ? (other$id != null) : !this$id.equals(other$id))
      return false; 
    Object this$lng = getLng(), other$lng = other.getLng();
    if ((this$lng == null) ? (other$lng != null) : !this$lng.equals(other$lng))
      return false; 
    Object this$lat = getLat(), other$lat = other.getLat();
    if ((this$lat == null) ? (other$lat != null) : !this$lat.equals(other$lat))
      return false; 
    Object this$quantity = getQuantity(), other$quantity = other.getQuantity();
    if ((this$quantity == null) ? (other$quantity != null) : !this$quantity.equals(other$quantity))
      return false; 
    Object this$deviceNo = getDeviceNo(), other$deviceNo = other.getDeviceNo();
    if ((this$deviceNo == null) ? (other$deviceNo != null) : !this$deviceNo.equals(other$deviceNo))
      return false; 
    Object this$createDate = getCreateDate(), other$createDate = other.getCreateDate();
    if ((this$createDate == null) ? (other$createDate != null) : !this$createDate.equals(other$createDate))
      return false; 
    Object this$dataTime = getDataTime(), other$dataTime = other.getDataTime();
    if ((this$dataTime == null) ? (other$dataTime != null) : !this$dataTime.equals(other$dataTime))
      return false; 
    Object this$uploadTime = getUploadTime(), other$uploadTime = other.getUploadTime();
    if ((this$uploadTime == null) ? (other$uploadTime != null) : !this$uploadTime.equals(other$uploadTime))
      return false; 
    Object this$dataType = getDataType(), other$dataType = other.getDataType();
    if ((this$dataType == null) ? (other$dataType != null) : !this$dataType.equals(other$dataType))
      return false; 
    Object this$traceId = getTraceId(), other$traceId = other.getTraceId();
    if ((this$traceId == null) ? (other$traceId != null) : !this$traceId.equals(other$traceId))
      return false; 
    Object this$voltage = getVoltage(), other$voltage = other.getVoltage();
    return ((this$voltage == null) ? (other$voltage != null) : !this$voltage.equals(other$voltage)) ? false : true; }
  
  protected boolean canEqual(Object other) { return other instanceof TDeviceRecordEntity; }
  

  
  public String getId() { return this.id; }
  
  public Double getLng() { return this.lng; }
  
  public Double getLat() { return this.lat; }
  
  public Double getQuantity() { return this.quantity; }
  
  public String getDeviceNo() { return this.deviceNo; }
  
  public LocalDateTime getCreateDate() { return this.createDate; }
  
  public LocalDateTime getDataTime() { return this.dataTime; }
  
  public LocalDateTime getUploadTime() { return this.uploadTime; }
  
  public Integer getDataType() { return this.dataType; }
  
  public String getTraceId() { return this.traceId; }
  
  public Double getVoltage() { return this.voltage; }
  
  protected Serializable pkVal() { return this.id; }
}
