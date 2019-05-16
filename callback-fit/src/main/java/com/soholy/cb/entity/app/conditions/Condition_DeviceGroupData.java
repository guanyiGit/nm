package com.soholy.cb.entity.app.conditions;

import com.alibaba.fastjson.JSON;
import com.soholy.cb.entity.app.Strategy;

public class Condition_DeviceGroupData implements Conditions {
  private String type;
  
  private String id;
  
  private DeviceGroupInfo deviceGroupInfo;
  
  private String operator;
  
  private String value;
  
  private JSON transInfo;
  
  private Integer duration;
  
  private Strategy strategy;
  
  public String getType() { return this.type; }
  
  public void setType(String type) { this.type = type; }
  
  public String getId() { return this.id; }
  
  public void setId(String id) { this.id = id; }
  
  public DeviceGroupInfo getDeviceGroupInfo() { return this.deviceGroupInfo; }
  
  public void setDeviceGroupInfo(DeviceGroupInfo deviceGroupInfo) { this.deviceGroupInfo = deviceGroupInfo; }
  
  public String getOperator() { return this.operator; }
  
  public void setOperator(String operator) { this.operator = operator; }
  
  public String getValue() { return this.value; }
  
  public void setValue(String value) { this.value = value; }
  
  public JSON getTransInfo() { return this.transInfo; }
  
  public void setTransInfo(JSON transInfo) { this.transInfo = transInfo; }
  
  public Integer getDuration() { return this.duration; }
  
  public void setDuration(Integer duration) { this.duration = duration; }
  
  public Strategy getStrategy() { return this.strategy; }
  
  public void setStrategy(Strategy strategy) { this.strategy = strategy; }
}
