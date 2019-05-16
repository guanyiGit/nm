package com.soholy.cb.service.app;

import com.alibaba.fastjson.JSONObject;
import com.soholy.cb.enums.CmdType;
import com.soholy.cb.enums.CodecVersion;

public interface CmdService {
  int generateMid();
  
  JSONObject sendCommand(CmdType paramCmdType, Integer paramInteger1, Integer paramInteger2, String paramString, CodecVersion codecVersion) throws Exception;
}
