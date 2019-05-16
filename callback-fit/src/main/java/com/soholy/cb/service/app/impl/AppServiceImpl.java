package com.soholy.cb.service.app.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.soholy.cb.entity.app.RuleDTO1_2;
import com.soholy.cb.entity.iot.deviceManager.CommandDTOV4;
import com.soholy.cb.entity.iot.deviceManager.CommandNA2CloudHeader;
import com.soholy.cb.enums.CallbackType;
import com.soholy.cb.service.app.AppService;
import com.soholy.cb.service.app.AuthService;
import com.soholy.cb.utils.HttpClientUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource({"classpath:config/conf.properties"})
public class AppServiceImpl implements AppService {
  private static final Logger logger = LoggerFactory.getLogger(AppServiceImpl.class);
  
  @Autowired
  private AuthService authService;
  
  @Value("${iot.application.appid}")
  private String appid;
  
  public boolean subscribe(CallbackType notifyType, String callbackurl) throws Exception {
    String url = this.authService.iotServerBaseUrl() + "/iocm/app/sub/v1.2.0/subscribe";
    Map<String, String> headers = this.authService.setAuthentication();
    Map<String, String> paramsObj = new HashMap<String, String>();
    paramsObj.put("notifyType", notifyType.toString());
    paramsObj.put("callbackurl", callbackurl);
    HttpClientUtil.HttpResult resp = HttpClientUtil.executeHttpParams(url, "POST", null, headers, 
        JSONObject.toJSONString(paramsObj), null);
    if (resp != null && resp.getStatusCode().intValue() == 201)
      return true; 
    logger.info("subscribe error ,result: " + resp);
    return false;
  }
  
  public JSONObject findDeviceHistory(String deviceId, String gatewayId, String serviceId, String property, int pageNo, int pageSize, String startTime, String endTime) throws Exception {
    if (StringUtils.isBlank(deviceId) || StringUtils.isBlank(gatewayId)) {
      logger.warn("findDeviceHistory 方法参数有误");
      return null;
    } 
    String url = this.authService.iotServerBaseUrl() + "/iocm/app/data/v1.1.0/deviceDataHistory";
    Map<String, String> headers = this.authService.setAuthentication();
    Map<String, String> paramsObj = new HashMap<String, String>();
    paramsObj.put("deviceId", deviceId);
    paramsObj.put("gatewayId", gatewayId);
    paramsObj.put("appId ", this.authService.iotServerBaseUrl() + this.appid);
    if (StringUtils.isNotBlank(serviceId))
      paramsObj.put("serviceId", serviceId); 
    if (StringUtils.isNotBlank(startTime))
      paramsObj.put("startTime ", startTime); 
    HttpClientUtil.HttpResult resp = HttpClientUtil.executeHttpParams(url, "GET", null, headers, paramsObj, null);
    if (resp == null || resp.getStatusCode().intValue() != 200 || resp.getContent() == null) {
      logger.error("findDeviceHistory error ,result statusCode:" + resp.getStatusCode());
      return null;
    } 
    return JSONObject.parseObject(resp.getContent());
  }
  
  public JSONObject findDeviceCapabilities(String deviceId, String gatewayId) throws Exception {
    if (StringUtils.isBlank(deviceId)) {
      logger.warn("findDeviceCapabilities 方法参数有误");
      return null;
    } 
    String url = this.authService.iotServerBaseUrl() + "/iocm/app/data/v1.1.0/deviceCapabilities";
    url = url + "?deviceId={deviceId}".replace("{deviceId}", deviceId);
    Map<String, String> headers = this.authService.setAuthentication();
    Map<String, String> paramsObj = null;
    if (StringUtils.isNotBlank(gatewayId)) {
      paramsObj = new HashMap<String, String>();
      paramsObj.put("deviceId", deviceId);
    } 
    HttpClientUtil.HttpResult resp = HttpClientUtil.executeHttpParams(url, "GET", null, headers, 
        JSONObject.toJSONString(paramsObj), null);
    if (resp == null || resp.getStatusCode().intValue() != 200 || resp.getContent() == null) {
      logger.error("findDeviceCapabilities error ,result statusCode:" + resp.getStatusCode());
      return null;
    } 
    return JSONObject.parseObject(resp.getContent());
  }
  
