package com.soholy.cb.service.app;

import com.soholy.cb.config.IotPropertiesConfig;
import com.soholy.cb.enums.CallbackType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterCallBack {
  @Autowired
  private IotPropertiesConfig conf;
  
  @Autowired
  private AppService appService;
  
  private Logger logger = LoggerFactory.getLogger(RegisterCallBack.class);
  
  private String mappingBaseUrl = "callback";
  
  private String callbackUrl() { return "https://" + this.conf.getCallbackHost() + ":" + this.conf.getCallbackPort(); }
  
  public void register() {
    try {
      String deviceDatasChanged = callbackUrl() + "/" + this.mappingBaseUrl + "/" + CallbackType.deviceDatasChanged;
      boolean bool1 = this.appService.subscribe(CallbackType.deviceDatasChanged, deviceDatasChanged);
      if (!bool1)
        this.logger.warn("订阅上传callback失败"); 
      this.logger.info("订阅上传callback");
      String bindDevice = callbackUrl() + "/" + this.mappingBaseUrl + "/" + CallbackType.bindDevice;
      boolean bool3 = this.appService.subscribe(CallbackType.bindDevice, bindDevice);
      if (!bool3)
        this.logger.warn("订阅设备绑定callback失败"); 
      this.logger.info("订阅设备绑定callback");
      String commandRsp = callbackUrl() + "/" + this.mappingBaseUrl + "/" + CallbackType.commandRsp;
      boolean bool4 = this.appService.subscribe(CallbackType.commandRsp, commandRsp);
      if (!bool4)
        this.logger.warn("订阅命令响应callback失败"); 
      this.logger.info("订阅命令响应callback");
      String deviceAdded = callbackUrl() + "/" + this.mappingBaseUrl + "/" + CallbackType.deviceAdded;
      boolean bool5 = this.appService.subscribe(CallbackType.deviceAdded, deviceAdded);
      if (!bool5)
        this.logger.warn("订阅设备添加callback失败"); 
      this.logger.info("订阅设备添加callback");
      String devicewarnChanged = callbackUrl() + "/" + this.mappingBaseUrl + "/" + CallbackType.deviceInfoChanged;
      boolean bool6 = this.appService.subscribe(CallbackType.deviceInfoChanged, devicewarnChanged);
      if (!bool6)
        this.logger.warn("订阅设备信息变化callback失败"); 
      this.logger.info("订阅设备信息变化callback");
      String messageConfirm = callbackUrl() + "/" + this.mappingBaseUrl + "/" + CallbackType.messageConfirm;
      boolean bool7 = this.appService.subscribe(CallbackType.messageConfirm, messageConfirm);
      if (!bool7)
        this.logger.warn("订阅息确认callback失败"); 
      this.logger.info("订阅息确认callback");
    } catch (Exception e) {
      e.printStackTrace();
      this.logger.warn("订阅息确认callback失败", e);
    } 
  }
}
