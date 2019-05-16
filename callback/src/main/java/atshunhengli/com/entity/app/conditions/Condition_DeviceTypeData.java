package atshunhengli.com.entity.app.conditions;


import atshunhengli.com.entity.app.Strategy;

public class Condition_DeviceTypeData implements Conditions {

    private String type;
    private String id;
    private DeviceTypeInfo deviceTypeInfo;
    private String operator;
    private String transInfo;
    private String duration;
    private Strategy strategy;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DeviceTypeInfo getDeviceTypeInfo() {
        return deviceTypeInfo;
    }

    public void setDeviceTypeInfo(DeviceTypeInfo deviceTypeInfo) {
        this.deviceTypeInfo = deviceTypeInfo;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getTransInfo() {
        return transInfo;
    }

    public void setTransInfo(String transInfo) {
        this.transInfo = transInfo;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

}