  public JSONObject createDeviceCommand(String deviceId, CommandDTOV4 command, String callbackUrl, Integer expireTime) throws Exception {
    if (StringUtils.isBlank(deviceId) || command == null || command.getServiceId() != null || command
      .getMethod() != null) {
      logger.warn("createDevice 方法参数有误");
      return null;
    } 
    String url = this.authService.iotServerBaseUrl() + "/iocm/app/cmd/v1.4.0/deviceCommands";
    Map<String, String> headers = this.authService.setAuthentication();
    JSONObject parameObj = new JSONObject();
    parameObj.put("deviceId", deviceId);
    parameObj.put("command", command);
    if (StringUtils.isNotBlank(callbackUrl))
      parameObj.put("callbackUrl", callbackUrl); 
    if (null != expireTime)
      parameObj.put("expireTime", expireTime); 
    HttpClientUtil.HttpResult resp = HttpClientUtil.executeHttpParams(url, "POST", null, headers, parameObj.toJSONString(), null);
    if (resp == null || resp.getStatusCode().intValue() != 201 || resp.getContent() == null) {
      logger.error("createDevice error ,result statusCode:" + resp.getStatusCode());
      return null;
    } 
    return JSONObject.parseObject(resp.getContent());
  }
  
  public JSONObject findDevice_V4_Commands(Integer pageNo, Integer pageSize, String deviceId, String startTime, String endTime) throws Exception {
    String url = this.authService.iotServerBaseUrl() + "/iocm/app/cmd/v1.4.0/deviceCommands";
    Map<String, String> headers = this.authService.setAuthentication();
    JSONObject paramsObj = new JSONObject();
    if (pageNo != null)
      paramsObj.put("pageNo", String.valueOf(pageNo)); 
    if (pageSize != null)
      paramsObj.put("pageSize", String.valueOf(pageSize)); 
    if (StringUtils.isNotBlank(deviceId))
      paramsObj.put("deviceId", deviceId); 
    if (StringUtils.isNotBlank(startTime))
      paramsObj.put("startTime", startTime); 
    if (StringUtils.isNotBlank(endTime))
      paramsObj.put("endTime", endTime); 
    HttpClientUtil.HttpResult resp = HttpClientUtil.executeHttpParams(url, "GET", null, headers, paramsObj, null);
    if (resp == null || resp.getStatusCode().intValue() != 200 || resp.getContent() == null) {
      logger.error("findDevice_V4_Commands error ,result statusCode:" + resp.getStatusCode());
      return null;
    } 
    return JSONObject.parseObject(resp.getContent());
  }
  
  public JSONObject modifyDevice_V4_Commands(String deviceCommandId, String status) throws Exception {
    if (StringUtils.isBlank(deviceCommandId) || StringUtils.isBlank(status)) {
      logger.warn("modifyDevice_V4_Commands 方法参数有误");
      return null;
    } 
    String url = this.authService.iotServerBaseUrl() + "/iocm/app/cmd/v1.4.0/deviceCommands";
    url = url + "/{deviceCommandId}".replace("{deviceCommandId}", deviceCommandId);
    Map<String, String> headers = this.authService.setAuthentication();
    Map<String, String> paramsObj = new HashMap<String, String>();
    paramsObj.put("status", status);
    HttpClientUtil.HttpResult resp = HttpClientUtil.executeHttpParams(url, "PUT", null, headers, 
        JSONObject.toJSONString(paramsObj), null);
    if (resp == null || resp.getStatusCode().intValue() != 200 || resp.getContent() == null) {
      logger.error("modifyDevice_V4_Commands error ,result statusCode:" + resp.getStatusCode());
      return null;
    } 
    return JSONObject.parseObject(resp.getContent());
  }
  
