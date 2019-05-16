package com.soholy.cb.entity.iot.deviceManager;

import java.util.Map;

public class CommandDTOV4 {
  private String serviceId;
  
  private String method;
  
  private Map<String, Object> paras;
  
  public String getServiceId() { return this.serviceId; }
  
  public void setServiceId(String serviceId) { this.serviceId = serviceId; }
  
  public String getMethod() { return this.method; }
  
  public void setMethod(String method) { this.method = method; }
  
  public Map<String, Object> getParas() { return this.paras; }
  
  public void setParas(Map<String, Object> paras) { this.paras = paras; }
}
