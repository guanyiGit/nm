package com.soholy.cb.entity.iot.check;

import java.util.Date;

public class Msg {
  private Integer id;
  
  private String title;
  
  private String content;
  
  private Integer status;
  
  private Integer type;
  
  private Date createDate;
  
  public Integer getId() { return this.id; }
  
  public void setId(Integer id) { this.id = id; }
  
  public String getTitle() { return this.title; }
  
  public void setTitle(String title) { this.title = title; }
  
  public String getContent() { return this.content; }
  
  public void setContent(String content) { this.content = content; }
  
  public Integer getStatus() { return this.status; }
  
  public void setStatus(Integer status) { this.status = status; }
  
  public Integer getType() { return this.type; }
  
  public void setType(Integer type) { this.type = type; }
  
  public Date getCreateDate() { return this.createDate; }
  
  public void setCreateDate(Date createDate) { this.createDate = createDate; }
}
