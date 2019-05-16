package com.soholy.cb.entity.app.conditions;

public class DeviceTypeInfo {
  private String manufacturerId;
  
  private String model;
  
  private String deviceType;
  
  private String protocolType;
  
  private String path;
  
  public String getManufacturerId() { return this.manufacturerId; }
  
  public void setManufacturerId(String manufacturerId) { this.manufacturerId = manufacturerId; }
  
  public String getModel() { return this.model; }
  
  public void setModel(String model) { this.model = model; }
  
  public String getDeviceType() { return this.deviceType; }
  
  public void setDeviceType(String deviceType) { this.deviceType = deviceType; }
  
  public String getProtocolType() { return this.protocolType; }
  
  public void setProtocolType(String protocolType) { this.protocolType = protocolType; }
  
  public String getPath() { return this.path; }
  
  public void setPath(String path) { this.path = path; }
}
