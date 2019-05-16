package atshunhengli.com.entity.app.conditions;


import atshunhengli.com.entity.app.Strategy;
import com.alibaba.fastjson.JSON;

public class Condition_DeviceGroupData implements Conditions {

    private String type;
    private String id;
    private DeviceGroupInfo deviceGroupInfo;
    private String operator;
    private String value;
    private JSON transInfo;
    private Integer duration;
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

    public DeviceGroupInfo getDeviceGroupInfo() {
        return deviceGroupInfo;
    }

    public void setDeviceGroupInfo(DeviceGroupInfo deviceGroupInfo) {
        this.deviceGroupInfo = deviceGroupInfo;
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

    public JSON getTransInfo() {
        return transInfo;
    }

    public void setTransInfo(JSON transInfo) {
        this.transInfo = transInfo;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

}
