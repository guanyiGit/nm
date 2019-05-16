package com.soholy.cb.entity.app;

import com.soholy.cb.entity.app.action.Action;
import com.soholy.cb.entity.app.conditions.Condition_CycleTimer;
import com.soholy.cb.entity.app.conditions.Conditions;
import com.soholy.cb.entity.app.triggerSource.TriggerSource;
import java.util.List;

public class RuleDTO1_2 {
  private String ruleId;
  
  private String appKey;
  
  private String name;
  
  private List<GroupExpress> description;
  
  private String author;
  
  private List<Conditions> conditions;
  
  private Operator logic = Operator.AND;
  
  private Condition_CycleTimer timeRange;
  
  private List<Action> actions;
  
  private String matchNow = "no";
  
  private String status = "active";
  
  private Conditions groupExpress;
  
  private String timezoneID;
  
  private List<TriggerSource> triggerSources;
  
  public String getRuleId() { return this.ruleId; }
  
  public void setRuleId(String ruleId) { this.ruleId = ruleId; }
  
  public String getAppKey() { return this.appKey; }
  
  public void setAppKey(String appKey) { this.appKey = appKey; }
  
  public String getName() { return this.name; }
  
  public void setName(String name) { this.name = name; }
  
  public List<GroupExpress> getDescription() { return this.description; }
  
  public void setDescription(List<GroupExpress> description) { this.description = description; }
  
  public String getAuthor() { return this.author; }
  
  public void setAuthor(String author) { this.author = author; }
  
  public List<Conditions> getConditions() { return this.conditions; }
  
  public void setConditions(List<Conditions> conditions) { this.conditions = conditions; }
  
  public Operator getLogic() { return this.logic; }
  
  public void setLogic(Operator logic) { this.logic = logic; }
  
  public Condition_CycleTimer getTimeRange() { return this.timeRange; }
  
  public void setTimeRange(Condition_CycleTimer timeRange) { this.timeRange = timeRange; }
  
  public List<Action> getActions() { return this.actions; }
  
  public void setActions(List<Action> actions) { this.actions = actions; }
  
  public String getMatchNow() { return this.matchNow; }
  
  public void setMatchNow(String matchNow) { this.matchNow = matchNow; }
  
  public String getStatus() { return this.status; }
  
  public void setStatus(String status) { this.status = status; }
  
  public Conditions getGroupExpress() { return this.groupExpress; }
  
  public void setGroupExpress(Conditions groupExpress) { this.groupExpress = groupExpress; }
  
  public String getTimezoneID() { return this.timezoneID; }
  
  public void setTimezoneID(String timezoneID) { this.timezoneID = timezoneID; }
  
  public List<TriggerSource> getTriggerSources() { return this.triggerSources; }
  
  public void setTriggerSources(List<TriggerSource> triggerSources) { this.triggerSources = triggerSources; }
}
