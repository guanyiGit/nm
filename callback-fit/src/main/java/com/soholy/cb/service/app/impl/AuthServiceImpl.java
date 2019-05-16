package com.soholy.cb.service.app.impl;

import com.alibaba.fastjson.JSONObject;
import com.soholy.cb.config.IotPropertiesConfig;
import com.soholy.cb.entity.iot.AccessToken;
import com.soholy.cb.service.app.AuthService;
import com.soholy.cb.utils.HttpClientUtil;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
  private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
  
  @Autowired
  private IotPropertiesConfig conf;
  
  public String iotServerBaseUrl() { return "https://" + this.conf.getIotServerHost() + ":" + this.conf.getIotServerPost(); }
  
  public Map<String, String> setAuthentication() throws Exception {
    Map<String, String> headers = new HashMap<String, String>();
    headers.put("app_key", this.conf.getAppid());
    headers.put("Authorization", "Bearer " + getAccessToken().getAccessToken());
    headers.put("Content-Type", ContentType.APPLICATION_JSON.getMimeType());
    return headers;
  }
  
  public AccessToken getAccessToken() throws Exception {
    String url = iotServerBaseUrl() + "/iocm/app/sec/v1.1.0/login";
    Map<String, String> paramsObj = new HashMap<String, String>();
    paramsObj.put("appId", this.conf.getAppid());
    paramsObj.put("secret", this.conf.getSecret());
    HttpClientUtil.HttpResult resp = HttpClientUtil.executePostParams(null, url, null, null, null, paramsObj);
    String content = resp.getContent();
    if (resp == null || resp.getStatusCode().intValue() != 200 || resp.getContent() == null) {
      logger.error("getAccessToken error ,result statusCode:" + resp.getStatusCode());
      return null;
    } 
    return (AccessToken)JSONObject.parseObject(content, AccessToken.class);
  }
  
  public AccessToken refreshToken() throws Exception {
    String url = iotServerBaseUrl() + "/iocm/app/sec/v1.1.0/refreshToken";
    Map<String, String> paramsObj = new HashMap<String, String>();
    paramsObj.put("appId", this.conf.getAppid());
    paramsObj.put("secret", this.conf.getSecret());
    paramsObj.put("refreshToken", getAccessToken().getRefreshToken());
    HttpClientUtil.HttpResult resp = HttpClientUtil.executePostParams(null, url, null, null, null, 
        JSONObject.toJSONString(paramsObj));
    if (resp == null || resp.getStatusCode().intValue() != 200 || resp.getContent() == null) {
      logger.error("getAccessToken error ,result statusCode:" + resp.getStatusCode());
      return null;
    } 
    String content = resp.getContent();
    return (AccessToken)JSONObject.parseObject(content, AccessToken.class);
  }
  
  public boolean logoutAuth() throws Exception {
    String url = iotServerBaseUrl() + "/iocm/app/sec/v1.1.0/logout";
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("accessToken", getAccessToken());
    HttpClientUtil.HttpResult resp = HttpClientUtil.executeHttpParams(url, "POST", null, null, jsonObject.toJSONString(), null);
    if (resp == null || resp.getStatusCode().intValue() != 204) {
      logger.error("logoutAuth error ,result statusCode:" + resp.getStatusCode());
      return false;
    } 
    return true;
  }
}
