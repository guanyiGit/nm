package com.soholy.cb.entity.app.conditions;

public class Condition_CycleTimer implements Conditions {
  private String type;
  
  private String id;
  
  private Condition_CycleTimer timeRange;
  
  private String interval;
  
  public String getType() { return this.type; }
  
  public void setType(String type) { this.type = type; }
  
  public String getId() { return this.id; }
  
  public void setId(String id) { this.id = id; }
  
  public Condition_CycleTimer getTimeRange() { return this.timeRange; }
  
  public void setTimeRange(Condition_CycleTimer timeRange) { this.timeRange = timeRange; }
  
  public String getInterval() { return this.interval; }
  
  public void setInterval(String interval) { this.interval = interval; }
}
