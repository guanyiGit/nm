package com.soholy.cb.service.app;

import com.alibaba.fastjson.JSONObject;
import com.soholy.cb.entity.iot.deviceManager.CommandDTONA2Cloud;
import com.soholy.cb.entity.iot.deviceManager.UpdateDeviceInfoReqDTO;

public interface ManageService {
  JSONObject register(String paramString1, String paramString2, String paramString3, Integer paramInteger) throws Exception;
  
  JSONObject register_gateway(String paramString1, String paramString2, CommandDTONA2Cloud paramCommandDTONA2Cloud) throws Exception;
  
  JSONObject findDeviceStatus(String paramString) throws Exception;
  
  boolean deleteDevice(String paramString) throws Exception;
  
  JSONObject deleteDevice_gateway(CommandDTONA2Cloud paramCommandDTONA2Cloud) throws Exception;
  
  boolean modifyDeviceId(String paramString, UpdateDeviceInfoReqDTO paramUpdateDeviceInfoReqDTO) throws Exception;
  
  JSONObject freshDeviceAuth(String paramString1, String paramString2, String paramString3, Integer paramInteger) throws Exception;
  
  JSONObject setEncryption(String paramString1, String paramString2, JSONObject paramJSONObject) throws Exception;
  
  JSONObject findDeviceInfo(String paramString) throws Exception;
}
