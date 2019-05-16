package com.soholy.cb.entity.app.action;

public class Action_SMS implements Action {
  private String type;
  
  private String id;
  
  private String msisdn;
  
  private String content;
  
  private String subject;
  
  private String title;
  
  private String accountId;
  
  public String getType() { return this.type; }
  
  public void setType(String type) { this.type = type; }
  
  public String getId() { return this.id; }
  
  public void setId(String id) { this.id = id; }
  
  public String getMsisdn() { return this.msisdn; }
  
  public void setMsisdn(String msisdn) { this.msisdn = msisdn; }
  
  public String getContent() { return this.content; }
  
  public void setContent(String content) { this.content = content; }
  
  public String getSubject() { return this.subject; }
  
  public void setSubject(String subject) { this.subject = subject; }
  
  public String getTitle() { return this.title; }
  
  public void setTitle(String title) { this.title = title; }
  
  public String getAccountId() { return this.accountId; }
  
  public void setAccountId(String accountId) { this.accountId = accountId; }
}
