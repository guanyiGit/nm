package atshunhengli.com.entity.app;


import atshunhengli.com.entity.app.action.Action;
import atshunhengli.com.entity.app.conditions.Condition_CycleTimer;
import atshunhengli.com.entity.app.conditions.Conditions;
import atshunhengli.com.entity.app.triggerSource.TriggerSource;

import java.util.List;

public class RuleDTO1_2 {

    // 规则实例的id，仅在规则更新时有效，其余情况应为空
    private String ruleId;
    // 应用实例的key值
    private String appKey;
    // 必选 规则名
    private String name;
    // 规则描述
    private List<GroupExpress> description;
    // 必选 用户id
    private String author;
    // 条件列表，自定义结构体（见下表）, 与groupExpress二选一必填
    private List<Conditions> conditions;// Condition_DeviceData
    // 多条件之间的逻辑关系，支持and和or，默认为and
    private Operator logic = Operator.AND;
    // 件场景的时间段
    private Condition_CycleTimer timeRange;
    // 定时场景必选
    private List<Action> actions;
    // 表示是否立即触发，即是否理解进行条件判断，条件符合的话执行动作，取值有“yes”,“no”，默认为“no”
    private String matchNow = "no";
    // 规则的状态，“active”代表激活状态，“inactive”代表未激活，默认为“active”状态
    private String status = "active";
    // 复杂多条件表达式
    private Conditions groupExpress;
    // 国际时区，如Asia/Shanghai、UTC
    private String timezoneID;
    // 触发源列表，自定义结构体TriggerSource，只与GroupExpress联用构造复杂多条件规则，目前有DEVICE类型和TIMER类型
    private List<TriggerSource> triggerSources;

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GroupExpress> getDescription() {
        return description;
    }

    public void setDescription(List<GroupExpress> description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Conditions> getConditions() {
        return conditions;
    }

    public void setConditions(List<Conditions> conditions) {
        this.conditions = conditions;
    }

    public Operator getLogic() {
        return logic;
    }

    public void setLogic(Operator logic) {
        this.logic = logic;
    }

    public Condition_CycleTimer getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(Condition_CycleTimer timeRange) {
        this.timeRange = timeRange;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public String getMatchNow() {
        return matchNow;
    }

    public void setMatchNow(String matchNow) {
        this.matchNow = matchNow;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Conditions getGroupExpress() {
        return groupExpress;
    }

    public void setGroupExpress(Conditions groupExpress) {
        this.groupExpress = groupExpress;
    }

    public String getTimezoneID() {
        return timezoneID;
    }

    public void setTimezoneID(String timezoneID) {
        this.timezoneID = timezoneID;
    }

    public List<TriggerSource> getTriggerSources() {
        return triggerSources;
    }

    public void setTriggerSources(List<TriggerSource> triggerSources) {
        this.triggerSources = triggerSources;
    }

}