  public JSONObject createCancelAndRevoke_V4(Integer pageNo, Integer pageSize, String taskId, String deviceId, String status, String startTime, String endTime) throws Exception {
    String url = this.authService.iotServerBaseUrl() + "/iocm/app/cmd/v1.4.0/deviceCommandCancelTasks";
    JSONObject paramsObj = new JSONObject();
    if (pageNo != null)
      paramsObj.put("pageNo", pageNo); 
    if (pageSize != null)
      paramsObj.put("pageSize", pageSize); 
    if (StringUtils.isNotBlank(deviceId))
      paramsObj.put("deviceId", deviceId); 
    if (StringUtils.isNotBlank(status))
      paramsObj.put("status", status); 
    if (StringUtils.isNotBlank(startTime))
      paramsObj.put("startTime", startTime); 
    if (StringUtils.isNotBlank(endTime))
      paramsObj.put("endTime", endTime); 
    HttpClientUtil.HttpResult resp = HttpClientUtil.executeHttpParams(url, "GET", null, this.authService.setAuthentication(), paramsObj
        .toJSONString(), null);
    if (resp == null || resp.getStatusCode().intValue() != 200 || resp.getContent() == null) {
      logger.error("createCancelAndRevoke_V4 error ,result statusCode:" + resp.getStatusCode());
      return null;
    } 
    return JSONObject.parseObject(resp.getContent());
  }
  
  public JSONObject callDevice(String deviceId, String serviceId, CommandNA2CloudHeader header, JSONObject body) throws Exception {
    if (StringUtils.isBlank(serviceId) || StringUtils.isBlank(serviceId) || header == null || body == null || header
      .getMode() == null || header.getMethod() == null) {
      logger.warn("callDevice 方法参数有误");
      return null;
    } 
    String url = this.authService.iotServerBaseUrl() + "/iocm/app/signaltrans/v1.1.0/devices/{deviceId}/services/{serviceId}/sendCommand";
    url = url.replace("{deviceId}", deviceId).replace("{serviceId}", serviceId);
    JSONObject paramsObj = new JSONObject();
    paramsObj.put("deviceId", deviceId);
    paramsObj.put("serviceId", serviceId);
    paramsObj.put("header", header);
    paramsObj.put("body", body);
    HttpClientUtil.HttpResult resp = HttpClientUtil.executeHttpParams(url, "POST", null, this.authService.setAuthentication(), paramsObj
        .toJSONString(), null);
    if (resp == null || resp.getStatusCode().intValue() != 202 || resp.getContent() == null) {
      logger.error("callDevice error ,result statusCode:" + resp.getStatusCode());
      return null;
    } 
    return JSONObject.parseObject(resp.getContent());
  }
  
  public JSONObject createTasks(String timeout, String taskName, String taskType, JSONObject param) throws Exception {
    if (StringUtils.isBlank(timeout) || StringUtils.isBlank(taskName) || StringUtils.isBlank(taskType) || param == null) {
      logger.warn("callDevice 方法参数有误");
      return null;
    } 
    JSONObject paramsObj = new JSONObject();
    paramsObj.put("appId", this.authService.iotServerBaseUrl() + this.appid);
    paramsObj.put("timeout", timeout);
    paramsObj.put("taskName", taskName);
    paramsObj.put("taskType", taskType);
    paramsObj.put("param", param);
    String url = this.authService.iotServerBaseUrl() + "/iocm/app/batchtask/v1.1.0/tasks";
    HttpClientUtil.HttpResult resp = HttpClientUtil.executeHttpParams(url, "POST", null, this.authService.setAuthentication(), paramsObj
        .toJSONString(), null);
    if (resp == null || resp.getStatusCode().intValue() != 200 || resp.getContent() == null) {
      logger.error("createTasks error ,result statusCode:" + resp.getStatusCode());
      return null;
    } 
    return JSONObject.parseObject(resp.getContent());
  }
  
  public JSONObject findTask(String taskId) throws Exception {
    if (StringUtils.isBlank(taskId)) {
      logger.warn("findTask 方法参数有误");
      return null;
    } 
    String url = this.authService.iotServerBaseUrl() + "/iocm/app/batchtask/v1.1.0/tasks";
    url = url + "/{taskId}".replace("{taskId}", taskId);
    HttpClientUtil.HttpResult resp = HttpClientUtil.executeHttpParams(url, "GET", null, this.authService.setAuthentication(), null, null);
    if (resp == null || resp.getStatusCode().intValue() != 200 || resp.getContent() == null) {
      logger.error("findTask error ,result statusCode:" + resp.getStatusCode());
      return null;
    } 
    return JSONObject.parseObject(resp.getContent());
  }
  
