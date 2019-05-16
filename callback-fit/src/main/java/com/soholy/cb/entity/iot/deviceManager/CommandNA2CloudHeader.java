package com.soholy.cb.entity.iot.deviceManager;

public class CommandNA2CloudHeader {
  private String requestId;
  
  private String mode;
  
  private String from;
  
  private String toType;
  
  private String to;
  
  private String method;
  
  private String callbackURL;
  
  public String getRequestId() { return this.requestId; }
  
  public void setRequestId(String requestId) { this.requestId = requestId; }
  
  public String getMode() { return this.mode; }
  
  public void setMode(String mode) { this.mode = mode; }
  
  public String getFrom() { return this.from; }
  
  public void setFrom(String from) { this.from = from; }
  
  public String getToType() { return this.toType; }
  
  public void setToType(String toType) { this.toType = toType; }
  
  public String getTo() { return this.to; }
  
  public void setTo(String to) { this.to = to; }
  
  public String getMethod() { return this.method; }
  
  public void setMethod(String method) { this.method = method; }
  
  public String getCallbackURL() { return this.callbackURL; }
  
  public void setCallbackURL(String callbackURL) { this.callbackURL = callbackURL; }
}
