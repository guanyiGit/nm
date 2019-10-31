package com.soholy.cb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = {"classpath:config/conf.properties"}, ignoreResourceNotFound = false, encoding = "UTF-8")
public class IotPropertiesConfig {
  @Value("${write.log.path}")
  private String logPath;
  
  @Value("${iot.codec.param.name}")
  private String codecParamName;
  
  @Value("${iot.codec.server.name}")
  private String serviceId;
  
  @Value("${iot.codec.command.name}")
  private String commandName;
  
  @Value("${iot.codec.command.value}")
  private String commandValue;
  
  @Value("${iot.callback.host}")
  private String callbackHost;
  
  @Value("${iot.callback.port}")
  private String callbackPort;
  
  @Value("${iot.application.appid}")
  private String appid;
  
  @Value("${iot.application.secret}")
  private String secret;
  
  @Value("${iot.server.address.host}")
  private String iotServerHost;
  
  @Value("${iot.server.address.post}")
  private String iotServerPost;
  
  @Value("${iot.device.info.type}")
  private String deviceType;
  
  @Value("${iot.device.info.manufacturerId}")
  private String manufacturerId;
  
  @Value("${iot.device.info.model}")
  private String model;
  
  @Value("${iot.codec.command.name}")
  private String cmdName;
  
  @Value("${iot.codec.command.value}")
  private String cmdProp;
  
  public void setLogPath(String logPath) { this.logPath = logPath; }
  
  public void setCodecParamName(String codecParamName) { this.codecParamName = codecParamName; }
  
  public void setServiceId(String serviceId) { this.serviceId = serviceId; }
  
  public void setCommandName(String commandName) { this.commandName = commandName; }
  
  public void setCommandValue(String commandValue) { this.commandValue = commandValue; }
  
  public void setCallbackHost(String callbackHost) { this.callbackHost = callbackHost; }
  
  public void setCallbackPort(String callbackPort) { this.callbackPort = callbackPort; }
  
  public void setAppid(String appid) { this.appid = appid; }
  
  public void setSecret(String secret) { this.secret = secret; }
  
  public void setIotServerHost(String iotServerHost) { this.iotServerHost = iotServerHost; }
  
  public void setIotServerPost(String iotServerPost) { this.iotServerPost = iotServerPost; }
  
  public void setDeviceType(String deviceType) { this.deviceType = deviceType; }
  
  public void setManufacturerId(String manufacturerId) { this.manufacturerId = manufacturerId; }
  
  public void setModel(String model) { this.model = model; }
  
  public void setCmdName(String cmdName) { this.cmdName = cmdName; }
  
  public void setCmdProp(String cmdProp) { this.cmdProp = cmdProp; }
  