  public JSONObject findTaskInfo(String taskId, String status, Integer pageNo, Integer pageSize, String index, String nodeId, String deviceId, String commandId) throws Exception {
    if (StringUtils.isBlank(taskId)) {
      logger.warn("findTaskInfo 方法参数有误");
      return null;
    } 
    String url = this.authService.iotServerBaseUrl() + "/iocm/app/batchtask/v1.1.0/taskDetails";
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("taskId", taskId);
    if (StringUtils.isNotBlank(status))
      jsonObject.put("status", status); 
    if (pageNo != null)
      jsonObject.put("pageNo", pageNo); 
    if (pageNo != null)
      jsonObject.put("pageSize", pageSize); 
    if (StringUtils.isNotBlank(index))
      jsonObject.put("index", index); 
    if (StringUtils.isNotBlank(nodeId))
      jsonObject.put("nodeId", nodeId); 
    if (StringUtils.isNotBlank(deviceId))
      jsonObject.put("deviceId", deviceId); 
    if (StringUtils.isNotBlank(commandId))
      jsonObject.put("commandId", commandId); 
    HttpClientUtil.HttpResult resp = HttpClientUtil.executeHttpParams(url, "GET", null, this.authService.setAuthentication(), null, null);
    if (resp == null || resp.getStatusCode().intValue() != 200 || resp.getContent() == null) {
      logger.error("findTaskInfo error ,result statusCode:" + resp.getStatusCode());
      return null;
    } 
    return JSONObject.parseObject(resp.getContent());
  }
  
  public JSONObject createRule(RuleDTO1_2 ruleDTO1_2) throws Exception {
    if (ruleDTO1_2 == null) {
      logger.warn("createRule 方法参数有误");
      return null;
    } 
    String url = this.authService.iotServerBaseUrl() + "/iocm/app/rule/v1.2.0/rules";
    HttpClientUtil.HttpResult resp = HttpClientUtil.executeHttpParams(url, "POST", null, this.authService.setAuthentication(), 
        JSONObject.toJSONString(ruleDTO1_2), null);
    if (resp == null || resp.getStatusCode().intValue() != 200 || resp.getContent() == null) {
      logger.error("getAccessToken error ,result statusCode:" + resp.getStatusCode());
      return null;
    } 
    return JSONObject.parseObject(resp.getContent());
  }
  
  public JSONObject toUpdateRule(RuleDTO1_2 ruleDTO1_2) throws Exception {
    if (ruleDTO1_2 == null) {
      logger.warn("toUpdateRule 方法参数有误");
      return null;
    } 
    String url = this.authService.iotServerBaseUrl() + "/iocm/app/rule/v1.2.0/rules";
    HttpClientUtil.HttpResult resp = HttpClientUtil.executeHttpParams(url, "PUT", null, this.authService.setAuthentication(), 
        JSONObject.toJSONString(ruleDTO1_2), null);
    if (resp == null || resp.getStatusCode().intValue() != 200 || resp.getContent() == null) {
      logger.error("toUpdateRule error ,result statusCode:" + resp.getStatusCode());
      return null;
    } 
    return JSONObject.parseObject(resp.getContent());
  }
  
  public JSONObject modifyRule(String ruleId, String status) throws Exception {
    if (StringUtils.isBlank(ruleId) || StringUtils.isBlank(status)) {
      logger.warn("modifyRule 方法参数有误");
      return null;
    } 
    String url = this.authService.iotServerBaseUrl() + "/iocm/app/rule/v1.2.0/rules";
    url = url + "/{ruleId}/status/{status}".replace("{ruleId}", ruleId).replace("{status}", status);
    HttpClientUtil.HttpResult resp = HttpClientUtil.executeHttpParams(url, "PUT", null, this.authService.setAuthentication(), null, null);
    if (resp == null || resp.getStatusCode().intValue() != 200 || resp.getContent() == null) {
      logger.error("modifyRule error ,result statusCode:" + resp.getStatusCode());
      return null;
    } 
    return JSONObject.parseObject(resp.getContent());
  }
  
