package com.soholy.cb.entity.app;

import com.alibaba.fastjson.JSON;

public class CMD {
  private String messageType;
  
  private JSON messageBody;
  
  private String serviceId;
  
  public String getMessageType() { return this.messageType; }
  
  public void setMessageType(String messageType) { this.messageType = messageType; }
  
  public JSON getMessageBody() { return this.messageBody; }
  
  public void setMessageBody(JSON messageBody) { this.messageBody = messageBody; }
  
  public String getServiceId() { return this.serviceId; }
  
  public void setServiceId(String serviceId) { this.serviceId = serviceId; }
}
