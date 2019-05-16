package atshunhengli.com.entity.app.conditions;


import atshunhengli.com.entity.app.Strategy;

public class Condition_DeviceData implements Conditions {

    private String String;
    private String id;
    private DeviceInfo deviceInfo;
    private String operator;
    private String value;
    private String transInfo;
    private Strategy strategy;

    public String getString() {
        return String;
    }

    public void setString(String string) {
        String = string;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTransInfo() {
        return transInfo;
    }

    public void setTransInfo(String transInfo) {
        this.transInfo = transInfo;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

}