  public boolean equals(Object o) { if (o == this)
      return true; 
    if (!(o instanceof IotPropertiesConfig))
      return false; 
    IotPropertiesConfig other = (IotPropertiesConfig)o;
    if (!other.canEqual(this))
      return false; 
    Object this$logPath = getLogPath(), other$logPath = other.getLogPath();
    if ((this$logPath == null) ? (other$logPath != null) : !this$logPath.equals(other$logPath))
      return false; 
    Object this$codecParamName = getCodecParamName(), other$codecParamName = other.getCodecParamName();
    if ((this$codecParamName == null) ? (other$codecParamName != null) : !this$codecParamName.equals(other$codecParamName))
      return false; 
    Object this$serviceId = getServiceId(), other$serviceId = other.getServiceId();
    if ((this$serviceId == null) ? (other$serviceId != null) : !this$serviceId.equals(other$serviceId))
      return false; 
    Object this$commandName = getCommandName(), other$commandName = other.getCommandName();
    if ((this$commandName == null) ? (other$commandName != null) : !this$commandName.equals(other$commandName))
      return false; 
    Object this$commandValue = getCommandValue(), other$commandValue = other.getCommandValue();
    if ((this$commandValue == null) ? (other$commandValue != null) : !this$commandValue.equals(other$commandValue))
      return false; 
    Object this$callbackHost = getCallbackHost(), other$callbackHost = other.getCallbackHost();
    if ((this$callbackHost == null) ? (other$callbackHost != null) : !this$callbackHost.equals(other$callbackHost))
      return false; 
    Object this$callbackPort = getCallbackPort(), other$callbackPort = other.getCallbackPort();
    if ((this$callbackPort == null) ? (other$callbackPort != null) : !this$callbackPort.equals(other$callbackPort))
      return false; 
    Object this$appid = getAppid(), other$appid = other.getAppid();
    if ((this$appid == null) ? (other$appid != null) : !this$appid.equals(other$appid))
      return false; 
    Object this$secret = getSecret(), other$secret = other.getSecret();
    if ((this$secret == null) ? (other$secret != null) : !this$secret.equals(other$secret))
      return false; 
    Object this$iotServerHost = getIotServerHost(), other$iotServerHost = other.getIotServerHost();
    if ((this$iotServerHost == null) ? (other$iotServerHost != null) : !this$iotServerHost.equals(other$iotServerHost))
      return false; 
    Object this$iotServerPost = getIotServerPost(), other$iotServerPost = other.getIotServerPost();
    if ((this$iotServerPost == null) ? (other$iotServerPost != null) : !this$iotServerPost.equals(other$iotServerPost))
      return false; 
    Object this$deviceType = getDeviceType(), other$deviceType = other.getDeviceType();
    if ((this$deviceType == null) ? (other$deviceType != null) : !this$deviceType.equals(other$deviceType))
      return false; 
    Object this$manufacturerId = getManufacturerId(), other$manufacturerId = other.getManufacturerId();
    if ((this$manufacturerId == null) ? (other$manufacturerId != null) : !this$manufacturerId.equals(other$manufacturerId))
      return false; 
    Object this$model = getModel(), other$model = other.getModel();
    if ((this$model == null) ? (other$model != null) : !this$model.equals(other$model))
      return false; 
    Object this$cmdName = getCmdName(), other$cmdName = other.getCmdName();
    if ((this$cmdName == null) ? (other$cmdName != null) : !this$cmdName.equals(other$cmdName))
      return false; 
    Object this$cmdProp = getCmdProp(), other$cmdProp = other.getCmdProp();
    return ((this$cmdProp == null) ? (other$cmdProp != null) : !this$cmdProp.equals(other$cmdProp)) ? false : true; }
  
  protected boolean canEqual(Object other) { return other instanceof IotPropertiesConfig; }
  

  
  public String toString() { return "IotPropertiesConfig(logPath=" + getLogPath() + ", codecParamName=" + getCodecParamName() + ", serviceId=" + getServiceId() + ", commandName=" + getCommandName() + ", commandValue=" + getCommandValue() + ", callbackHost=" + getCallbackHost() + ", callbackPort=" + getCallbackPort() + ", appid=" + getAppid() + ", secret=" + getSecret() + ", iotServerHost=" + getIotServerHost() + ", iotServerPost=" + getIotServerPost() + ", deviceType=" + getDeviceType() + ", manufacturerId=" + getManufacturerId() + ", model=" + getModel() + ", cmdName=" + getCmdName() + ", cmdProp=" + getCmdProp() + ")"; }
  
  public String getLogPath() { return this.logPath; }
  
  public String getCodecParamName() { return this.codecParamName; }
  
  public String getServiceId() { return this.serviceId; }
  
  public String getCommandName() { return this.commandName; }
  
  public String getCommandValue() { return this.commandValue; }
  
  public String getCallbackHost() { return this.callbackHost; }
  
  public String getCallbackPort() { return this.callbackPort; }
  
  public String getAppid() { return this.appid; }
  
  public String getSecret() { return this.secret; }
  
  public String getIotServerHost() { return this.iotServerHost; }
  
  public String getIotServerPost() { return this.iotServerPost; }
  
  public String getDeviceType() { return this.deviceType; }
  
  public String getManufacturerId() { return this.manufacturerId; }
  
  public String getModel() { return this.model; }
  
  public String getCmdName() { return this.cmdName; }
  
  public String getCmdProp() { return this.cmdProp; }
}