  public boolean deleteRule(String ruleId) throws Exception {
    if (StringUtils.isBlank(ruleId)) {
      logger.warn("deleteRule 方法参数有误");
      return false;
    } 
    String url = this.authService.iotServerBaseUrl() + "/iocm/app/rule/v1.2.0/rules";
    url = url + "/{ruleId}".replace("{ruleId}", ruleId);
    HttpClientUtil.HttpResult resp = HttpClientUtil.executeHttpParams(url, "DELETE", null, this.authService.setAuthentication(), null, null);
    if (resp == null || resp.getStatusCode().intValue() != 204) {
      logger.error("deleteRule error ,result statusCode:" + resp.getStatusCode());
      return true;
    } 
    return false;
  }
  
  public JSONObject findRule(String author, String name) throws Exception {
    if (StringUtils.isBlank(author)) {
      logger.warn("findRule 方法参数有误");
      return null;
    } 
    String url = this.authService.iotServerBaseUrl() + "/iocm/app/rule/v1.2.0/rules";
    url = url + "?author={author}".replace("{author}", author);
    if (StringUtils.isBlank(name))
      url = url + "&name={name}".replace("{name}", name); 
    HttpClientUtil.HttpResult resp = HttpClientUtil.executeHttpParams(url, "GET", null, this.authService.setAuthentication(), null, null);
    if (resp == null || resp.getStatusCode().intValue() != 200 || resp.getContent() == null) {
      logger.error("findRule error ,result statusCode:" + resp.getStatusCode());
      return null;
    } 
    return JSONObject.parseObject(resp.getContent());
  }
  
  public JSONObject modefyRules(List<JSONObject> ruleStatusUpdateReqDTO) throws Exception {
    if (ruleStatusUpdateReqDTO == null || ruleStatusUpdateReqDTO.size() == 0) {
      logger.warn("modefyRules 方法参数有误");
      return null;
    } 
    String url = this.authService.iotServerBaseUrl() + "/iocm/app/rule/v1.2.0/rules";
    JSONObject paramsObj = new JSONObject();
    JSONArray jsonArray = new JSONArray();
    paramsObj.put("requests", jsonArray);
    for (JSONObject obj : ruleStatusUpdateReqDTO) {
      String ruleId = obj.getString("ruleId");
      String status = obj.getString("status");
      if (StringUtils.isNotBlank(ruleId) && StringUtils.isNotBlank(status)) {
        jsonArray.add(obj);
        continue;
      } 
      logger.warn("modefyRules 方法参数有误");
      return null;
    } 
    HttpClientUtil.HttpResult resp = HttpClientUtil.executeHttpParams(url, "GET", null, this.authService.setAuthentication(), paramsObj, null);
    if (resp == null || resp.getStatusCode().intValue() != 200 || resp.getContent() == null) {
      logger.error("modefyRules error ,result statusCode:" + resp.getStatusCode());
      return null;
    } 
    return JSONObject.parseObject(resp.getContent());
  }
  
  public boolean deleteSubs() throws Exception {
    String url = this.authService.iotServerBaseUrl() + "/iocm/app/sub/v1.2.0/subscriptions";
    HttpClientUtil.HttpResult resp = HttpClientUtil.executeHttpParams(url, "DELETE", null, this.authService.setAuthentication(), null, null);
    if (resp != null && 204 == resp.getStatusCode().intValue())
      return true; 
    return false;
  }
  
  public String findSubs() throws Exception {
    String url = this.authService.iotServerBaseUrl() + "/iocm/app/sub/v1.2.0/subscriptions";
    HttpClientUtil.HttpResult resp = HttpClientUtil.executeHttpParams(url, "GET", null, this.authService.setAuthentication(), null, null);
    if (resp != null && 200 == resp.getStatusCode().intValue())
      return resp.getContent(); 
    logger.info(ReflectionToStringBuilder.toString(resp, ToStringStyle.MULTI_LINE_STYLE));
    return null;
  }
}
