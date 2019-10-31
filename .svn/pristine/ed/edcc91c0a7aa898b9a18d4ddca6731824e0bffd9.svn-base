package com.soholy.cb.service.app;

import com.alibaba.fastjson.JSONObject;
import com.soholy.cb.entity.app.RuleDTO1_2;
import com.soholy.cb.entity.iot.deviceManager.CommandDTOV4;
import com.soholy.cb.entity.iot.deviceManager.CommandNA2CloudHeader;
import com.soholy.cb.enums.CallbackType;
import java.util.List;

public interface AppService {
  boolean subscribe(CallbackType paramCallbackType, String paramString) throws Exception;
  
  JSONObject findDeviceHistory(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt1, int paramInt2, String paramString5, String paramString6) throws Exception;
  
  JSONObject findDeviceCapabilities(String paramString1, String paramString2) throws Exception;
  
  JSONObject createDeviceCommand(String paramString1, CommandDTOV4 paramCommandDTOV4, String paramString2, Integer paramInteger) throws Exception;
  
  JSONObject findDevice_V4_Commands(Integer paramInteger1, Integer paramInteger2, String paramString1, String paramString2, String paramString3) throws Exception;
  
  JSONObject modifyDevice_V4_Commands(String paramString1, String paramString2) throws Exception;
  
  JSONObject createCancelAndRevoke_V4(Integer paramInteger1, Integer paramInteger2, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5) throws Exception;
  
  JSONObject callDevice(String paramString1, String paramString2, CommandNA2CloudHeader paramCommandNA2CloudHeader, JSONObject paramJSONObject) throws Exception;
  
  JSONObject createTasks(String paramString1, String paramString2, String paramString3, JSONObject paramJSONObject) throws Exception;
  
  JSONObject findTask(String paramString) throws Exception;
  
  JSONObject findTaskInfo(String paramString1, String paramString2, Integer paramInteger1, Integer paramInteger2, String paramString3, String paramString4, String paramString5, String paramString6) throws Exception;
  
  JSONObject createRule(RuleDTO1_2 paramRuleDTO1_2) throws Exception;
  
  JSONObject toUpdateRule(RuleDTO1_2 paramRuleDTO1_2) throws Exception;
  
  JSONObject modifyRule(String paramString1, String paramString2) throws Exception;
  
  boolean deleteRule(String paramString) throws Exception;
  
  JSONObject findRule(String paramString1, String paramString2) throws Exception;
  
  JSONObject modefyRules(List<JSONObject> paramList) throws Exception;
  
  boolean deleteSubs() throws Exception;
  
  String findSubs() throws Exception;
}
