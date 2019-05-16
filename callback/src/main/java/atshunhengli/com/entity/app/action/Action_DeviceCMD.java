package atshunhengli.com.entity.app.action;

import atshunhengli.com.entity.app.CMD;
import com.alibaba.fastjson.JSON;

public class Action_DeviceCMD implements Action {

    private String type;
    private String id;
    private String appKey;
    private String deviceId;
    private CMD cmd;
    private String cmdVersion;
    private String cmdMetaData;
    private JSON transInfo;

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

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public CMD getCmd() {
        return cmd;
    }

    public void setCmd(CMD cmd) {
        this.cmd = cmd;
    }

    public String getCmdVersion() {
        return cmdVersion;
    }

    public void setCmdVersion(String cmdVersion) {
        this.cmdVersion = cmdVersion;
    }

    public String getCmdMetaData() {
        return cmdMetaData;
    }

    public void setCmdMetaData(String cmdMetaData) {
        this.cmdMetaData = cmdMetaData;
    }

    public JSON getTransInfo() {
        return transInfo;
    }

    public void setTransInfo(JSON transInfo) {
        this.transInfo = transInfo;
    }

}
