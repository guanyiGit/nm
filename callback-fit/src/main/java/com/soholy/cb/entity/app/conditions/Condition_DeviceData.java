package com.soholy.cb.entity.app.conditions;

import com.soholy.cb.entity.app.Strategy;

public class Condition_DeviceData implements Conditions {
  private String String;
  
  private String id;
  
  private DeviceInfo deviceInfo;
  
  private String operator;
  
  private String value;
  
  private String transInfo;
  
  private Strategy strategy;
  
  public String getString() { return this.String; }
  
  public void setString(String string) { this.String = string; }
  
  public String getId() { return this.id; }
  
  public void setId(String id) { this.id = id; }
  
  public DeviceInfo getDeviceInfo() { return this.deviceInfo; }
  
  public void setDeviceInfo(DeviceInfo deviceInfo) { this.deviceInfo = deviceInfo; }
  
  public String getOperator() { return this.operator; }
  
  public void setOperator(String operator) { this.operator = operator; }
  
  public String getValue() { return this.value; }
  
  public void setValue(String value) { this.value = value; }
  
  public String getTransInfo() { return this.transInfo; }
  
  public void setTransInfo(String transInfo) { this.transInfo = transInfo; }
  
  public Strategy getStrategy() { return this.strategy; }
  
  public void setStrategy(Strategy strategy) { this.strategy = strategy; }
}
