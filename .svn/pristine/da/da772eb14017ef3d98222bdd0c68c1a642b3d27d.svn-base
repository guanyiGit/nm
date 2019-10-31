package com.soholy.cb.service.app;

import com.soholy.cb.entity.iot.AccessToken;
import java.util.Map;

public interface AuthService {
  String iotServerBaseUrl();
  
  AccessToken getAccessToken() throws Exception;
  
  AccessToken refreshToken() throws Exception;
  
  boolean logoutAuth() throws Exception;
  
  Map<String, String> setAuthentication() throws Exception;
}
